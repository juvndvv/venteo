import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink, RouterLinkActive } from '@angular/router';

import { CommonModule } from '@angular/common';
import { AuctionComponent } from '../../components/auction/auction.component';
import { CategoryAuctionComponent } from '../../components/category-auction/category-auction.component';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-category',
  standalone: true,
  imports: [AuctionComponent,CommonModule,RouterLink,RouterLinkActive, CategoryAuctionComponent],
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent implements OnInit {
  categoryId$: any;
  category: any;
  auctions: any[] = [];

  constructor(private _route: ActivatedRoute, private router: Router, private categoriaService: CategoryService) { 
    this.categoryId$ = this._route.snapshot.params['id'];
    console.log("Category ID: ", this.categoryId$);
  }

  ngOnInit(): void {
    this.getCategoriaAndAuctions(this.categoryId$);
  }

  getCategoriaAndAuctions(id: number): void {
    this.categoriaService.getCategoriaById(id).subscribe(
      (data: any) => {
        this.category = data;
        this.auctions = data.auctions;
      },
      (error: any) => {
        console.error('Error al obtener la categoría y sus subastas:', error);
      }
    );
  }
}
