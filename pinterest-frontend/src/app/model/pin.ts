import { UserModel } from "./user";

export interface PinModel {
  id: number,
  url: string,
  description: string,
  favorites: number,
}
