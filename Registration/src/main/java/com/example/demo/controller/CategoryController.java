package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Category;
import com.example.demo.model.Items;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ItemService;
import com.example.demo.service.UserService;

//http://localhost:
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/category/")

public class CategoryController  {
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping("addcategory")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		return new ResponseEntity <Category>(categoryService.addcategory(category),HttpStatus.CREATED);
	}
	
	@GetMapping("getall")
	public List<Category> getAllCategories() {
	    return categoryService.getAllCategories();
	}
}