import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndustryCrudComponent } from './industry-crud/industry-crud.component';
import {ReactiveFormsModule} from "@angular/forms";
import {IndustryServiceService} from "./industry-service.service";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    IndustryCrudComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [IndustryServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
