import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  Router,
  RouterLink,
  RouterLinkActive,
  RouterModule,
  RouterOutlet,
} from '@angular/router';
import { CategoryService } from '../../services/category.service';
import { HttpClientModule } from '@angular/common/http';
import { CategoryComponent } from '../category/category.component';
import { getImageUrl } from '../../lib/imageUrl';

@Component({
  selector: 'app-categorias',
  standalone: true,
  imports: [
    RouterOutlet,
    CommonModule,
    RouterLink,
    RouterLinkActive,
    HttpClientModule,
    RouterModule,
    CategoryComponent,
  ],
  templateUrl: './categorias.component.html',
  styleUrls: ['./categorias.component.css'],
})
export class CategoriasComponent implements OnInit {
  categorias: any[] = [];

  constructor(
    private categoriaService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.obtenerCategorias();
  }

  obtenerCategorias(): void {
    this.categoriaService.getAllCategorias().subscribe(
      (cat: any) => {
        cat.forEach((c: any) => {
          c.imageUrl = getImageUrl(c.imageUrl, 200);
        });

        this.categorias = cat;
      },
      (error) => {
        console.error('Error al obtener las categorías:', error);
      }
    );
  }
}
