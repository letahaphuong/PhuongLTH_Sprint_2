import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment.prod';
import {HttpClient} from '@angular/common/http';
import {Title} from '@angular/platform-browser';
import {Observable} from 'rxjs';
import {CustomerView} from '../../dto/customer-view';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  private API_PRODUCT_LIST = environment.API_PRODUCT;

  constructor(
    private http: HttpClient,
  ) {
  }

  showAllList(request: any): Observable<CustomerView[]> {
    const params = request;
    return this.http.get<CustomerView[]>(this.API_PRODUCT_LIST, {params});
  }

  search(nameCategory: string, nameProduct: string, price: string, request: any): Observable<any> {
    const params = request;
    const url = this.API_PRODUCT_LIST +
      '?nameCategory=' + nameCategory +
      '&nameProduct=' + nameProduct +
      '&price=' + price;
    console.log(url);
    return this.http.get<any>(url, {params});
  }

  searchs(searchs: string, request: any): Observable<any> {
    const params = request;
    const url = this.API_PRODUCT_LIST + '/home' +
      '?searchs=' + searchs;
    console.log(url);
    return this.http.get<any>(url, {params});
  }
}
