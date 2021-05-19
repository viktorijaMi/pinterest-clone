import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PinService } from '../services/pin.service';
import { Subject } from 'rxjs';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-add-pin',
  templateUrl: './add-pin.component.html',
  styleUrls: ['./add-pin.component.css']
})
export class AddPinComponent implements OnInit {

  items = this.service.getAllPins();

  checkoutForm = this.formBuilder.group({
    description: '',
    url: '',
    username: ''
  });

  reload = new Subject<boolean>();

  constructor(private service: PinService,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.service.addPin(this.checkoutForm.value['description'], this.checkoutForm.value['url'], this.checkoutForm.value['username']).subscribe(
      (result) => {
        console.log("Added pin ", result);
        this.reload.next(false);
      }
    )
    this.checkoutForm.reset();
  }
}
