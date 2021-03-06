import { Component, Input, OnInit } from '@angular/core';
import { FavoriteModel } from '../model/favorite';
import { PinModel } from '../model/pin';
import { FavoriteService } from '../services/favorite.service';
import { PinService } from '../services/pin.service';
import { SecurityService } from '../services/security.service'
@Component({
  selector: 'app-pin-details',
  templateUrl: './pin-details.component.html',
  styleUrls: ['./pin-details.component.css']
})
export class PinDetailsComponent implements OnInit {

  @Input() pin!: PinModel;

  favorites: FavoriteModel[] = []

  constructor(private pinService: PinService,
    private favoriteService: FavoriteService,
    public securityService: SecurityService) { }

  ngOnInit(): void {
    this.loadPin()
  }

  loadPin() {
    this.pinService.getPinById(this.pin.id).subscribe((result) => {
      this.pin = result
    })
  }

  onFavorite() {
    this.favoriteService.favoritePin(this.pin?.id).subscribe(() => {
      this.loadPin()
    })
  }
}
