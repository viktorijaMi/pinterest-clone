import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
    url: ''
  });

  reload = new Subject<boolean>();

  constructor(private service: PinService,
    private formBuilder: FormBuilder,
    private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.service.addPin(this.checkoutForm.value['description'], this.checkoutForm.value['url']).subscribe(
      (result) => {
        this.reload.next(false);
      }
    )
    this.checkoutForm.reset();
    this.router.navigate(['/pins']);
  }
}
