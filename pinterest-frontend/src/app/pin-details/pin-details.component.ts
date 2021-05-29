import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FavoriteModel } from '../model/favorite';
import { PinModel } from '../model/pin';
import { PinService } from '../services/pin.service';
import { SecurityService } from '../services/security.service';

@Component({
  selector: 'app-pin-details',
  templateUrl: './pin-details.component.html',
  styleUrls: ['./pin-details.component.css']
})
export class PinDetailsComponent implements OnInit {

  @Input() pin!: PinModel;
  @Input() callbackFunction!: () => void;

  favorites: FavoriteModel[] = []
  constructor(private service: PinService,
              private route: ActivatedRoute,
              private router: Router,
              private securityService: SecurityService) { }

  ngOnInit(): void {
    this.loadPin()
  }

  loadFavorites(){
    this.service.getAllFavoritesByPinId(this.pin.id).subscribe((result) => {
      this.favorites = result
    })
  }

  loadPin() {
    this.service.getPinById(this.pin.id).subscribe((result) => {
      this.pin = result
    })
  }

  onFavorite() {
    this.service.favoritePin(this.pin?.id).subscribe(() => {
      this.loadPin()
    })
  }
}
