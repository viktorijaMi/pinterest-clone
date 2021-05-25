import { NgModule } from '@angular/core';
import { Route, RouterModule, Routes } from '@angular/router';
import { AddPinComponent } from './add-pin/add-pin.component';
import { AuthGuard } from './auth-guard';
import { PinDetailsComponent } from './pin-details/pin-details.component';
import { PinboardComponent } from './pinboard/pinboard.component';
import {LoginComponent} from './login/login.component'
import { CallbackComponent } from './callback/callback.component';

const routes: Route[]= [
  { path: 'dashboard', component: PinboardComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'callback', component: CallbackComponent },
  { path: 'pins', component: PinDetailsComponent },
  { path: 'add-pin', component: AddPinComponent},
  { path: '', redirectTo:'dashboard' , pathMatch: 'full'},
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
