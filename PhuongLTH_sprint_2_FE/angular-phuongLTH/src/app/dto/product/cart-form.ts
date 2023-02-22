import {ProductService} from '../../product/service/product.service';

export interface CartForm {
  idProductOrder: number;
  quantityOrder: number;
  price: number;
  product: ProductService;
}
