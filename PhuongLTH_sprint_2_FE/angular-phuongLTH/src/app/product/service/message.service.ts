import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {ProductView} from '../../dto/product/product-view';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private resultSearch: BehaviorSubject<any> = new BehaviorSubject<any>('');
  private numberCart: BehaviorSubject<any> = new BehaviorSubject<any>('');
  private message: BehaviorSubject<any> = new BehaviorSubject<any>('');

  constructor() {
  }

  setMessageNumber(value: string): void {
    this.numberCart.next(value);
  }

  getMessageNumber(): Observable<any> {
    return this.numberCart.asObservable();
  }

  setMessageSearch(value: string): void {
    this.resultSearch.next(value);
  }

  getMessageSearch(): Observable<any> {
    return this.resultSearch.asObservable();
  }
  sendMessage(productView: ProductView): void {
    this.message.next(productView);
  }

  getMessage(): Observable<any> {
    return this.message.asObservable();
  }
}
