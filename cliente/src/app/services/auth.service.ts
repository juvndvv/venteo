import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginForm } from '../../types';
import { environment } from '../env';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private url = environment.URL;

  constructor(private http: HttpClient) {}

  authenticate(loginForm: LoginForm): Observable<Object> {
    return this.http.post(`${this.url}/authenticate`, loginForm);
  }
}
