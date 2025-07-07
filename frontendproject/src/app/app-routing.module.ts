import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { AdminhomeComponent } from './components/adminhome/adminhome.component';
import { UserhomeComponent } from './components/userhome/userhome.component';
import { AdditemComponent } from './components/additem/additem.component';
import { ViewBillComponent } from './components/view-bill/view-bill.component';
import { PaymentSuccessComponent } from './components/payment-success/payment-success.component';


const routes: Routes = [{path:"",component:WelcomeComponent},
  {path:"register",component:RegisterComponent},
  {path:"login",component:LoginComponent},
  {path:"adminhome",component:AdminhomeComponent},
   {path:"userhome",component:UserhomeComponent},
   {path:"AddItemUrl",component:AdditemComponent},
   {path:"billurl",component:ViewBillComponent},
   {path:'payment-success', component:PaymentSuccessComponent }
   

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
