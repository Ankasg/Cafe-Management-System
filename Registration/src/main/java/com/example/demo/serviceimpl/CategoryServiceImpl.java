package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Save a new category
    @Override
    public Category addcategory(Category category) {
        return categoryRepository.save(category);
    }

    // Fetch all categories (added method)
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

	@Override
	public Category getCategoryByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(categoryId)
			    .orElseThrow(() -> new RuntimeException("Category not found for ID: " + categoryId));

	}
}