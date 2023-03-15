import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CustomerListComponent} from './customer-list/customer-list.component';
import {AuthGuardGuard} from '../authGauth/authorization/auth-guard.guard';
import {AdminGuard} from '../authGauth/authorization/admin.guard';
import {SuperAdminGuard} from '../authGauth/authorization/super-admin.guard';

const routes: Routes = [
  {path: '', component: CustomerListComponent, canActivate: [AuthGuardGuard] && [SuperAdminGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
