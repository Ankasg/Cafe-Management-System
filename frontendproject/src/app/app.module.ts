import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AdminhomeComponent } from './components/adminhome/adminhome.component';
import { UserhomeComponent } from './components/userhome/userhome.component';
import { AdditemComponent } from './components/additem/additem.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { ViewBillComponent } from './components/view-bill/view-bill.component';
import { PaymentSuccessComponent } from './components/payment-success/payment-success.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    RegisterComponent,
    LoginComponent,
    AdminhomeComponent,
    UserhomeComponent,
    AdditemComponent,
    ViewBillComponent,
    PaymentSuccessComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
