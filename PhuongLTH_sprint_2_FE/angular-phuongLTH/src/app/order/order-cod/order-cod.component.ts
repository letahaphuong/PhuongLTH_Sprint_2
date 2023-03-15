import {Component, OnInit} from '@angular/core';
import {Title} from '@angular/platform-browser';
import {OrderInfo} from '../../dto/order-info/OrderInfo';
import {TokenService} from '../../security/service/token.service';
import {OrderInfoService} from '../service/order-info.service';
import {GetCartTotalPrice} from '../../dto/product/get-cart-total-price';
import {ProductService} from '../../product/service/product.service';

@Component({
  selector: 'app-order-cod',
  templateUrl: './order-cod.component.html',
  styleUrls: ['./order-cod.component.css']
})
export class OrderCodComponent implements OnInit {

  orderInfo: OrderInfo[] = [];
  getCartTotalPrice: GetCartTotalPrice | undefined;

  constructor(
    private title: Title,
    private tokenService: TokenService,
    private orderInfoService: OrderInfoService,
    private productService: ProductService
  ) {
    this.title.setTitle('Thông báo đặt hàng');
    const id = this.tokenService.getIdCustomer();
    console.log(id);
    this.orderInfoService.getInfoOrder(id).subscribe(data => {
      this.orderInfo = data;
    });
    if (id != null) {
      this.productService.getCartTotalPrice(+id).subscribe(total => {
        this.getCartTotalPrice = total;
      });
    }
  }

  ngOnInit(): void {
  }
}
