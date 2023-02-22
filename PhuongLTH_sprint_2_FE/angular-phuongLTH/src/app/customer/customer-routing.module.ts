import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CustomerListComponent} from './customer-list/customer-list.component';
import {AuthGuardGuard} from '../authGauth/authorization/auth-guard.guard';
import {AdminGuard} from '../authGauth/authorization/admin.guard';

const routes: Routes = [
  {path: '', component: CustomerListComponent, canActivate: [AuthGuardGuard] && [AdminGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
