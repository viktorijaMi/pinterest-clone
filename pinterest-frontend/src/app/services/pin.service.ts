import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { PinModel } from '../model/pin';
import { PinDTO } from '../model/pinDto';

@Injectable({
  providedIn: 'root'
})
export class PinService {

  constructor(private http: HttpClient) { }

  getAllPins() : Observable<PinModel[]> {
    console.log("in service")
    return this.http.get<PinModel[]>("/api/home");
  }

  getPinById(id: number) : Observable<PinModel> {
    return this.http.get<PinModel>(`/api/${id}`)
  }

  addPin(pin: PinDTO) : Observable<PinModel> {
    console.log("adding new pin")
    return this.http.post<PinModel>("/api/add", pin)
  }
}
