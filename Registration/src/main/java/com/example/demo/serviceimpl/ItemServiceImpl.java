package com.example.demo.serviceimpl;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Category;
import com.example.demo.model.Items;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Items addItem(Items items, int categoryId, MultipartFile imageFile) throws IOException {
        // Save image if provided
        if (imageFile != null && !imageFile.isEmpty()) {
            String uploadDir = "uploads";
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            items.setImageUrl("/uploads/" + fileName); // for access in frontend
        }

        // Set category
        Category category = categoryService.getCategoryByCategoryId(categoryId);
        items.setCategory(category);

        // Save item
        return itemRepository.save(items);
    }

    @Override
    public Items addItem(Items items) {
        return itemRepository.save(items);
    }

    @Override
    public Items addItem(Items items, int categoryId) {
        Category category = categoryService.getCategoryByCategoryId(categoryId);
        items.setCategory(category);
        return itemRepository.save(items);
    }

    @Override
    public String addItemWithImage(String itemName, float itemPrice, int categoryId, MultipartFile imageFile) throws IOException {
        Items item = new Items();
        item.setItemName(itemName);
        item.setItemPrice(itemPrice);

        if (imageFile != null && !imageFile.isEmpty()) {
            String uploadDir = "uploads";
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            item.setImageUrl("/uploads/" + fileName);
        }

        Category category = categoryService.getCategoryByCategoryId(categoryId);
        item.setCategory(category);

        itemRepository.save(item);
        return "Item added successfully with image.";
    }
}
