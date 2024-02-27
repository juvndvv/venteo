import { Component, Input } from '@angular/core';
import { Notification } from '../../../types';
import { timeStamp } from 'console';
import { Timestamp, timestamp } from 'rxjs';

@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css',
})
export class NotificationComponent {
  @Input({ required: true }) notification: Notification = {} as Notification;

  constructor() {}

  getDate(date: Date): string {
    // 2025-01-02T07:00:00.000+00:00 -> 02/01/2025
    return new Date(date).toLocaleDateString('pt-BR');
  }
}
