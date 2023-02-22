import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ProductDetailComponent} from './product-detail/product-detail.component';
import {ProductCartComponent} from './product-cart/product-cart.component';
import {ProductListComponent} from './product-list/product-list.component';
import {ProductCreateComponent} from './product-create/product-create.component';
import {AuthGuardGuard} from '../authGauth/authorization/auth-guard.guard';
import {AdminGuard} from '../authGauth/authorization/admin.guard';
import {ProductEditComponent} from './product-edit/product-edit.component';

const routes: Routes = [
  {path: 'detail/:idProduct', component: ProductDetailComponent},
  {path: 'cart/:idProduct', component: ProductCartComponent},
  {path: '', component: ProductListComponent, canActivate: [AuthGuardGuard] && [AdminGuard]},
  {path: 'create', component: ProductCreateComponent, canActivate: [AuthGuardGuard] && [AdminGuard]},
  {path: 'edit/:idProduct', component: ProductEditComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule {
}
