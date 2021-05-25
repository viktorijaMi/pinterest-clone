import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import { UserModel } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {


  // redirecting to github authorization
  private authorizeEndpoint = '/oauth2/authorization/github'

  // api callback to backend
  private tokenEndpoint = '/login/oauth2/code/github';
  private baseUrl = 'http://localhost:9090';
  private tokenKey = 'token';

  constructor(private http: HttpClient) {
  }

  login() {
    window.open(this.baseUrl + this.authorizeEndpoint, '_self')
  }

  updateToken(token: string) {
    localStorage.setItem(this.tokenKey, token);
  }

  fetchToken(code: string, state: string) : Observable<any> {
    return this.http.get(this.baseUrl + this.tokenEndpoint + '?code=' + code + '&state=' + state);
  }

  getToken() {
    return localStorage.getItem(this.tokenKey)
  }

  isLoggedIn(): boolean {
    const token = this.getToken()
    return token != null
  }

  getUserInfo() : Observable<any> {
    return this.http.get("/api/login")
  }

  removeToken() {
    console.log("in remove token key")
    localStorage.removeItem(this.tokenKey);
  }

  logout(): Observable<any> {
    console.log("in logout service")
    return this.http.post('/logout', this.getToken());
  }

  getUser() : Observable<UserModel> {
    return this.http.get<UserModel>(this.baseUrl + '/api/user');
  }

}
