import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuctionService } from '../../services/auction.service';
import { Auction, Bid, User, BidDto } from '../../../types';
import { getImageUrl } from '../../lib/imageUrl';
import { UserService } from '../../services/user.service';
import { interval, map } from 'rxjs';
import { FormControl, FormGroup } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AudioService } from '../../services/audio.service';
import { WebSocketServiceService } from '../../services/web-socket-service.service';
import { UserSignalService } from '../../services/user-signal.service';

@Component({
  selector: 'app-liveAuction',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './liveAuction.component.html',
  styleUrl: './liveAuction.component.css',
})
export class AuctionComponent implements OnInit, OnDestroy {
  auction: Auction = {} as Auction;
  user: User = {} as User;
  expert: User = {} as User;
  isLive: number = 0; // 0: no live, 1: live, 2: finished
  time: string = '';
  bids: Bid[] = [];
  bid: FormGroup = new FormGroup({
    amount: new FormControl(''),
  });
  lastCurrUserBid: number = 0;
  lastBidAmount: number = 0;
  lastBidMessage: string = '';
  socket: any;

  constructor(
    private _route: ActivatedRoute,
    private _auctionService: AuctionService,
    private _userService: UserService,
    private _audioService: AudioService,
    private _webSocketService: WebSocketServiceService
  ) {
    this.socket = this._webSocketService.getSocket(
      this._route.snapshot.params['id'],
      (bid: Bid) => {
        bid.imageUrl = getImageUrl(bid.imageUrl, 30);
        this.bids.unshift(bid);
        this.lastBidAmount = bid.amount;
        this._audioService.incomingBid();
      }
    );
  }

  userSignal: UserSignalService = inject(UserSignalService);
  ngOnInit(): void {
    // Configuración inicial
    const auctionId = this._route.snapshot.params['id'];
    this.getAuction(auctionId);
    this.getBids(auctionId);
    this.setIsLive();
    this.user = this.userSignal.user();
  }

  ngOnDestroy(): void {
    this.socket.disconnect();
  }

  getAuction(auctionId: number): void {
    this._auctionService.findById(auctionId).subscribe((data: Auction) => {
      data.imageUrl = getImageUrl(data.imageUrl, 600, false);
      this.auction = data;
      console.log(data);
      this.getExpert(data.userId);
      this.setIsLive();
      this.start().subscribe();
    });
  }

  getBids(auctionId: number): void {
    this._auctionService
      .findBidsByAuctionId(auctionId)
      .subscribe((data: Bid[]) => {
        data.forEach((bid) => {
          bid.imageUrl = getImageUrl(bid.imageUrl, 30);
        });
        this.bids = data.length > 0 ? data.reverse() : [];
        this.lastBidAmount =
          data.length > 0 ? data[0].amount : this.auction.initialPrice;
      });
  }

  getExpert(expertId: number): void {
    this._userService.findById(expertId).subscribe((expert: User) => {
      expert.imageUrl = getImageUrl(expert.imageUrl, 70);
      this.expert = expert;
    });
  }

  setIsLive() {
    const now = new Date();
    const startDate = new Date(this.auction.startsAt);
    const endDate = new Date(this.auction.endsAt);

    if (now < startDate) {
      this.isLive = 0;
    } else if (now > startDate && now < endDate) {
      this.isLive = 1;
    } else {
      this.isLive = 2;
    }
  }

  updateTime(): void {
    if (this.isLive === 0) {
      const startDate = new Date(this.auction.startsAt);
      this.time = 'La subasta comienza en ' + this.getTimeStr(startDate);
    } else if (this.isLive === 1) {
      const endDate = new Date(this.auction.endsAt);
      this.time = 'La subasta finaliza en ' + this.getTimeStr(endDate);
    } else {
      this.time = 'La subasta ha finalizado';
    }
  }

  start() {
    return interval(1000).pipe(
      map((x: number) => {
        this.updateTime();
        return x;
      })
    );
  }

  onSubmit(): void {
    const diff = parseInt(this.bid.value.amount) - this.lastBidAmount;
    this.bid.reset();
    console.log('Puja de ' + diff);
    this.sendBid(diff);
  }

  sendBid(diff: number): void {
    console.log(
      'Validando puja de ' +
        (diff + (this.lastBidAmount || this.auction.initialPrice))
    );

    if (
      this.isBidValid(diff + (this.lastBidAmount || this.auction.initialPrice))
    ) {
      console.log('La puja es válida');

      // Actualiza el estado de la subasta
      this.lastBidAmount =
        (this.lastBidAmount || this.auction.initialPrice) + diff;

      console.log(this.lastBidAmount);

      const bid: BidDto = {
        auctionId: this.auction.auctionId || 0,
        userId: this.user.id || 0,
        amount: this.lastBidAmount,
      };

      this.userSignal.updateUser({
        ...this.user,
        money: (this.user.money || 0) - this.lastBidAmount,
      });

      console.log(bid);

      this.socket.send(
        `/app/auction/${this.auction.auctionId}`,
        {},
        JSON.stringify(bid)
      );
    } else {
      console.log('La puja no es válida');
      this._audioService.error();
    }
  }

  private isBidValid(amount: number): boolean {
    if (!this.validateLive()) return false;
    if (!this.validateNumber(amount)) return false;
    if (!this.validateExpert()) return false;
    if (!this.validateMoney(amount)) return false;
    if (!this.validateAmount(amount)) return false;
    return true;
  }

  private validateNumber(amount: number): boolean {
    if (isNaN(amount)) {
      this.lastBidMessage = 'La cantidad debe ser un número';
      console.log('La cantidad debe ser un número');
      return false;
    }
    return true;
  }

  private validateLive(): boolean {
    if (this.isLive === 0 || this.isLive === 2) {
      this.lastBidMessage = 'La subasta no está en directo';
      console.log('La subasta no está en directo');
      return false;
    }
    return true;
  }

  private validateExpert(): boolean {
    if (this.user.id === this.expert.id) {
      this.lastBidMessage = 'No puedes pujar en tu propia subasta';
      console.log('No puedes pujar en tu propia subasta');
      return false;
    }
    return true;
  }

  private validateMoney(amount: number): boolean {
    if (this.user.money != undefined && this.user.money < amount) {
      this.lastBidMessage = 'No tienes suficiente dinero';
      console.log('No tienes suficiente dinero');
      return false;
    }
    return true;
  }

  private validateAmount(amount: number): boolean {
    if (amount <= this.lastBidAmount) {
      this.lastBidMessage = 'La cantidad debe ser mayor que la última puja';
      console.log('La cantidad debe ser mayor que la última puja');
      return false;
    }
    return true;
  }

  private getTimeStr(time: Date): any {
    const total =
      Date.parse(time.toString()) - Date.parse(new Date().toString());
    const seconds = Math.floor((total / 1000) % 60);
    const minutes = Math.floor((total / 1000 / 60) % 60);
    const hours = Math.floor((total / (1000 * 60 * 60)) % 24);
    const days = Math.floor(total / (1000 * 60 * 60 * 24));

    return `${days}d ${hours}h ${minutes}m ${seconds}s`;
  }
}
