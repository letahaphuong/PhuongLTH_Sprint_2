import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment.prod';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CustomerView} from '../../dto/customer-view';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private API_CUSTOMER = environment.API_CUSTOMER;

  constructor(private http: HttpClient) {
  }

  showAllList(request: any): Observable<CustomerView[]> {
    const params = request;
    return this.http.get<CustomerView[]>(this.API_CUSTOMER, {params});
  }

  search(name: string, email: string, request: any): Observable<any> {
    const params = request;
    const url = this.API_CUSTOMER +
      '?name=' + name +
      '&email=' + email;
    console.log(url);
    return this.http.get<any>(url, {params});
  }

  findCustomerById(id: any): Observable<any> {
    return this.http.get<any>(this.API_CUSTOMER + '/' + id);
  }
}
