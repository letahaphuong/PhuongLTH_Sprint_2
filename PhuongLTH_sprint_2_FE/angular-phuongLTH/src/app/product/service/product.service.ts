import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment.prod';
import {Observable} from 'rxjs';
import {ProductCreate} from '../../entity/product/ProductCreate';
import {Category} from '../../dto/product/category';
import {CartForm} from '../../dto/product/cart-form';
import {CartView} from '../../dto/product/cart-view';
import {Orders} from "../../dto/product/orders";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private API_PRODUCT = environment.API_PRODUCT;
  private API_CATEGORY = environment.API_CATEGORY;

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

  getAllCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(this.API_CATEGORY);
  }

  createCart(cartForm: CartForm): Observable<any> {
    return this.http.post<any>(this.API_PRODUCT + '/cart/create', cartForm);
  }

  getItemForCartByIdCustomer(id: any): Observable<CartView[]> {
    console.log('id service nè', id);
    return this.http.get<CartView[]>(this.API_PRODUCT + '/cart/object/' + id);
  }

  deleteProduct(id: number): Observable<any> {
    return this.http.delete<any>(this.API_PRODUCT + '/delete/' + id);
  }

  editProduct(productEditForm: ProductCreate): Observable<ProductCreate> {
    return this.http.patch<ProductCreate>(this.API_PRODUCT + '/' + productEditForm.idProduct, productEditForm);
  }

  deleteCart(id: number): Observable<any> {
    return this.http.delete<any>(this.API_PRODUCT + '/cart/' + id);
  }

  getCartTotalPrice(id: number): Observable<any> {
    return this.http.get<any>(this.API_PRODUCT + '/cart/total-price/' + id);
  }

  createOrder(orderForm: Orders): Observable<any> {
    return this.http.post<any>(this.API_PRODUCT + '/order/create', orderForm);
  }
}
