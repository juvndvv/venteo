import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../env';

@Injectable({
  providedIn: 'root',
})
export class NotificationsService {
  private url = environment.URL;

  constructor(private http: HttpClient) {}

  findAll(): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.url}/notifications`);
  }
  findAllByUserId(userId: number): Observable<Notification[]> {
    return this.http.get<Notification[]>(
      `${this.url}/notifications?userId=${userId}`
    );
  }
  saveNotification(notification: Notification): Observable<Notification> {
    return this.http.post<Notification>(
      `${this.url}/notifications`,
      notification
    );
  }
  updateNotification(
    notification: Notification,
    notificationId: number
  ): Observable<Notification> {
    return this.http.put<Notification>(
      `${this.url}/notification/${notificationId}`,
      notification
    );
  }
  patchNotification(
    notification: Notification,
    notificationId: number
  ): Observable<Notification> {
    return this.http.patch<Notification>(
      `${this.url}/notification/${notificationId}`,
      notification
    );
  }
  deleteNotification(notificationId: number): Observable<Notification> {
    return this.http.delete<Notification>(
      `${this.url}/notification/${notificationId}`
    );
  }
}
