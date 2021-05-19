import { FavoriteModel } from "./favorite";
import { UserModel } from "./user";

export interface PinModel {
  id: number,
  url: string,
  description: string,
  numFavorites: number,
  favorites: FavoriteModel[],
  createdBy: UserModel
}
