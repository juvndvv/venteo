import { Inject, Injectable } from '@angular/core';
import { Bid, BidDto } from '../../types';
import { Stomp } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root',
})
export class WebSocketServiceService {
  public getSocket(auctionId: number, onMessage: CallableFunction): any {
    const URL: string = 'ws://200.234.238.29:3389';
    const subscribePath: string = '/topic/auction';
    const socket = new WebSocket(`${URL}/auctions`);
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
      stompClient.subscribe(`${subscribePath}/${auctionId}`, (message: any) => {
        onMessage(JSON.parse(message.body));
      });
    });

    return stompClient;
  }
}
