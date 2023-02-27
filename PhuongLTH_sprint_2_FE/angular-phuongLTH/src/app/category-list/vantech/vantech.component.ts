import { Component, OnInit } from '@angular/core';
import {ProductView} from "../../dto/product/product-view";
import {ProductViewInfoJson} from "../../dto/product/product-view-info-json";
import {HomeService} from "../../home/service/home.service";
import {ToastrService} from "ngx-toastr";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-vantech',
  templateUrl: './vantech.component.html',
  styleUrls: ['./vantech.component.css']
})
export class VantechComponent implements OnInit {

  productViewInfo: ProductView[] = [];
  productViewList!: ProductViewInfoJson;
  request = {page: 0, size: 8};
  pageNumber = 0;
  totalPages = 0;
  searchs = 'VANTECH';

  constructor(
    private homeService: HomeService,
    private toast: ToastrService,
    private title: Title
  ) {
    this.title.setTitle('Trang chủ');
  }

  ngOnInit(): void {
    this.search(this.searchs, true);
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
      this.searchs.trim(),
      this.request).subscribe(data => {
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
