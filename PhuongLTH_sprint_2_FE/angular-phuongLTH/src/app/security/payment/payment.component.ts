import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../product/service/product.service';
import {FormControl, FormGroup} from '@angular/forms';
import {render} from 'creditcardpayments/creditCardPayments';
import {ToastrService} from 'ngx-toastr';
import {TokenService} from '../service/token.service';
import {CustomerService} from '../../customer/service/customer.service';
import {GetCartTotalPrice} from '../../dto/product/get-cart-total-price';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  ordersForm: FormGroup = new FormGroup({});
  idAccount: string | null | undefined;
  idCustomer: number | undefined;
  getCartTotalPrice: GetCartTotalPrice | undefined = {};

  constructor(private productService: ProductService,
              private toast: ToastrService,
              private tokenService: TokenService,
              private customerService: CustomerService) {
    this.ordersForm = new FormGroup({
      idOrder: new FormControl(),
      codeOrder: new FormControl(),
      paymentStatus: new FormControl(),
      shippingAddress: new FormControl(),
      orderPhoneNumber: new FormControl(),
      customer: new FormControl()
    });
  }

  ngOnInit(): void {
    this.idAccount = this.tokenService.getId();
    console.log(this.tokenService.getId(), 'aaaaaaaaaaa');
    this.customerService.findIdCustomerByIdAccount(this.idAccount).subscribe(data => {
      console.log('id nè', data);
      this.idCustomer = data.idCustomer;
      console.log('this.idCustomer', this.idCustomer);
      if (this.idCustomer != null) {
        this.productService.getCartTotalPrice(this.idCustomer).subscribe(total => {
          this.getCartTotalPrice = total;
          console.log(this.getCartTotalPrice?.cartTotalPrice, 'aaaaaaaaaaa');
          // @ts-ignore
          const value1 = (this.getCartTotalPrice?.cartTotalPrice / 90000).toFixed(2);
          render(
            {
              id: '#myPaypalButtons',
              currency: 'VNĐ',
              value: value1,
              onApprove: (details) => {
                if (details.error) {
                  this.toast.error('Thanh toán không thành công');
                }
                this.toast.success('Thanh toán thành công.');
              }
            }
          );
        });
      }
    });
    // render(
    //   {
    //     id: '#myPaypalButtons',
    //     currency: 'VNĐ',
    //     value: '',
    //     onApprove: (details) => {
    //       this.toast.success('Thanh toán thành công.');
    //     }
    //   }
    // );
  }

}
