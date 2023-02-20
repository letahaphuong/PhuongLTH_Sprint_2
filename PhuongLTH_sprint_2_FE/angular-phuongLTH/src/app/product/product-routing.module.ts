import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ProductDetailComponent} from './product-detail/product-detail.component';
import {ProductCartComponent} from './product-cart/product-cart.component';

const routes: Routes = [
  {path: 'detail/:id', component: ProductDetailComponent},
  {path: 'cart', component: ProductCartComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule {
}
