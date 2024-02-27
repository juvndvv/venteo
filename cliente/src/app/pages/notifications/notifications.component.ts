import { Component, OnInit } from '@angular/core';
import { NotificationComponent } from '../../components/notification/notification.component';
import { NotificationsService } from '../../services/notifications.service';

@Component({
  selector: 'app-notifications',
  standalone: true,
  imports: [NotificationComponent],
  templateUrl: './notifications.component.html',
  styleUrl: './notifications.component.css',
})
export class NotificationsComponent {
  notificationsU: any[] = [];
  constructor(private notificationService: NotificationsService) {}

  ngOnInit(): void {
    this.findAllByUserId();
  }
  findAllByUserId(): void {
    this.notificationService.findAllByUserId(1).subscribe((notifications) => {
      this.notificationsU = notifications;
    });
  }
}
