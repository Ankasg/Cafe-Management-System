package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Items;
import com.example.demo.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;  // ✅ Required for JSON to Object conversion

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/item/")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Existing method: add item without category
    @PostMapping
    public ResponseEntity<Items> addItems(@RequestBody Items items) {
        return new ResponseEntity<Items>(itemService.addItem(items), HttpStatus.CREATED);
    }

    // Existing method: add item with category (without image)
    @PostMapping("{categoryId}")
    public ResponseEntity<Items> addItems(@PathVariable("categoryId") int categoryId, @RequestBody Items items) {
        return new ResponseEntity<Items>(itemService.addItem(items, categoryId), HttpStatus.CREATED);
    }

    // ✅ Final method: add item with image and category
    @PostMapping("add-item-with-image")
    public ResponseEntity<Items> addItemWithImage(
            @RequestPart("item") String itemJson,
            @RequestParam("categoryId") int categoryId,
            @RequestPart("image") MultipartFile imageFile) {
        try {
            // Convert JSON string to Items object
            ObjectMapper objectMapper = new ObjectMapper();
            Items items = objectMapper.readValue(itemJson, Items.class);

            Items savedItem = itemService.addItem(items, categoryId, imageFile);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Fetch all items
    @GetMapping("getitems")
    public List<Items> getAllItems() {
        return itemService.getAllItems();
    }
}