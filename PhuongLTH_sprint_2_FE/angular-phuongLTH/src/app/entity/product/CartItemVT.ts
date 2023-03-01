import {ProductView} from '../../dto/product/product-view';

export class CartItemVT {
  productId: number;
  productName: string;
  productUrl: string;
  productMemory: string;
  productPrice: number;
  qty: number;
  productNameCategory: string;

  constructor(productView: ProductView) {
    this.productId = productView.idProduct;
    this.productName = productView.nameProduct;
    this.productPrice = productView.price;
    this.qty = productView.quantity;
    this.productUrl = productView.url;
    this.productMemory = productView.memory;
    this.productNameCategory = productView.nameCategory;
  }
}
