import { Component, Input, OnInit } from '@angular/core';
import { Auction } from '../../../types';
import { UserService } from '../../services/user.service';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { getImageUrl } from '../../lib/imageUrl';

@Component({
  selector: 'app-category-auction',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './category-auction.component.html',
  styleUrl: './category-auction.component.css',
})
export class CategoryAuctionComponent implements OnInit {
  @Input({ required: true }) auction: Auction = {} as Auction;
  @Input() auctionIndex: number = 0;

  date = new Date();
  isLive: boolean = false;

  userName: string = '';
  imageUrl: string = '';

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.findUserById(this.auction.userId);

    this.auction.startsAt = new Date(this.auction.startsAt);
    this.auction.endsAt = new Date(this.auction.endsAt);

    if (this.auction.startsAt < this.date && this.auction.endsAt > this.date) {
      this.isLive = true;
    }

    this.imageUrl = getImageUrl(this.auction.imageUrl, 600, false);
    //this.imageUrl = getImageUrl("default-auction", 600);
  }

  findUserById(id: number): void {
    this.userService.findById(id).subscribe((user) => {
      this.userName = '@' + user.userName || 'unknown';
    });
  }
}
