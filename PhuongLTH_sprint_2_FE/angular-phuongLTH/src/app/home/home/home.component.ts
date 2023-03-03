import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {ProductView} from '../../dto/product/product-view';
import {ProductViewInfoJson} from '../../dto/product/product-view-info-json';
import {ToastrService} from 'ngx-toastr';
import {Title} from '@angular/platform-browser';
import {HomeService} from '../service/home.service';
import {MessageService} from '../../product/service/message.service';
import {CartView} from "../../dto/product/cart-view";
import {GetCartTotalPrice} from "../../dto/product/get-cart-total-price";
import {CartItemVT} from "../../entity/product/CartItemVT";
import {CustomerService} from "../../customer/service/customer.service";
import {ProductService} from "../../product/service/product.service";
import {TokenService} from "../../security/service/token.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  changeDetection: ChangeDetectionStrategy.Default
})
export class HomeComponent implements OnInit {
  productViewInfo: ProductView[] = [];
  productViewList!: ProductViewInfoJson;
  request = {page: 0, size: 8};
  pageNumber = 0;
  totalPages = 0;
  searchs = '';
  checkSearch = '';
  cartList: CartView[] | undefined;
  idAccount: string | null | undefined;
  idCustomer: number | undefined;
  temp: CartView | undefined;
  getCartTotalPrice: GetCartTotalPrice | undefined;


  constructor(
    private homeService: HomeService,
    private toast: ToastrService,
    private title: Title,
    private messageService: MessageService,
    private customerService: CustomerService,
    private productService: ProductService,
    private tokenService: TokenService
  ) {
    this.title.setTitle('Trang chủ');
  }

  ngOnInit(): void {
    this.messageService.getMessageSearch().subscribe(data => {
      this.searchs = data;
      this.search(this.searchs, true);
      console.log('loading .....', this.searchs);
    });
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

  getAll(request: { page: number, size: number }): void {
    this.homeService.showAllList(request).subscribe(data => {
      console.log(data);
      // @ts-ignore
      this.productViewList = data;
      // @ts-ignore
      this.productViewInfo = data.content;
      console.log(this.productViewInfo);
      // @ts-ignore
      this.pageNumber = data.pageable.pageNumber;
      // @ts-ignore
      this.totalPages = data.totalPages;
    }, error => {
    });
  }

  changePage(pageNumber: number): void {
    this.request.page = pageNumber;
    this.ngOnInit();
  }

  reload(): void {
    this.request.page = 0;
    this.getAll(this.request);
  }

  search(searchs: string, flag: boolean): void {
    console.log(1);
    if (!flag) {
      this.request.page = 0;
    }
    this.searchs = searchs;
    console.log(name);
    this.homeService.searchs(
      searchs.trim(),
      this.request
    ).subscribe(data => {
      console.log(data);
      this.productViewList = data;
      // @ts-ignore
      this.productViewInfo = data.content;

      // @ts-ignore
      this.totalPages = data.totalPages;
      // @ts-ignore
      this.pageNumber = data.pageable.pageNumber;
      if ((searchs !== '') && !flag) {
        this.toast.success('Tim kiếm thành công.');
      }
    }, error => {
      this.searchs = '';
      flag = true;
      if (error.status === 400) {
        this.toast.error('Không có kết quả.', 'Thông báo');
      }

    }, () => {
    });
  }
}
