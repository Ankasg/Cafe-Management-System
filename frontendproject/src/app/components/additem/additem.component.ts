import { Component, OnInit } from '@angular/core';
import { Items } from '../../models/items';
import { Category } from '../../models/category';
import { ItemService } from '../../services/item.service';

@Component({
  selector: 'app-additem',
  standalone: false,
  templateUrl: './additem.component.html',
  styleUrls: ['./additem.component.css']
})
export class AdditemComponent implements OnInit {
  
  item = new Items();
  categoryId: any;
  categoryList: any;

  // ✅ NEW: to store selected file
  selectedFile: File | null = null;

  constructor(private itemService: ItemService) {
    // Initialize item.category as a new Category
    this.item.category = new Category(0, '');
  }

  ngOnInit(): void {
    this.itemService.getCategories().subscribe(
      (response: any) => {
        this.categoryList = response;
        console.log('Categories fetched successfully');
        console.log(this.categoryList);
      },
      error => {
        console.error('Failed to fetch categories', error);
      }
    );
  }

  // ✅ NEW: Handle file selection
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  addItems() {
    // ✅ Added this check only (without changing rest of your code)
    if (!this.categoryId || this.categoryId == 0) {
      alert("Please select a valid category.");
      return;
    }

    if (!this.selectedFile) {
      alert("Please select an image.");
      return;
    }

    const formData = new FormData();
    formData.append('item', JSON.stringify(this.item));
    formData.append('categoryId', this.categoryId.toString()); // Ensure it's sent as string
    formData.append('image', this.selectedFile);

    this.itemService.addItemWithImage(formData).subscribe(
      (response: any) => {
        alert('Item added successfully');
      },
      error => {
        console.error('Error adding item:', error);
        alert('Failed to add the item');
      }
    );
  }
}
