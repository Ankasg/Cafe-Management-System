import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../services/order.service';
import { Order } from '../../models/order';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

declare var Razorpay: any;

@Component({
  selector: 'app-view-bill',
  standalone:false,
  styleUrls: ['./view-bill.component.css'],
  templateUrl: './view-bill.component.html'
})
export class ViewBillComponent implements OnInit {
  userId!: number;
  orders: Order[] = [];
  paymentMessage: string = '';

  constructor(
    private orderService: OrderService,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    const storedUserId = sessionStorage.getItem('userId');
    if (storedUserId) {
      this.userId = +storedUserId;
      this.orderService.getOrdersByUserId(this.userId).subscribe({
        next: (data) => {
          const sorted = data.sort((a, b) => new Date(b.orderDate).getTime() - new Date(a.orderDate).getTime());
          const latestPending = sorted.find(order => order.paymentStatus !== 'Done');
          this.orders = latestPending ? [latestPending] : [];
          console.log('Current order to pay:', this.orders);
        },
        error: (err) => {
          console.error('Error fetching orders:', err);
        }
      });
    } else {
      console.error('User ID not found in sessionStorage');
      alert('Please log in again.');
    }
  }

  payViaRazorpay(orderId: number, amount: number): void {
    console.log('Starting payment for order:', orderId, 'Amount:', amount);

    this.http.post<any>('http://localhost:9090/api/order/create-payment-order', { amount }).subscribe(response => {
      console.log('Razorpay order created:', response);

      const options = {
        key: 'rzp_test_y7HXsd9wbI20tM',
        amount: response.amount,
        currency: 'INR',
        name: 'Cafe Management',
        description: 'Cafe Order Payment',
        order_id: response.id,

        handler: (res: any) => {
          console.log('Payment successful with response:', res);
          
          this.http.post(`http://localhost:9090/api/order/update-payment-status/${orderId}`, {}, { responseType: 'text' }).subscribe({
            next: (data: any) => {
              console.log('Payment status updated:', data);

              // ✅ ADDED: Save payment record
              const paymentData = {
                orderId: orderId.toString(),
                paymentId: res.razorpay_payment_id,
                status: 'Success',
                amount: amount
              };

              this.http.post('http://localhost:9090/api/payments', paymentData, { responseType: 'text' as 'json' }).subscribe({
                next: (msg) => {
                  console.log('Payment record saved:', msg);
                  console.log('Redirecting to /payment-success');
                  this.router.navigate(['/payment-success']);
                },
                error: (err) => {
                  console.error('Error saving payment record:', err);
                  this.paymentMessage = '❌ Payment saved in order, but failed in payment history!';
                }
              });

            },
            error: (err) => {
              console.error('Error updating payment status:', err);
              this.paymentMessage = '❌ Error updating Razorpay payment';
            }
          });
        },
        prefill: {
          name: 'Customer',
          email: 'customer@example.com',
          contact: '9999999999'
        },
        theme: {
          color: '#3399cc'
        }
      };

      const rzp = new Razorpay(options);
      rzp.open();
    }, err => {
      console.error('Error creating Razorpay order:', err);
    });
  }
}
