import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { PinModel } from '../model/pin';
import { FavoriteModel } from '../model/favorite';

@Injectable({
  providedIn: 'root'
})
export class PinService {

  constructor(private http: HttpClient) { }

  getAllPins(): Observable<PinModel[]> {
    return this.http.get<PinModel[]>("/api/pins");
  }

  getPinById(id: number): Observable<PinModel> {
    return this.http.get<PinModel>(`/api/pins/${id}`)
  }

  addPin(description: String, url: String, username: String): Observable<PinModel> {
    return this.http.post<PinModel>("/api/pins/add", {
      "description": description,
      "url": url,
      "username": username
    })
  }

  deletePin(id: number) {
    return this.http.delete(`api/pins/delete/${id}`)
  }

  getAllFavoritesByPinId(id: number) : Observable<FavoriteModel[]> {
    return this.http.get<FavoriteModel[]>(`/api/favorites/${id}`)
  }

  favoritePin(pinId: number, username: string) {
    console.log("pinId: ", pinId)
    console.log("username: ", username)
    return this.http.post(`/api/favorites/${pinId}?username=${username}`, {})
  }
}
