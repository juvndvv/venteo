import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { AuctionComponent } from '../liveAuction/liveAuction.component';
import { AuctionService } from '../../services/auction.service';
import { Auction } from '../../../types';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { UserService } from '../../services/user.service';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { CategoryService } from '../../services/category.service';
import { CategoriasComponent } from '../categorias/categorias.component';
import { CookieService } from 'ngx-cookie-service';
import { uploadImage } from '../../lib/imageUrl';
import { HttpClient } from '@angular/common/http';
import swall from '../../lib/swall';
import { environment } from '../../env';

@Component({
  selector: 'app-generate-auction',
  standalone: true,
  imports: [
    CommonModule,
    AuctionComponent,
    ReactiveFormsModule,
    FormsModule,
    RouterLink,
    RouterLinkActive,
    CategoriasComponent,
  ],
  templateUrl: './generate-auction.component.html',
  styleUrl: './generate-auction.component.css',
})
export class GenerateAuctionComponent implements OnInit {
  categorias: any[] = [];

  newAuctionForm = new FormGroup({
    auctionName: new FormControl(''),
    auctionDescription: new FormControl(''),
    auctionCategory: new FormControl(null),
    auctionStarts: new FormControl(''),
    auctionsEnds: new FormControl(''),
    auctionPrice: new FormControl(0),
    auctionImage: new FormControl(''),
  });

  date = new Date();
  isLive: boolean = false;

  userName: string = '';
  imageUrl: string = '';

  imageFile?: File;

  constructor(
    private auctionService: AuctionService,
    private userService: UserService,
    private categoriaService: CategoryService,
    private cookieService: CookieService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.obtenerCategorias();
  }

  onFileChange(event: any) {
    if (event?.target.files.length > 0) {
      const file = event?.target.files[0];
      this.imageFile = file;
    }
  }

  private _router: Router = inject(Router);
  newAuction() {
    const formAuction = this.newAuctionForm.value;

    const fileName = formAuction.auctionImage
      ?.replace(/^.*[\\\/]/, '')
      .split('.')[0];

    const newAuctionForm: Auction = {
      auctionName: formAuction.auctionName || '',
      auctionDescription: formAuction.auctionDescription || '',
      startsAt: formAuction.auctionStarts
        ? new Date(formAuction.auctionStarts)
        : new Date(),
      endsAt: formAuction.auctionsEnds
        ? new Date(formAuction.auctionsEnds)
        : new Date(),
      initialPrice: formAuction.auctionPrice || 0,
      imageUrl: formAuction.auctionImage || '',
      userId: JSON.parse(this.cookieService.get('user')).id,
      categoryId: formAuction.auctionCategory || 0,
    };

    console.log(newAuctionForm);
    if (this.imageFile)
      uploadImage(this.imageFile).then((url: string) => {
        newAuctionForm.imageUrl = url;
        this.http
          .post<any>(`${environment.URL}/auctions`, newAuctionForm)
          .subscribe(
            (response) => {
              console.log('Subasta creada:', response);
              // Aquí puedes mostrar un mensaje de éxito o redirigir a otra página
              swall('Subasta creada con éxito', 'success');

              setTimeout(() => {
                this._router.navigate(['/']);
              }, 3000);
            },
            (error) => {
              console.error('Error al crear la subasta:', error);
              // Aquí puedes manejar el error, mostrar un mensaje de error, etc.
            }
          );
        console.log(newAuctionForm);
      });
  }

  findUserById(id: number): void {
    this.userService.findById(id).subscribe((user) => {
      this.userName = '@' + user.userName || 'unknown';
    });
  }
  obtenerCategorias(): void {
    this.categoriaService.getAllCategorias().subscribe(
      (cat: any) => {
        this.categorias = cat;
        console.log(this.categorias);
      },
      (error) => {
        console.error('Error al obtener las categorías:', error);
      }
    );
  }
}
