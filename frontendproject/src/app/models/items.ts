// src/app/models/items.model.ts

import { Category } from './category'; // Adjust path if needed

export class Items {
  itemId: number | null;
  itemName: string;
  itemPrice: number;
  category: Category;

  // Added this field to support image URL returned from backend
  imageUrl?: string;

  constructor() {
    this.itemId = null;
    this.itemName = "";
    this.itemPrice = 0;
    this.category = new Category(0, '');
  }
}