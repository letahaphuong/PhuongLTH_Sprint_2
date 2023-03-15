import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SecurityRoutingModule } from './security-routing.module';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import {ReactiveFormsModule} from '@angular/forms';
import { ChangeAvatrComponent } from './change-avatr/change-avatr.component';
import { UploadAvatarComponent } from './upload-avatar/upload-avatar.component';
import { PaymentComponent } from './payment/payment.component';
import { LoginSuperAdminComponent } from './login-super-admin/login-super-admin.component';
import { ManagementPageComponent } from './management-page/management-page.component';


@NgModule({
  declarations: [LoginComponent, SignUpComponent, ChangeAvatrComponent, UploadAvatarComponent, PaymentComponent, LoginSuperAdminComponent, ManagementPageComponent],
  exports: [
    UploadAvatarComponent
  ],
  imports: [
    CommonModule,
    SecurityRoutingModule,
    ReactiveFormsModule
  ]
})
export class SecurityModule { }
