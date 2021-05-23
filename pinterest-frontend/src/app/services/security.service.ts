import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import { Login } from '../model/login';
import { map, debounceTime, distinctUntilChanged } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  urlLogin = '/api/user'

  constructor(private http: HttpClient){}

  getLogin(): Observable<Login> {
    return this.http.get<any>(`${this.urlLogin}`).pipe(
      map(obj => {
        return new Login(obj.userAuthentication.details.username, obj.userAuthentication.details.name != null
          ? obj.userAuthentication.details.login : obj.userAuthentication.details.name)
      })
    )
  }
}
