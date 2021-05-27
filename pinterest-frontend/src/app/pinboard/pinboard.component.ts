import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Params, Router } from '@angular/router';
import { PinService } from '../services/pin.service';
import { SecurityService } from '../services/security.service';
import { PinModel } from '../model/pin';
import { Observable, Subject } from 'rxjs';
import { FavoriteModel } from '../model/favorite';
import { UserModel } from '../model/user';
import {UserService} from '../services/user.service'

@Component({
  selector: 'app-pinboard',
  templateUrl: './pinboard.component.html',
  styleUrls: ['./pinboard.component.css']
})
export class PinboardComponent implements OnInit {

  pins: PinModel[] = []
  favorite: FavoriteModel | undefined;
  username: string | undefined;
  user: UserModel | undefined;
  token: string | undefined;
  delete = new Subject<number>();
  reload = new Subject<boolean>();

  constructor(private pinService: PinService,
              private securityService: SecurityService,
              private userService: UserService,
              private route: ActivatedRoute,
              private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.securityService.updateToken(params['accessToken'])
    })
    console.log("token: ",this.securityService.getToken())
    this.loadAllPins()
    this.getUserUsername()
  }

  loadAllPins() {
    this.pinService.getAllPins()
      .subscribe((result: PinModel[]) => {
        console.log('Result ', result);
        this.pins = result;
      });
    }

  getUserUsername() {
    this.userService.getUserUsername().subscribe(result => {
      this.username = `${result['name']}`
      this.getUser(this.username)
    })
  }

  getUser(username: string) {
    console.log("username in get user", username)
    this.userService.addUser(username).subscribe((result) => {
      this.user = result
    })
  }
}
