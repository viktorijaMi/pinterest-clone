import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Params, Router } from '@angular/router';
import { PinService } from '../services/pin.service';
import { SecurityService } from '../services/security.service';
import { PinModel } from '../model/pin';
import { Observable, Subject } from 'rxjs';
import { FavoriteModel } from '../model/favorite';
import { UserModel } from '../model/user';
import { Login } from '../model/login'
@Component({
  selector: 'app-pinboard',
  templateUrl: './pinboard.component.html',
  styleUrls: ['./pinboard.component.css']
})
export class PinboardComponent implements OnInit {

  pins: PinModel[] = []
  favorite: FavoriteModel | undefined;
  login: Login | undefined

  constructor(private pinService: PinService,
              private securityService: SecurityService,
              private route: ActivatedRoute,
              private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.getUser()
    this.loadAllPins()
  }

  loadAllPins() {
    this.pinService.getAllPins()
      .subscribe((result: PinModel[]) => {
        console.log('Result ', result);
        this.pins = result;
      });
    }

  getUser() {
    this.securityService.getLogin().subscribe((result) => {
      this.login = result
    })
  }

}
