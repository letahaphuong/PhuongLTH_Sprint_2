import {Component, OnInit} from '@angular/core';
import {CustomerView} from '../../dto/customer-view';
import {CustomerViewInfoJson} from '../../dto/customer-view-info-json';
import {CustomerService} from '../service/customer.service';
import {ToastrService} from 'ngx-toastr';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
  customerViewInfo: CustomerView[] = [];
  customerViewList!: CustomerViewInfoJson;
  request = {page: 0, size: 5};
  pageNumber = 0;
  totalPages = 0;
  name = '';
  email = '';

  constructor(
    private customerService: CustomerService,
    private toast: ToastrService,
    private title: Title
  ) {
    this.title.setTitle('Danh sách khách hàng');
  }

  ngOnInit(): void {
    this.getAll(this.request);
  }

  getAll(request: { page: number, size: number }): void {
    this.customerService.showAllList(request).subscribe(data => {
      console.log(data);
      // @ts-ignore
      this.customerViewList = data;
      // @ts-ignore
      this.customerViewInfo = data.content;
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


  changePage(pageNumber: number): void {
    this.request.page = pageNumber;
    this.ngOnInit();
  }

  search(name: string, email: string, flag: boolean): void {
    if (!flag) {
      this.request.page = 0;
    }
    this.name = name;
    this.email = email;
    console.log(name);
    this.customerService.search(
      name.trim(),
      email.trim(),
      this.request).subscribe(data => {
      console.log(data);
      this.customerService = data;
      // @ts-ignore
      this.customerViewInfo = data.content;
      // @ts-ignore
      this.totalPages = data.totalPages;
      // @ts-ignore
      this.pageNumber = data.pageable.pageNumber;
      if ((name !== '' || email !== '') && !flag) {
        this.toast.success('Tim kiếm thành công');
      }
    }, error => {
      this.name = '';
      this.email = '';
      flag = true;
      if (error.status === 400) {
        this.toast.error('Không có kết quả', 'Thông báo');
      }

    }, () => {
    });
  }
}
