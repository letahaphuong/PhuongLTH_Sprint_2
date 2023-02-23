import {Component, OnInit} from '@angular/core';
import {ProductService} from '../service/product.service';
import {ProductView} from '../../dto/product/product-view';
import {CartView} from '../../dto/product/cart-view';
import {TokenService} from '../../security/service/token.service';

@Component({
  selector: 'app-product-cart',
  templateUrl: './product-cart.component.html',
  styleUrls: ['./product-cart.component.css']
})
export class ProductCartComponent implements OnInit {

  cartList: CartView[] | undefined;
  constructor(
    private productService: ProductService,
    private tokenService: TokenService
  ) {

  }

  ngOnInit(): void {
  }

}
