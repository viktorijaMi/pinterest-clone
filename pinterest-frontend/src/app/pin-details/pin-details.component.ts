import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, Input, OnInit } from '@angular/core';
import { FavoriteModel } from '../model/favorite';
import { PinModel } from '../model/pin';
import { PinService } from '../services/pin.service';

@Component({
  selector: 'app-pin-details',
  templateUrl: './pin-details.component.html',
  styleUrls: ['./pin-details.component.css']
})
export class PinDetailsComponent implements OnInit {

  @Input() pin!: PinModel;

  favorites: FavoriteModel[] = []
  constructor(private service: PinService) { }

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

  onDelete() {
    this.service.deletePin(this.pin.id).subscribe();
  }

  onFavorite() {
    this.service.favoritePin(this.pin?.id, this.pin?.createdBy.username).subscribe(() => {
      this.loadPin()
    })
  }
}
