import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment.prod';
import {HttpClient} from '@angular/common/http';
import {OrderInfo} from '../../dto/order-info/OrderInfo';
import {Observable} from 'rxjs';
import {OrderHistory} from '../../dto/order-info/OrderHistory';
import {OrderDetailHistory} from "../../dto/product/OrderDetailHistory";

@Injectable({
  providedIn: 'root'
})
export class OrderInfoService {
  private API_ORDER_INFO = environment.API_ORDER_INFO;

  constructor(
    private http: HttpClient
  ) {
  }

  getInfoOrder(id: any): Observable<OrderInfo[]> {
    return this.http.get<OrderInfo[]>(this.API_ORDER_INFO + '/' + id);
  }

  getHistory(id: any): Observable<OrderHistory[]> {
    return this.http.get<OrderHistory[]>(this.API_ORDER_INFO + '/history/' + id);
  }

  getOrderDetailHistory(id: any): Observable<OrderDetailHistory[]> {
    return this.http.get<OrderDetailHistory[]>(this.API_ORDER_INFO + '/order-detail-history/' + id);
  }
}
