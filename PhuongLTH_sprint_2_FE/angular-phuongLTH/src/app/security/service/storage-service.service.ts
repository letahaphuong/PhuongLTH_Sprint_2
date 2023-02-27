import { Injectable } from '@angular/core';
import {CartItemModel} from '../../entity/cart-item-model';

@Injectable({
  providedIn: 'root'
})
export class StorageServiceService {

  constructor() { }

  existsCart(): boolean {
    return localStorage.getItem('cart') != null;
  }

  setCart(cart: CartItemModel[]): void {
    localStorage.setItem('cart', JSON.stringify(cart));
  }

  getCart(): CartItemModel[] {
    // @ts-ignore
    return JSON.parse(localStorage.getItem('cart'));
  }

  clear(): void {
    localStorage.removeItem('cart');
  }
}
