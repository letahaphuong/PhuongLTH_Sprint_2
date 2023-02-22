import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ProductCartComponent } from './product-cart/product-cart.component';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductCreateComponent } from './product-create/product-create.component';
import {ReactiveFormsModule} from '@angular/forms';
import {SecurityModule} from '../security/security.module';
import { ProductDeleteComponent } from './product-delete/product-delete.component';
import { ProductEditComponent } from './product-edit/product-edit.component';


@NgModule({
  declarations: [ProductDetailComponent, ProductCartComponent, ProductListComponent, ProductCreateComponent, ProductDeleteComponent, ProductEditComponent],
  imports: [
    CommonModule,
    ProductRoutingModule,
    ReactiveFormsModule,
    SecurityModule
  ]
})
export class ProductModule { }
