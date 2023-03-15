import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../product/service/product.service';
import {FormControl, FormGroup} from '@angular/forms';
import {render} from 'creditcardpayments/creditCardPayments';
import {ToastrService} from 'ngx-toastr';
import {TokenService} from '../service/token.service';
import {CustomerService} from '../../customer/service/customer.service';
import {GetCartTotalPrice} from '../../dto/product/get-cart-total-price';
import {SecurityService} from '../service/security.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Customer} from '../../entity/customer/customer';
// @ts-ignore
import * as CryptoJS from 'crypto-js';

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
  name = '';
  address = '';
  phone = '';
  status = false;
  customer: Customer | undefined;
  email = '';
  decPassword = '123123';
  checkPayment = false;

  constructor(private productService: ProductService,
              private toast: ToastrService,
              private tokenService: TokenService,
              private customerService: CustomerService,
              private securityService: SecurityService,
              private router: Router
  ) {
    // @ts-ignore
    this.email = this.tokenService.getEmail();
    const idCustomer2 = this.tokenService.getIdCustomer();

    this.customerService.findCustomerById(idCustomer2).subscribe(data => {
      this.ordersForm.patchValue(data);
      this.name = data.name;
      this.address = data.address;
      this.phone = data.phone;
    });
    this.idAccount = this.tokenService.getId();
    this.customerService.findIdCustomerByIdAccount(this.idAccount).subscribe(data => {
      this.customer = data;
    });
    this.ordersForm = new FormGroup({
      idOrder: new FormControl(),
      codeOrder: new FormControl(),
      paymentStatus: new FormControl(this.status),
      address: new FormControl(),
      phone: new FormControl(this.phone),
      customer: new FormControl(this.customer),
      name: new FormControl(),
      email: new FormControl(this.email)
    });
  }

  ngOnInit(): void {
    this.idAccount = this.tokenService.getId();
    this.customerService.findIdCustomerByIdAccount(this.idAccount).subscribe(data => {
      this.idCustomer = data.idCustomer;
      if (this.idCustomer != null) {
        this.productService.getCartTotalPrice(this.idCustomer).subscribe(total => {
          this.getCartTotalPrice = total;
          // @ts-ignore
          const value1 = (this.getCartTotalPrice?.cartTotalPrice / 24000).toFixed(2);
          render(
            {
              id: '#myPaypalButtons',
              currency: 'USD',
              value: value1,
              onApprove: (details) => {
                this.status = true;
                this.checkPayment = true;
                this.router.navigateByUrl('/order/success');
                if (details.error) {
                  this.toast.error('Thanh toán không thành công.');
                }
                this.ordersForm = new FormGroup({
                  idOrder: new FormControl(),
                  codeOrder: new FormControl(),
                  paymentStatus: new FormControl(this.status),
                  address: new FormControl(this.address),
                  phone: new FormControl(this.phone),
                  customer: new FormControl(this.customer),
                  name: new FormControl(this.name),
                  email: new FormControl(this.email),
                });
                const inFoOrder = this.ordersForm.value;
                // tslint:disable-next-line:no-shadowed-variable
                this.securityService.infoOrder(inFoOrder).subscribe((data: any) => {
                  if (data.status === 201) {
                    this.toast.success('Cảm ơn Anh/ Chị :' + inFoOrder.name + ' đã đặt hàng. Vui lòng check' +
                      ' mail để kiểm tra thông tin');
                    this.router.navigateByUrl('/order/success');
                  }
                }, error => {
                  this.toast.error('Đặt hàng không thành công. Vui lòng kiểm tra lại thông tin.');
                });
              }
            }
          );
        });
      }
    });
  }

  ordersInfo(): void {
    this.checkPayment = true;
    const inFoOrder = this.ordersForm.value;
    this.securityService.infoOrder(inFoOrder).subscribe(data => {
      this.toast.success('Cảm ơn Anh/ Chị' + inFoOrder.name + ' đã đặt hàng. Vui lòng check mail để kiểm tra thông tin.', 'Thông báo', {
        timeOut: 3000,
        extendedTimeOut: 1500
      });
      this.router.navigateByUrl('/order/cod');
      this.checkPayment = false;
    }, error => {
      this.toast.error('Đặt hàng không thành công. Vui lòng kiểm tra lại thông tin.');
      this.checkPayment = false;
    });
  }
}
