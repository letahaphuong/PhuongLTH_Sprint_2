import {Component, OnInit} from '@angular/core';
import {OrderDetailHistory} from '../../dto/product/OrderDetailHistory';
import {OrderInfoService} from '../service/order-info.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-order-detail-history',
  templateUrl: './order-detail-history.component.html',
  styleUrls: ['./order-detail-history.component.css']
})
export class OrderDetailHistoryComponent implements OnInit {
  orderDetailHistory: OrderDetailHistory [] = [];
  total = 0;
  array: any = [];

  constructor(
    private orderInfoService: OrderInfoService,
    private activatedRoute: ActivatedRoute,
  ) {
    this.activatedRoute.paramMap.subscribe(data => {
      const id = data.get('id');
      if (id != null) {
        this.getOrderDetailHistory(id);
      }
    });
  }

  getOrderDetailHistory(id: any): void {
    this.orderInfoService.getOrderDetailHistory(id).subscribe(data => {
      this.orderDetailHistory = data;
      const arr: OrderDetailHistory[] = data;
      for (let i = 0; i < arr.length; i++) {
        // @ts-ignore
        this.total += (this.orderDetailHistory[i].priceOrder * this.orderDetailHistory[i].quantityOrder);
        console.log(this.total, 'loading...');
        console.log(this.orderDetailHistory[i].priceOrder, 'loading...');
        console.log(this.orderDetailHistory[i].quantityOrder, 'loading...');
      }
    });
  }

  ngOnInit(): void {
  }

  file(): void {
    var arr = this.orderDetailHistory;
    for (let i = 0; i < arr.length; i++) {
      let o;
      var newArr: any = [];
      for (o in this.orderDetailHistory[i]) {
        newArr.push(o);
      }
      break;
    }
    this.array.push(newArr);
    for (let i = 0; i < arr.length; i++) {
      this.array.push(Object.values(arr[i]));
    }
    var CsvString = '';
    this.array.forEach((RowItem: any, RowIndex: any) => {
      RowItem.forEach((ColItem: any, ColIndex: any) => {
        CsvString += ColItem + ',';
      });
      CsvString += '\r\n';
    });
    CsvString = 'data:application/pdf, ' + encodeURIComponent(CsvString);
    var x = document.createElement('A');
    x.setAttribute('csv', CsvString);
    x.setAttribute('download', 'hoa-don-ma-' + arr[1].codeOrder + '.csv');
    document.body.appendChild(x);
    x.click();
    this.array = [];
  }
}
