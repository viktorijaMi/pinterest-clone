import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SecurityService } from '../services/security.service'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private securityService: SecurityService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  onLogin() {
    this.securityService.login()
  }
}
