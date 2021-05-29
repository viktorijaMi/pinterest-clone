import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { PinModel } from '../model/pin';

@Injectable({
  providedIn: 'root'
})
export class PinService {

  constructor(private http: HttpClient) { }

  getAllPins(): Observable<PinModel[]> {
    return this.http.get<PinModel[]>("/api/pins/all");
  }

  getPinById(id: number): Observable<PinModel> {
    return this.http.get<PinModel>(`/api/pins/${id}`)
  }

  getMyPins() : Observable<PinModel[]> {
    return this.http.get<PinModel[]>("/api/pins/my-pins");
  }

  addPin(description: String, url: String): Observable<PinModel> {
    return this.http.post<PinModel>("/api/pins", {
      "description": description,
      "url": url
    })
  }

  deletePin(id: number) {
    return this.http.delete(`api/pins/delete/${id}`)
  }
}
