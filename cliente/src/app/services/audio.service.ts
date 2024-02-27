import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AudioService {
  constructor() {}

  incomingBid(): void {
    const audio = new Audio();
    audio.src = '../../../assets/bid.mp3';
    audio.load();
    audio.play();
  }

  error(): void {
    const audio = new Audio();
    audio.src = '../../../assets/error.mp3';
    audio.load();
    audio.play();
  }
}
