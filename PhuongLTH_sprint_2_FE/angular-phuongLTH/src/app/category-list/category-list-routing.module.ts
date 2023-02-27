import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HikivisionComponent} from './hikivision/hikivision.component';
import {KbvisionComponent} from './kbvision/kbvision.component';
import {VantechComponent} from './vantech/vantech.component';

const routes: Routes = [
  {path: 'hikivision', component: HikivisionComponent},
  {path: 'kbvision', component: KbvisionComponent},
  {path: 'vantech', component: VantechComponent},
  {path: 'yoosee', component: HikivisionComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoryListRoutingModule {
}
