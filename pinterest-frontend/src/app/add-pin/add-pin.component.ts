import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { PinModel } from '../model/pin';
import { PinService } from '../services/pin.service';
import { map, mergeMap, switchMap } from 'rxjs/operators';
import { PinDTO } from '../model/pinDto';
import { of } from 'rxjs';

@Component({
  selector: 'app-add-pin',
  templateUrl: './add-pin.component.html',
  styleUrls: ['./add-pin.component.css']
})
export class AddPinComponent implements OnInit {

  pin : PinModel | undefined;
  pinDto: PinDTO | any;

  constructor(private service: PinService, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log("on submit")
    this.route.params.subscribe((params) => {
      this.pinDto = {
        "description" : params['description'],
        "url" : params['url'],
        "username" : params['username']
      }
    })
    console.log("pinDto: ", this.pinDto)
    this.service.addPin(this.pinDto)
  }
}
