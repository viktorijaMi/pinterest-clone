import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PinService } from '../services/pin.service';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-add-pin',
  templateUrl: './add-pin.component.html',
  styleUrls: ['./add-pin.component.css']
})
export class AddPinComponent implements OnInit {

  formError: string | undefined

  checkoutForm = this.formBuilder.group({
    description: '',
    url: ''
  });

  constructor(private service: PinService,
    private formBuilder: FormBuilder,
    private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    this.formError = undefined
    this.service.addPin(this.checkoutForm.value['description'], this.checkoutForm.value['url']).subscribe(
      (result) => {
        this.checkoutForm.reset();
        this.router.navigate(['/my-pins']);
      },
      (error) => {
        if (error.error) {
          console.log("Error message: ", error.error.message)
          this.formError = error.error.message;
        }
      }
    )
  }
}
