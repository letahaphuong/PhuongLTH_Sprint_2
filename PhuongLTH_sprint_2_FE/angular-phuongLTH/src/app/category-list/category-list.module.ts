import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CategoryListRoutingModule } from './category-list-routing.module';
import { HikivisionComponent } from './hikivision/hikivision.component';
import { KbvisionComponent } from './kbvision/kbvision.component';
import { VantechComponent } from './vantech/vantech.component';
import { YooseeComponent } from './yoosee/yoosee.component';


@NgModule({
  declarations: [HikivisionComponent, KbvisionComponent, VantechComponent, YooseeComponent],
  imports: [
    CommonModule,
    CategoryListRoutingModule
  ]
})
export class CategoryListModule { }
