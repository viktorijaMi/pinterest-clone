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

  updateToken(token: string){
    localStorage.setItem(this.tokenKey, token);
    console.log("in update token: ", localStorage.getItem(this.tokenKey))
  }

  fetchToken(code: string, state: string) : Observable<any> {
    return this.http.get(this.baseUrl + this.tokenEndpoint + '?code=' + code + '&state=' + state);
  }

  getToken() {
    console.log("in get token: ", localStorage.getItem(this.tokenKey))
    return localStorage.getItem(this.tokenKey)
  }

  isLoggedIn(): boolean {
    const token = this.getToken()
    // treba da se proveruva i !== undefined
    return token !== null && token !== undefined
  }

  getUserInfo() : Observable<any> {
    return this.http.get("/api/login")
  }

  removeToken() {
    localStorage.removeItem(this.tokenKey);
  }

  logout(): Observable<any> {
    return this.http.get(this.baseUrl + '/logout');
  }

  getUser() : Observable<UserModel> {
    return this.http.get<UserModel>(this.baseUrl + '/api/user');
  }

}
