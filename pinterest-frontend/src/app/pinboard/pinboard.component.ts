import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Params, Router } from '@angular/router';
import { PinService } from '../services/pin.service';
import { PinModel } from '../model/pin';
import { Subject } from 'rxjs';
import { FavoriteModel } from '../model/favorite';

@Component({
  selector: 'app-pinboard',
  templateUrl: './pinboard.component.html',
  styleUrls: ['./pinboard.component.css']
})
export class PinboardComponent implements OnInit {

  pins: PinModel[] = []
  favorite: FavoriteModel | undefined;
  delete = new Subject<number>();
  reload = new Subject<boolean>();

  constructor(private service: PinService, private route: ActivatedRoute, private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.loadAllPins()
  }

  loadAllPins() {
    this.service.getAllPins()
      .subscribe((result: PinModel[]) => {
        console.log('Result ', result);
        this.pins = result;
      });
    }
}
