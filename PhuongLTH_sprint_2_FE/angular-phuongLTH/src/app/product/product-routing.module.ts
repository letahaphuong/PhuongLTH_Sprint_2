import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ProductDetailComponent} from './product-detail/product-detail.component';
import {ProductCartComponent} from './product-cart/product-cart.component';
import {ProductListComponent} from './product-list/product-list.component';
import {ProductCreateComponent} from './product-create/product-create.component';
import {AuthGuardGuard} from '../authGauth/authorization/auth-guard.guard';
import {ProductEditComponent} from './product-edit/product-edit.component';
import {SuperAdminGuard} from '../authGauth/authorization/super-admin.guard';

const routes: Routes = [
  {path: 'detail/:idProduct', component: ProductDetailComponent},
  {path: 'cart/:idCustomer', component: ProductCartComponent, canActivate: [AuthGuardGuard]},
  {path: '', component: ProductListComponent, canActivate: [AuthGuardGuard] && [SuperAdminGuard]},
  {path: 'create', component: ProductCreateComponent, canActivate: [AuthGuardGuard] && [SuperAdminGuard]},
  {path: 'edit/:idProduct', component: ProductEditComponent, canActivate: [AuthGuardGuard] && [SuperAdminGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule {
}
