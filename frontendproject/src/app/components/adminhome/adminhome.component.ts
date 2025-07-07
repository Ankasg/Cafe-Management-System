import { Component, OnInit } from '@angular/core';
import { Items } from '../../models/items';
import { Category } from '../../models/category';
import { ItemService } from '../../services/item.service';

@Component({
  selector: 'app-adminhome',
  standalone: false,
  templateUrl: './adminhome.component.html',
  styleUrls: ['./adminhome.component.css']
})
export class AdminhomeComponent implements OnInit {
  category: Category = new Category(0, '');
  item: Items = new Items();
  showCategoryForm: boolean = false;
  showItemForm: boolean = false;

  categoryId: any;                      // ✅ for dropdown
  categoryList: any[] = [];             // ✅ stores category list

  constructor(private itemService: ItemService) {}

  ngOnInit() {
    this.itemService.getCategories().subscribe(
      (response: any) => {
        this.categoryList = response;
      },
      error => {
        console.error('Error fetching categories', error);
      }
    );
  }

  categoryForm() {
    this.showCategoryForm = true;
  }

  itemForm() {
    this.showItemForm = true;
  }

  addCategory() {
    this.itemService.addCategory(this.category).subscribe(
      response => {
        console.log("Category added successfully", response);
        this.showCategoryForm = false;
      },
      error => {
        console.error("Error adding category", error);
      }
    );
  }

  addItem() {
    if (!this.categoryId) {
      alert("Please select a category.");
      return;
    }

    // set selected category
    this.item.category = new Category(this.categoryId, '');

    this.itemService.addItems(this.item, this.categoryId).subscribe(
      response => {
        console.log("Item added successfully", response);
        this.showItemForm = false;
      },
      error => {
        console.error("Error adding item", error);
      }
    );
  }
}