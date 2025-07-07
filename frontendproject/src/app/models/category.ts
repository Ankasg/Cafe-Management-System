import { Items } from "./items";

export class Category {
  id(item: Items, id: any) {
    throw new Error('Method not implemented.');
  }
  categoryId: number;
  categoryName: string;

  constructor(categoryId: number, categoryName: string) {
    this.categoryId = categoryId;
    this.categoryName = categoryName;
  }
}