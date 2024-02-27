import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Issue } from '../../types';
import { Observable } from 'rxjs';
import { environment } from '../env';

@Injectable({
  providedIn: 'root',
})
export class IssuesService {
  private url = environment.URL;

  constructor(private http: HttpClient) {}

  findAll(): Observable<Issue[]> {
    return this.http.get<Issue[]>(`${this.url}/issues`);
  }

  saveIssue(issue: Issue): Observable<Issue> {
    return this.http.post<Issue>(`${this.url}/issues/save`, issue);
  }

  updateIssue(issue: Issue, issueId: number): Observable<Issue> {
    return this.http.put<Issue>(`${this.url}/issue/update/${issueId}`, issue);
  }

  patchIssue(issue: Issue, issueId: number): Observable<Issue> {
    return this.http.patch<Issue>(`${this.url}/issue/update/${issueId}`, issue);
  }

  deleteIssue(issueId: number): Observable<Issue> {
    return this.http.delete<Issue>(`${this.url}/issues/delete/${issueId}`);
  }
}
