import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment.prod';
import {Observable} from 'rxjs';
import {ProductCreate} from '../../entity/product/ProductCreate';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private API_PRODUCT = environment.API_PRODUCT;

  constructor(private http: HttpClient) {
  }

  getProductById(idProduct: number): Observable<any> {
    console.log('ảnh nè' + idProduct);
    return this.http.get(this.API_PRODUCT + '/' + idProduct);
  }

  createProduct(productForm: ProductCreate): Observable<ProductCreate> {
    console.log('aaa', productForm.url);
    return this.http.post<ProductCreate>(this.API_PRODUCT, productForm);
  }
}
