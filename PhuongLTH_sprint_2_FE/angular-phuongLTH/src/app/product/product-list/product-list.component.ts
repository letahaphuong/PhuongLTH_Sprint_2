import {Component, OnInit} from '@angular/core';
import {ProductView} from '../../dto/product/product-view';
import {ProductViewInfoJson} from '../../dto/product/product-view-info-json';
import {HomeService} from '../../home/service/home.service';
import {Title} from '@angular/platform-browser';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  productViewInfo: ProductView[] = [];
  productViewList!: ProductViewInfoJson;
  request = {page: 0, size: 8};
  pageNumber = 0;
  totalPages = 0;
  nameCategory = '';
  nameProduct = '';
  price = '';
  temp: ProductView | undefined;

  constructor(
    private homeService: HomeService,
    private toast: ToastrService,
    private title: Title
  ) {
    this.title.setTitle('Danh sách sản phẩm');
  }

  ngOnInit(): void {
    this.search(this.nameCategory, this.nameProduct, this.price, true);
  }
  changePage(pageNumber: number): void {
    this.request.page = pageNumber;
    this.ngOnInit();
  }
  getAll(request: { page: number, size: number }): void {
    this.homeService.showAllList(request).subscribe(data => {
      // @ts-ignore
      this.productViewList = data;
      // @ts-ignore
      this.productViewInfo = data.content;
      // @ts-ignore
      this.pageNumber = data.pageable.pageNumber;
      // @ts-ignore
      this.totalPages = data.totalPages;
    }, error => {
    });
  }
  reload(): void {
    this.request.page = 0;
    this.getAll(this.request);
  }

  search(nameCategory: string, nameProduct: string, price: string, flag: boolean): void {
    if (!flag) {
      this.request.page = 0;
    }
    this.nameCategory = nameCategory;
    this.nameProduct = nameProduct;
    this.price = price;
    this.homeService.search(
      nameCategory.trim(),
      nameProduct.trim(),
      price.trim(),
      this.request).subscribe(data => {
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
