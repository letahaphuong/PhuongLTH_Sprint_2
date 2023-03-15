import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
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
import {CartView} from '../../dto/product/cart-view';
import {GetCartTotalPrice} from '../../dto/product/get-cart-total-price';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
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

  checkProduct = 0;
  error: any = {
    message: 'quantity not enough'
  };
  success: any = {
    message: 'ok'
  };
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
  checkLogin = false;
  cartList: CartView[] | undefined;
  getCartTotalPrice: GetCartTotalPrice | undefined;
  productDetail1: ProductView | undefined;

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
      this.checkRoles = this.tokenService.getAnony();
    }
    if (this.tokenService.getId() != null) {
      this.idAccount = this.tokenService.getId();
      this.customerService.findIdCustomerByIdAccount(this.idAccount).subscribe(data => {
        this.customer = data;
      });
    } else if (this.tokenService.getIdSession() != null) {
      this.idAccount = this.tokenService.getIdSession();
      this.customerService.findIdCustomerByIdAccount(this.idAccount).subscribe(data => {
        this.customer = data;
      });
    }
    this.cartForm = new FormGroup({
      idProductOrder: new FormControl(),
      quantityOrder: new FormControl(this.quantity),
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
          this.checkProduct = data.quantity;
        }, error => {

        }, () => {

        });
      }
    });
  }

  ngOnInit(): void {
    this.calculate(this.max, this.ratingValue);
  }

  calculate(max: number, ratingValue: number): void {
    this.ratingUnits = Array.from({length: max},
      (_, index) => ({
        value: index + 1,
        active: index < ratingValue
      }));
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
    this.idAccount = this.tokenService.getId();
    this.customerService.findIdCustomerByIdAccount(this.idAccount).subscribe(data => {
      this.idCustomer = data.idCustomer;
      console.log(this.idCustomer);
      this.productService.getItemForCartByIdCustomer(this.idCustomer).subscribe(next => {
        this.cartList = next;
        this.ngOnInit();
        this.messageService.setMessageNumber(String(this.cartList.length));
        this.ngOnInit();
      });
      if (this.idCustomer != null) {
        this.productService.getCartTotalPrice(+this.idCustomer).subscribe(total => {
          this.getCartTotalPrice = total;
        });
      }
    });
    this.cartForm = new FormGroup({
      idProductOrder: new FormControl(),
      quantityOrder: new FormControl(this.quantity),
      price: new FormControl(this.getPrice),
      product: new FormControl(this.product),
      customer: new FormControl(this.customer)
    });
    this.productService.createCart(this.cartForm.value).subscribe(data => {
      if (JSON.stringify(data) === JSON.stringify(this.error)) {
        this.toast.error('Số lượng trong kho không đủ.');
      } else if (JSON.stringify(data) === JSON.stringify(this.success)) {
        this.toast.success('Thêm giỏ hàng thành công.');

      }
    }, error => {
      if (error.status === 404) {
        this.toast.warning('Số lượng trong kho không còn đủ hàng.');
      } else {
        this.toast.error('Thêm giỏ hàng không thành công.');
      }
    });
  }

  cantAcctive(): void {
    this.toast.warning('Vui lòng đăng nhập để sử dụng chức năng.');
  }

  addCart(): void {
    if (this.cartForm) {
      this.messageService.sendMessage(this.cartForm.value);
    }
  }
}
