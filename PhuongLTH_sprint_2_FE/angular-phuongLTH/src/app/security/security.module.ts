import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SecurityRoutingModule } from './security-routing.module';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import {ReactiveFormsModule} from '@angular/forms';
import { ChangeAvatrComponent } from './change-avatr/change-avatr.component';
import { UploadAvatarComponent } from './upload-avatar/upload-avatar.component';


@NgModule({
  declarations: [LoginComponent, SignUpComponent, ChangeAvatrComponent, UploadAvatarComponent],
  imports: [
    CommonModule,
    SecurityRoutingModule,
    ReactiveFormsModule
  ]
})
export class SecurityModule { }
