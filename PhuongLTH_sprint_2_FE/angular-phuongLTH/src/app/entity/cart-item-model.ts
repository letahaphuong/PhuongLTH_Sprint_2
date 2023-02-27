import {ProductCreate} from './product/ProductCreate';

export class CartItemModel {
  productId: number;
  productName: string;
  productPrice: number;
  qty: number;

  constructor(product: ProductCreate) {
    this.productId = product.idProduct;
    this.productName = product.nameProduct;
    this.productPrice = product.price;
    this.qty = 1;
  }
}
