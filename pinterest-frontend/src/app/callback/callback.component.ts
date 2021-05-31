import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { SecurityService } from "../services/security.service";

@Component({
  selector: 'app-callback',
  templateUrl: './callback.component.html',
  styleUrls: ['./callback.component.scss']
})
export class CallbackComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private router: Router,
    private securityService: SecurityService) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.securityService.updateToken(params['accessToken'])
    })
    this.router.navigate(["/dashboard"])
  }

}
