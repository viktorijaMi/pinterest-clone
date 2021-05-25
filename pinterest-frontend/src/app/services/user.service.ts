import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import { UserModel } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  // redirecting to github authorization
  private authorizeEndpoint = '/oauth2/authorization/github'

  // api callback to backend
  private tokenEndpoint = '/login/oauth2/code/github';
  private tokenKey = 'token';
  private baseUrl = 'http://localhost:9090';


  constructor(private http: HttpClient) {
  }

  getUserUsername() : Observable<any> {
      return this.http.get(this.baseUrl + "/api/user");
  }

  addUser(username: string | undefined) : Observable<UserModel> {
      console.log("username: ", username)
      return this.http.post<UserModel>(this.baseUrl + "/api/user/add", {
        "username" : username
      });
  }


}
