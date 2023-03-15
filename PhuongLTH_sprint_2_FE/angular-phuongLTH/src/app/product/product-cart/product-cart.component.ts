import {Component, OnInit} from '@angular/core';
import {ProductService} from '../service/product.service';
import {CartView} from '../../dto/product/cart-view';
import {TokenService} from '../../security/service/token.service';
import {CustomerService} from '../../customer/service/customer.service';
import {GetCartTotalPrice} from '../../dto/product/get-cart-total-price';
import {MessageService} from '../service/message.service';
import {CartItemVT} from '../../entity/product/CartItemVT';
import {FormGroup} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';


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
  form: FormGroup = new FormGroup({});
  error: any = {
    message: 'quantity not enough'
  };
  success: any = {
    message: 'ok'
  };

  constructor(
    private productService: ProductService,
    private tokenService: TokenService,
    private customerService: CustomerService,
    private messageService: MessageService,
    private toast: ToastrService
  ) {
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
    }
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

  quantityUpdate(quantity: string, idCart: number): void {
    this.productService.updateQuantity(quantity, idCart).subscribe(data => {
      this.ngOnInit();
      if (JSON.stringify(data) === JSON.stringify(this.error)) {
        console.log(data.message);
        this.toast.error('Số lượng trong kho không đủ.');
      }
    }, error => {
    });
  }

  getItem(): void {
    this.messageService.getMessage().subscribe(data => {
      let exists = false;
      this.cartItemVT.forEach(item => {
        if (item.productId === data.id) {
          exists = true;
          item.qty++;
        }
      });
      if (!exists) {
        const cartItem = new CartItemVT(data);
        this.cartItemVT.push(cartItem);
      }
      // this.total = this.getTotal();
      // @ts-ignore
      sessionStorage.setItem('cart', this.cartItemVT);
    });
  }
}
