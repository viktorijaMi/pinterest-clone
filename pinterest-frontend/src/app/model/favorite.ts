import { PinModel } from './pin';
import { UserModel } from './user'

export interface FavoriteModel {
  id: number,
  pin: PinModel,
  user: UserModel
}
