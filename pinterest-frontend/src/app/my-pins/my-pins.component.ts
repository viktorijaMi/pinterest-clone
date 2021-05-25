import { Component, OnInit } from '@angular/core';
import { PinService } from '../services/pin.service';
import {PinModel} from '../model/pin'
@Component({
  selector: 'app-my-pins',
  templateUrl: './my-pins.component.html',
  styleUrls: ['./my-pins.component.css']
})
export class MyPinsComponent implements OnInit {

  pins: PinModel[] = []

  constructor(private pinService: PinService) { }

  ngOnInit(): void {
    this.loadMyPins()
  }

  loadMyPins() {
    this.pinService.getMyPins().subscribe(result => {
      console.log("result: ", result)
      this.pins = result
    })
  }
}
