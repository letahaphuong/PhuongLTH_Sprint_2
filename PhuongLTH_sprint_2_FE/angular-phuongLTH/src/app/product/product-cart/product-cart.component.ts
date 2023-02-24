import {Component, OnInit} from '@angular/core';
import {ProductService} from '../service/product.service';
import {CartView} from '../../dto/product/cart-view';
import {TokenService} from '../../security/service/token.service';
import {CustomerService} from '../../customer/service/customer.service';
import {GetCartTotalPrice} from "../../dto/product/get-cart-total-price";


@Component({
  selector: 'app-product-cart',
  templateUrl: './product-cart.component.html',
  styleUrls: ['./product-cart.component.css']
})
export class ProductCartComponent implements OnInit {

  cartList: CartView[] | undefined;
  idAccount: string | null | undefined;
  idCustomer: number | undefined;
  temp: CartView | undefined;
  getCartTotalPrice: GetCartTotalPrice | undefined;

  constructor(
    private productService: ProductService,
    private tokenService: TokenService,
    private customerService: CustomerService
  ) {
  }

  ngOnInit(): void {
    this.idAccount = this.tokenService.getId();
    this.customerService.findIdCustomerByIdAccount(this.idAccount).subscribe(data => {
      console.log('id nè', data);
      this.idCustomer = data.idCustomer;
      console.log('this.idCustomer', this.idCustomer);
      this.productService.getItemForCartByIdCustomer(this.idCustomer).subscribe(next => {
        this.cartList = next;
        console.log(this.cartList);
      });
      if (this.idCustomer != null) {
        this.productService.getCartTotalPrice(this.idCustomer).subscribe(total => {
          this.getCartTotalPrice = total;
        });
      }
    });
    console.log(this.idCustomer, 'aaaaaaaaaaa');
  }

  reload(): void {
    this.ngOnInit();
  }
}
