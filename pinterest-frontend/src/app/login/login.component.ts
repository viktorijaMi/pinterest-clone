import { Component, OnInit } from '@angular/core';
import { SecurityService } from '../services/security.service'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private securityService: SecurityService) { }

  ngOnInit(): void {
  }
}
