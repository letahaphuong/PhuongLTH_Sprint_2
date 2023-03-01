import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {ProductView} from '../../dto/product/product-view';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  message = new Subject();

  constructor() {
  }

  sendMessage(productView: ProductView): void {
    this.message.next(productView);
  }

  getMessage(): Observable<any> {
    return this.message.asObservable();
  }
}
