import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../models/order';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseUrl = 'http://localhost:9090/api/order';

  constructor(private http: HttpClient) {}

  placeOrder(userId: number, items: { itemId: number; quantity: number }[]): Observable<Order> {
    return this.http.post<Order>(`${this.baseUrl}/place/${userId}`, items);
  }

  getOrdersByUserId(userId: number): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.baseUrl}/user/${userId}`);
  }

  // ✅ Extra: Update payment status of order
  updatePaymentStatus(orderId: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/payment-done/${orderId}`, {});
  }

  // ✅ Razorpay: Create payment order
  createPaymentOrder(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/create-payment-order`, data);
  }
}
