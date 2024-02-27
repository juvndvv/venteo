import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Auction, Bid } from '../../types';
import { environment } from '../env';

@Injectable({
  providedIn: 'root',
})
export class AuctionService {
  private url = environment.URL;

  constructor(private http: HttpClient) {}

  findAll(): Observable<Auction[]> {
    return this.http.get<Auction[]>(`${this.url}/auctions`);
  }

  createAuction(auction: Auction): Observable<Auction> {
    return this.http.post<Auction>(`${this.url}/auctions`, auction);
  }

  findById(auctionId: number): Observable<Auction> {
    return this.http.get<Auction>(`${this.url}/auction/${auctionId}`);
  }

  updateAuction(auction: Auction, auctionId: number): Observable<Auction> {
    return this.http.put<Auction>(`${this.url}/auction/${auctionId}`, auction);
  }

  patchAuction(auction: Auction, auctionId: number): Observable<Auction> {
    return this.http.patch<Auction>(
      `${this.url}/auction/${auctionId}`,
      auction
    );
  }

  deleteAuction(auctionId: number): Observable<Auction> {
    return this.http.delete<Auction>(`${this.url}/auction/${auctionId}`);
  }

  findBidsByAuctionId(auctionId: number): Observable<Bid[]> {
    return this.http.get<Bid[]>(`${this.url}/auction/${auctionId}/bids`);
  }
}
