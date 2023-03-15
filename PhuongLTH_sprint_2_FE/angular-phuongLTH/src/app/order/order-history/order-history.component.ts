import {Component, OnInit} from '@angular/core';
import {OrderHistory} from '../../dto/order-info/OrderHistory';
import {OrderInfoService} from '../service/order-info.service';
import {TokenService} from '../../security/service/token.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {
  orderHistory: OrderHistory[] = [];
  idCustomer: string | undefined | null;
  p = 0;
  constructor(
    private tokenService: TokenService,
    private orderInfoService: OrderInfoService) {
    if (this.tokenService.getToken()) {
      this.idCustomer = this.tokenService.getIdCustomer();
      this.getOrderHistory(this.idCustomer);
    }
  }

  getOrderHistory(id: any): void {
    this.orderInfoService.getHistory(id).subscribe(data => {
      this.orderHistory = data;
    });
  }

  ngOnInit(): void {
  }

}
