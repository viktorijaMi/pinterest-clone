import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private authorizeEndpoint = '/oauth2/authorization/github'
  private tokenEndpoint = '/login/oauth2/code/github';
  private baseUrl = "http://localhost:9090";
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
    return this.http.get(this.tokenEndpoint + '?code=' + code + '&state=' + state);
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
    localStorage.removeItem(this.tokenKey);
  }

  logout(): Observable<any> {
    return this.http.post('/logout', this.getToken());
  }
}
