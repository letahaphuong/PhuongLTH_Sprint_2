import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {OrderCodComponent} from './order-cod/order-cod.component';
import {UserGuard} from '../authGauth/authorization/user.guard';
import {OrderPaymentComponent} from './order-payment/order-payment.component';
import {OrderHistoryComponent} from './order-history/order-history.component';
import {OrderDetailHistoryComponent} from './order-detail-history/order-detail-history.component';

const routes: Routes = [
  {path: 'cod', component: OrderCodComponent},
  {path: 'success', component: OrderPaymentComponent},
  {path: 'history', component: OrderHistoryComponent},
  {path: 'order-detail/history/:id', component: OrderDetailHistoryComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderRoutingModule {
}
