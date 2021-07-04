import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {IndustryCrudComponent} from "./industry-crud/industry-crud.component";

const routes: Routes = [
  {path:"crud-app", component:IndustryCrudComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
