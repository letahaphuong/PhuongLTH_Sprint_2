import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {ProductView} from '../../dto/product/product-view';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  message = new Subject();
  private resultSearch: BehaviorSubject<any> = new BehaviorSubject<any>('');

  constructor() {
  }

  sendMessage(productView: ProductView): void {
    this.message.next(productView);
  }

  setMessageSearch(value: string): void {
    this.resultSearch.next(value);
  }

  getMessageSearch(): Observable<any> {
    return this.resultSearch.asObservable();
  }

  getMessage(): Observable<any> {
    return this.message.asObservable();
  }
}
