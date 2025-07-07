import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ItemService } from '../../services/item.service';
import { OrderService } from '../../services/order.service';
import { Order } from '../../models/order';

@Component({
  selector: 'app-userhome',
  standalone: false,
  templateUrl: './userhome.component.html',
  styleUrls: ['./userhome.component.css']
})
export class UserhomeComponent implements OnInit {
  userid: any;
  loginStatus: boolean = false;
  items: any[] = [];

  showItems: boolean = false;
  selectedItems: { itemId: number, quantity: number }[] = [];
  showOrders: boolean = false;
  orders: Order[] = [];

  p: number = 1;
  count: number = 5;

  constructor(
    private router: Router,
    private itemService: ItemService,
    private orderService: OrderService
  ) {}

  ngOnInit(): void {
    this.userid = sessionStorage.getItem('userId');
    if (this.userid != null) {
      this.loginStatus = true;

      this.itemService.getItems().subscribe({
        next: (data: any) => {
          this.items = data || [];
        },
        error: (error) => {
          console.error("Error fetching items:", error);
        }
      });
    }
  }

  logout() {
    sessionStorage.removeItem('userId');
    this.router.navigate([""]);
  }

  toggleSelect(itemId: number) {
    const index = this.selectedItems.findIndex(item => item.itemId === itemId);
    if (index > -1) {
      this.selectedItems.splice(index, 1); // Uncheck
    } else {
      this.selectedItems.push({ itemId, quantity: 1 }); // Check with quantity 1
    }
  }

  isItemSelected(itemId: number): boolean {
    return this.selectedItems.some(i => i.itemId === itemId);
  }

  getQuantity(itemId: number): number {
    const item = this.selectedItems.find(i => i.itemId === itemId);
    return item ? item.quantity : 1;
  }

  increaseQuantity(itemId: number): void {
    const item = this.selectedItems.find(i => i.itemId === itemId);
    if (item) item.quantity++;
  }

  decreaseQuantity(itemId: number): void {
    const item = this.selectedItems.find(i => i.itemId === itemId);
    if (item && item.quantity > 1) item.quantity--;
  }

  placeOrder() {
    if (this.selectedItems.length === 0) {
      alert("Please select at least one item to place an order.");
      return;
    }

    this.orderService.placeOrder(this.userid, this.selectedItems).subscribe({
      next: (order) => {
        alert("Order placed successfully!");
        this.orders.push(order);
        this.selectedItems = [];
        this.router.navigate(['billurl']);
      },
      error: (err) => {
        console.error("Order failed", err);
        alert("Something went wrong while placing the order.");
      }
    });
  }

  viewOrders() {
    this.showItems = false;
    this.showOrders = true;

    this.itemService.getOrdersByUserId(this.userid).subscribe({
      next: (res) => {
        this.orders = res as Order[];
      },
      error: (err) => {
        console.error("Failed to fetch orders", err);
      }
    });
  }

  orderItem(item: any) {
    this.selectedItems = [{ itemId: item.itemId, quantity: 1 }];
    this.placeOrder();
  }
}
