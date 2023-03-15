import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrderRoutingModule } from './order-routing.module';
import { OrderCodComponent } from './order-cod/order-cod.component';
import { OrderPaymentComponent } from './order-payment/order-payment.component';
import { OrderHistoryComponent } from './order-history/order-history.component';
import {NgxPaginationModule} from "ngx-pagination";
import { OrderDetailHistoryComponent } from './order-detail-history/order-detail-history.component';


@NgModule({
  declarations: [OrderCodComponent, OrderPaymentComponent, OrderHistoryComponent, OrderDetailHistoryComponent],
    imports: [
        CommonModule,
        OrderRoutingModule,
        NgxPaginationModule
    ]
})
export class OrderModule { }
