import {Component, OnInit} from '@angular/core';
import {ProductService} from '../service/product.service';
import {CartView} from '../../dto/product/cart-view';
import {TokenService} from '../../security/service/token.service';
import {CustomerService} from '../../customer/service/customer.service';
import {GetCartTotalPrice} from '../../dto/product/get-cart-total-price';
import {MessageService} from '../service/message.service';
import {ProductView} from '../../dto/product/product-view';
import {CartItemVT} from '../../entity/product/CartItemVT';


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
  checkLogin = false;

  cartItemVT: CartItemVT[] = [];

  constructor(
    private productService: ProductService,
    private tokenService: TokenService,
    private customerService: CustomerService,
    private messageService: MessageService
  ) {
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
    }
  }

  getItem(): void {
    this.messageService.getMessage().subscribe((productView: ProductView) => {
      const cartItem = new CartItemVT(productView);
      // @ts-ignore
      this.cartItemVT.push(cartItem);
    });
  }

  ngOnInit(): void {
    this.idAccount = this.tokenService.getId();
    this.customerService.findIdCustomerByIdAccount(this.idAccount).subscribe(data => {
      this.idCustomer = data.idCustomer;
      this.productService.getItemForCartByIdCustomer(this.idCustomer).subscribe(next => {
        this.cartList = next;
        this.messageService.setMessageNumber(String(this.cartList.length));
      });
      if (this.idCustomer != null) {
        this.productService.getCartTotalPrice(this.idCustomer).subscribe(total => {
          this.getCartTotalPrice = total;
        });
      }
    });
  }

  reload(): void {
    this.ngOnInit();
  }
}
