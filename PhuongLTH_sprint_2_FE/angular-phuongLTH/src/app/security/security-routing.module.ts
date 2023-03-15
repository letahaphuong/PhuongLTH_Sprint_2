import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {SignUpComponent} from './sign-up/sign-up.component';
import {ChangeAvatrComponent} from './change-avatr/change-avatr.component';
import {PaymentComponent} from './payment/payment.component';
import {AuthGuardGuard} from '../authGauth/authorization/auth-guard.guard';
import {LoginSuperAdminComponent} from './login-super-admin/login-super-admin.component';
import {ManagementPageComponent} from './management-page/management-page.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'sign-up', component: SignUpComponent},
  {path: 'change-avatar', component: ChangeAvatrComponent},
  {path: 'payment', component: PaymentComponent, canActivate: [AuthGuardGuard]},
  {path: 'login-sp-admin', component: LoginSuperAdminComponent},
  {path: 'management-page', component: ManagementPageComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SecurityRoutingModule {
}
