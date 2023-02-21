import {Component, OnInit} from '@angular/core';
import {ProductView} from '../../dto/product/product-view';
import {ProductViewInfoJson} from '../../dto/product/product-view-info-json';
import {ToastrService} from 'ngx-toastr';
import {Title} from '@angular/platform-browser';
import {HomeService} from '../service/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  productViewInfo: ProductView[] = [];
  productViewList!: ProductViewInfoJson;
  request = {page: 0, size: 8};
  pageNumber = 0;
  totalPages = 0;
  nameCategory = '';
  nameProduct = '';
  price = '';

  constructor(
    private homeService: HomeService,
    private toast: ToastrService,
    private title: Title
  ) {
    this.title.setTitle('Trang chủ');
  }

  ngOnInit(): void {
    this.search(this.nameCategory, this.nameProduct, this.price, true);
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

  search(nameCategory: string, nameProduct: string, price: string, flag: boolean): void {
    console.log(1);
    if (!flag) {
      this.request.page = 0;
    }
    this.nameCategory = nameCategory;
    this.nameProduct = nameProduct;
    this.price = price;
    console.log(name);
    this.homeService.search(
      nameCategory.trim(),
      nameProduct.trim(),
      price.trim(),
      this.request).subscribe(data => {
      console.log(data);
      this.productViewList = data;
      // @ts-ignore
      this.productViewInfo = data.content;

      // @ts-ignore
      this.totalPages = data.totalPages;
      // @ts-ignore
      this.pageNumber = data.pageable.pageNumber;
      if ((nameCategory !== '' || nameProduct !== '' || price !== '') && !flag) {
        this.toast.success('Tim kiếm thành công.');
      }
    }, error => {
      this.nameCategory = '';
      this.nameProduct = '';
      this.price = '';
      flag = true;
      if (error.status === 400) {
        this.toast.error('Không có kết quả.', 'Thông báo');
      }

    }, () => {
    });
  }
}
