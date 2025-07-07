package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="itemtable")
public class Items {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="Item_id")
	private int ItemId;

	@Column(name="ItemName",length=50,nullable = false)
	private String itemName;

	@Column(name="ItemPrice")
	private float itemPrice;

	/*
	 * @Column(name="ItemType") private String itemType;
	 */

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="categoryId")
	private Category category;

	// ✅ NEW FIELD for storing image path
	@Column(name = "ImageUrl")
	private String imageUrl;

	// Getters and Setters
	public int getItemId() {
		return ItemId;
	}

	public void setItemId(int itemId) {
		ItemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// ✅ Getter and Setter for imageUrl
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Items [ItemId=" + ItemId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", category=" + category
				+ ", imageUrl=" + imageUrl + "]";
	}
}