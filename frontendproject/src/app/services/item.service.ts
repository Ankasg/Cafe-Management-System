import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { url } from '../models/projecturl';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  
  constructor(private httpClient: HttpClient) { }

  addItems(items: any, categoryId: any) {
    return this.httpClient.post(`${url}/api/item/${categoryId}`, items);
  }

  addCategory(category: any) {
    return this.httpClient.post(`${url}/api/category/addcategory`, category);
  }

  // category endpoint
  getCategories() {
    return this.httpClient.get(`${url}/api/category/getall`);
  }

  getItems() {
    return this.httpClient.get(`${url}/api/item/getitems`);
  }

  // ✅ NEW: Added this method as you asked
  getItemsFromDefaultUrl() {
    return this.httpClient.get("http://localhost:9090/api/items");
  }

  // (Optional) You may later use this to test direct URL access.
  placeOrder(userId: number, itemIds: number[]) {
    return this.httpClient.post(`${url}/api/order/place/${userId}`, itemIds);
  }

  // ✅ NEW: Get all orders placed by a specific user
  getOrdersByUserId(userId: number) {
    return this.httpClient.get(`${url}/api/order/user/${userId}`);
  }

  // ✅ NEW: Add item with image (for admin item upload with file)
  addItemWithImage(formData: FormData) {
    return this.httpClient.post(`${url}/api/item/add-item-with-image`, formData);
  }
}