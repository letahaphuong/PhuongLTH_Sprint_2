import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ProductDetailComponent} from './product-detail/product-detail.component';
import {ProductCartComponent} from './product-cart/product-cart.component';
import {ProductListComponent} from './product-list/product-list.component';
import {ProductCreateComponent} from './product-create/product-create.component';

const routes: Routes = [
  {path: 'detail/:idProduct', component: ProductDetailComponent},
  {path: 'cart/:idProduct', component: ProductCartComponent},
  {path: '', component: ProductListComponent},
  {path: 'create' , component: ProductCreateComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule {
}
