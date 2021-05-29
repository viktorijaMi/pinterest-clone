import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PinService } from '../services/pin.service';
import { PinModel } from '../model/pin';

@Component({
  selector: 'app-pinboard',
  templateUrl: './pinboard.component.html',
  styleUrls: ['./pinboard.component.css']
})
export class PinboardComponent implements OnInit {

  pins: PinModel[] = []

  constructor(private pinService: PinService,
              private router: Router) {
    // this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.loadAllPins()
  }

  loadAllPins() {
    this.pinService.getAllPins()
      .subscribe((result: PinModel[]) => {
        this.pins = result;
      });
    }
}
