import {Component, EventEmitter, Input, OnInit, Output, SimpleChanges} from '@angular/core';
import {RatingUnit} from '../../entity/rating/rating-unit';
import {ProductService} from '../service/product.service';
import {ProductView} from '../../dto/product/product-view';
import {Title} from '@angular/platform-browser';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {FormControl, FormGroup} from '@angular/forms';
import {ProductCreate} from '../../entity/product/ProductCreate';
import {CustomerService} from '../../customer/service/customer.service';
import {TokenService} from '../../security/service/token.service';
import {Customer} from '../../entity/customer/customer';
// @ts-ignore
import * as CryptoJS from 'crypto-js';
import {MessageService} from '../service/message.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  cartForm: FormGroup = new FormGroup({});
  productDetail: ProductView | undefined;
  idProduct = 0;
  getPrice: number | undefined;
  product: ProductCreate | undefined;
  idAccount: string | null | undefined;
  idCustomer: string | null | undefined;
  customer: Customer | undefined;
  checkRoles: string | null | undefined;
  decPassword = '123123';
  checkLogin = false;;

  constructor(
    private productService: ProductService,
    private title: Title,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toast: ToastrService,
    private customerService: CustomerService,
    private tokenService: TokenService,
    private messageService: MessageService
  ) {
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
      // @ts-ignore
      this.checkRoles = CryptoJS.AES.decrypt(this.tokenService.getAnony().toString(), this.decPassword?.trim()).toString(CryptoJS.enc.Utf8);
    }
    if (this.tokenService.getId() != null) {
      this.idAccount = this.tokenService.getId();
      this.customerService.findIdCustomerByIdAccount(this.idAccount).subscribe(data => {
        this.customer = data;
        console.log(this.customer, 'loadding....');
      });
    } else if (this.tokenService.getIdSession() != null) {
      this.idAccount = this.tokenService.getIdSession();
      this.customerService.findIdCustomerByIdAccount(this.idAccount).subscribe(data => {
        this.customer = data;
      });
    }
    // this.customerService.findCustomerById(this.idCustomer).subscribe(data => {
    //   this.customer = data;
    // });
    this.cartForm = new FormGroup({
      idProductOrder: new FormControl(),
      quantityOrder: new FormControl(),
      price: new FormControl(),
      product: new FormControl(),
      customer: new FormControl()
    });
    this.activatedRoute.paramMap.subscribe(param => {
      const idProduct = param.get('idProduct');
      if (idProduct !== null) {
        this.idProduct = Number(idProduct);
        this.productService.getProductById(this.idProduct).subscribe(data => {
          this.cartForm.patchValue(data);
          this.getPrice = data.price;
          this.product = data;
          this.productDetail = data;
          console.log(data);
        }, error => {

        }, () => {

        });
      }
    });
  }

  @Input()
  max = 10;
  @Input()
  ratingValue = 5;
  @Input()
  showRatingValue = true;

  @Output()
  rateChange = new EventEmitter<number>();

  ratingUnits: Array<RatingUnit> = [];
  quantity = 1;
  i = 1;

  calculate(max: number, ratingValue: number): void {
    this.ratingUnits = Array.from({length: max},
      (_, index) => ({
        value: index + 1,
        active: index < ratingValue
      }));
  }

  ngOnInit(): void {
    this.calculate(this.max, this.ratingValue);
  }

  minus = () => {
    if (this.i !== 1) {
      this.i--;
      this.quantity = this.i;
    }
  }

  plus = () => {
    if (this.i !== 100) {
      this.i++;
      this.quantity = this.i;
    }
  }


  select(index: number): void {
    this.toast.success('Cảm ơn bạn đã đánh giá.');
    this.ratingValue = index + 1;
    this.ratingUnits.forEach((item, idx) => item.active = idx < this.ratingValue);
    this.rateChange.emit(this.ratingValue);
  }

  enter(index: number): void {
    this.ratingUnits.forEach((item, idx) => item.active = idx <= index);
  }

  reset(): void {
    this.ratingUnits.forEach((item, idx) => item.active = idx < this.ratingValue);
  }

  createCart(): void {
    this.cartForm = new FormGroup({
      idProductOrder: new FormControl(),
      quantityOrder: new FormControl(this.quantity),
      price: new FormControl(this.getPrice),
      product: new FormControl(this.product),
      customer: new FormControl(this.customer)
    });
    console.log(this.cartForm.value);
    this.productService.createCart(this.cartForm.value).subscribe(data => {
      this.toast.success('Thêm giỏ hàng thành công');
    }, error => {
      this.toast.error('Thêm giỏ hàng không thành công');
    });
  }

  cantAcctive(): void {
    this.toast.warning('Không được dùng tài khoản ADMIN đặt hàng');
  }

  addCart(): void {
    if (this.productDetail) {
      this.messageService.sendMessage(this.productDetail);
    }
  }
}
