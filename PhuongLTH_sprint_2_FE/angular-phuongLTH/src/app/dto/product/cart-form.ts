import {ProductCreate} from '../../entity/product/ProductCreate';
import {Customer} from '../../entity/customer/customer';


export interface CartForm {
  idProductOrder: number;
  quantityOrder: number;
  price: number;
  product: ProductCreate;
  customer: Customer;
}
