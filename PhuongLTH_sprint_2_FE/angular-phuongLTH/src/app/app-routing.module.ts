import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {NotFoundComponent} from './not-found/not-found.component';
import {AuthGuardGuard} from './authGauth/authorization/auth-guard.guard';
import {ADMINUSERGuard} from './authGauth/authorization/admin-user.guard';

const routes: Routes = [
  {path: 'home', loadChildren: () => import('./home/home.module').then(module => module.HomeModule)},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'security', loadChildren: () => import('./security/security.module').then(module => module.SecurityModule)},
  {path: 'product', loadChildren: () => import('./product/product.module').then(module => module.ProductModule)},
  {
    path: 'customer',
    loadChildren: () => import('./customer/customer.module').then(module => module.CustomerModule),
    canActivate: [AuthGuardGuard]
  },
  {path: 'order', loadChildren: () => import('./order/order.module').then(module => module.OrderModule), canActivate: [AuthGuardGuard]},
  {path: 'chat', loadChildren: () => import('./chat/chat.module').then(module => module.ChatModule)}
  ,
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
