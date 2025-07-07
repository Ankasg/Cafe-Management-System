package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Category;

public interface CategoryService {
	public Category addcategory(Category category );
	List<Category> getAllCategories();
	public Category getCategoryByCategoryId(int categoryId);
	

	

}
