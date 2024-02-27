import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Promotion } from '../../types';
import { UserPromotion } from '../../types';
import { environment } from '../env';

@Injectable({
  providedIn: 'root',
})
export class PromotionsService {
  private url = environment.URL;

  constructor(private http: HttpClient) {}

  findAll(): Observable<Promotion[]> {
    return this.http.get<Promotion[]>(`${this.url}/promotions`);
  }

  create(promotion: Promotion): Observable<Promotion> {
    return this.http.post<Promotion>(`${this.url}/promotions`, promotion);
  }

  findById(promotionId: number): Observable<Promotion> {
    return this.http.get<Promotion>(`${this.url}/promotion/${promotionId}`);
  }

  update(promotion: Promotion, promotionId: number): Observable<Promotion> {
    return this.http.put<Promotion>(
      `${this.url}/promotion/${promotionId}`,
      promotion
    );
  }

  delete(promotionId: number): Observable<Promotion> {
    return this.http.delete<Promotion>(`${this.url}/promotion/${promotionId}`);
  }

  use(userPromotionId: UserPromotion): Observable<boolean> {
    return this.http.post<boolean>(
      `${this.url}/promotion/use`,
      userPromotionId
    );
  }
}
