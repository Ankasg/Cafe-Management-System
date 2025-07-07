import { Component } from '@angular/core';

@Component({
  selector: 'app-payment-success',
  standalone: false,
  templateUrl: './payment-success.component.html',
  styleUrls: ['./payment-success.component.css']  // ✅ fixed here
})
export class PaymentSuccessComponent {}
