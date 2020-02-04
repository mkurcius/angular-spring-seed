import { Inject, Injectable } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { User } from './user';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient,
              @Inject(DOCUMENT) private document: Document) {}

  private _user: User;

  get user(): User {
    return this._user;
  }

  loadUser(): Promise<any> {
    console.log('load user');
    return this.http.get<User>('/api/user').toPromise()
      .then(user => this._user = user)
      .catch(err => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401) {
            this.document.location.href = 'http://localhost:8080/login';
          }
        }
      });
  }
}
