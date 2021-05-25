import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Params, Router } from '@angular/router';
import { PinService } from '../services/pin.service';
import { SecurityService } from '../services/security.service';
import { PinModel } from '../model/pin';
import { Observable, Subject } from 'rxjs';
import { FavoriteModel } from '../model/favorite';
import { UserModel } from '../model/user';

@Component({
  selector: 'app-pinboard',
  templateUrl: './pinboard.component.html',
  styleUrls: ['./pinboard.component.css']
})
export class PinboardComponent implements OnInit {

  pins: PinModel[] = []
  favorite: FavoriteModel | undefined;
  name: string | undefined;
  user: UserModel | undefined;
  delete = new Subject<number>();
  reload = new Subject<boolean>();

  constructor(private pinService: PinService,
              private securityService: SecurityService,
              private route: ActivatedRoute,
              private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.loadAllPins()
    this.getUser()
  }

  loadAllPins() {
    this.pinService.getAllPins()
      .subscribe((result: PinModel[]) => {
        console.log('Result ', result);
        this.pins = result;
      });
    }

    getUserInfo() {
        this.securityService.getUserInfo()
        .subscribe((result) => {
          this.name = result
        })
    }

    getUser() {
      this.securityService.getUser().subscribe((result) => {
        console.log(result)
        this.user = result;
      })
    }

    logout()
    {
      this.securityService.logout() .subscribe(() => {
        this.securityService.removeToken();
        this.router.navigate(['/login']);
      });
    }
}
