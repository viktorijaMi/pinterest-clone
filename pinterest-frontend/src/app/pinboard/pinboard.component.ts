import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Params } from '@angular/router';
import { PinService } from '../services/pin.service';
import { PinModel } from '../model/pin';
import { Subject } from 'rxjs';
import { mergeMap, switchMap, tap, map } from "rxjs/operators";
@Component({
  selector: 'app-pinboard',
  templateUrl: './pinboard.component.html',
  styleUrls: ['./pinboard.component.css']
})
export class PinboardComponent implements OnInit {

  pins : PinModel[] = []


  constructor(private service: PinService, private route: ActivatedRoute) { }


  ngOnInit(): void {
    // this.route.params.pipe(
    //   switchMap(id => this.service.getAllPins())
    // ).subscribe(
    //   result => this.pins = result
    // )

    console.log("in ng on init")
     this.service.getAllPins()
      .subscribe((result: PinModel[]) => {
          console.log('Result ', result);
          this.pins = result;
      });
  }

}
