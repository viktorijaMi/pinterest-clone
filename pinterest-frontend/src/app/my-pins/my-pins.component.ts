import { Component, OnInit } from '@angular/core';
import { PinService } from '../services/pin.service';
import {PinModel} from '../model/pin'
import { ActivatedRoute, Router } from '@angular/router';
import { SecurityService } from '../services/security.service';


@Component({
  selector: 'app-my-pins',
  templateUrl: './my-pins.component.html',
  styleUrls: ['./my-pins.component.css']
})
export class MyPinsComponent implements OnInit {

  pins: PinModel[] = []

  constructor(private pinService: PinService,
    private route: ActivatedRoute,
    private router: Router,
    private securityService: SecurityService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.securityService.updateToken(params['accessToken'])
    })
    this.loadMyPins()
  }

  loadMyPins() {
    this.pinService.getMyPins().subscribe(result => {
      console.log("result: ", result)
      this.pins = result
    })
  }
}
