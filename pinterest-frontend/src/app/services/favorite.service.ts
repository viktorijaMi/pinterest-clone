import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { FavoriteModel } from '../model/favorite'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FavoriteService {

  constructor(private http: HttpClient) { }

  getAllFavoritesByPinId(id: number) : Observable<FavoriteModel[]> {
    return this.http.get<FavoriteModel[]>(`/api/favorites/${id}`)
  }

  favoritePin(pinId: number) {
    return this.http.post(`/api/favorites/${pinId}`, {})
  }
}
