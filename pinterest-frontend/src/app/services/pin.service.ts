import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { PinModel } from '../model/pin';
import { FavoriteModel } from '../model/favorite';

@Injectable({
  providedIn: 'root'
})
export class PinService {

  private baseUrl = 'http://localhost:9090';
  constructor(private http: HttpClient) { }

  getAllPins(): Observable<PinModel[]> {
    return this.http.get<PinModel[]>("/api/pins");
  }

  getPinById(id: number): Observable<PinModel> {
    return this.http.get<PinModel>(`/api/pins/${id}`)
  }

  getMyPins() : Observable<PinModel[]> {
    return this.http.get<PinModel[]>("/api/pins/my-pins");
  }


  addPin(description: String, url: String): Observable<PinModel> {
    console.log("in add pin")
    return this.http.post<PinModel>("/api/pins/add", {
      "description": description,
      "url": url
    })
  }

  deletePin(id: number) {
    return this.http.delete(`api/pins/delete/${id}`)
  }

  getAllFavoritesByPinId(id: number) : Observable<FavoriteModel[]> {
    return this.http.get<FavoriteModel[]>(`/api/favorites/${id}`)
  }

  favoritePin(pinId: number) {
    console.log("pinId: ", pinId)
    return this.http.post(`/api/favorites/${pinId}`, {})
  }
}
