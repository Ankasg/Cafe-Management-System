package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Items;

public interface ItemService {
	public Items addItem(Items items);

	Items addItem(Items items, int categoryId);

	List getAllItems();

	// ✅ NEW method for item + image upload
	String addItemWithImage(String itemName, float itemPrice, int categoryId, MultipartFile imageFile) throws IOException;

	Items addItem(Items items, int categoryId, MultipartFile imageFile) throws IOException;
}