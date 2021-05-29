// import {Injectable} from '@angular/core';
// import {environment} from "../../environments/environment";
// import {Observable} from "rxjs";
// import {HttpClient} from "@angular/common/http";
// import { UserModel } from '../model/user';

// @Injectable({
//   providedIn: 'root'
// })
// export class UserService {
//   constructor(private http: HttpClient) {
//   }

//   getUserUsername() : Observable<any> {
//       return this.http.get("/api/user");
//   }

//   addUser(username: string | undefined) : Observable<UserModel> {
//       console.log("username: ", username)
//       return this.http.post<UserModel>(this.baseUrl + "/api/user/add", {
//         "username" : username
//       });
//   }


// }
