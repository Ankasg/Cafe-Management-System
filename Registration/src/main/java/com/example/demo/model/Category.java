package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="CategoryTable")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="category_id")
	private int categoryId;
   @Column(name="CategoryName",length=50,nullable = false)
	private String categoryName;
   
   //it references to the foreign created by the items.java
   @JsonIgnore
   @OneToMany(mappedBy="category")
   private List<Items> itemList;
   
public List<Items> getItemList() {
	return itemList;
}
public void setItemList(List<Items> itemList) {
	this.itemList = itemList;
}
public int getCategoryId() {
	return categoryId;
}
public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
}
public String getCategoryName() {
	return categoryName;
}
public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}
@Override
public String toString() {
	return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
}
   
   
   
}
