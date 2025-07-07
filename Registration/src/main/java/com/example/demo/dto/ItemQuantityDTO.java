package com.example.demo.dto;

public class ItemQuantityDTO {
	 private int itemId;
	    private int quantity;

	    // Constructors
	    public ItemQuantityDTO() {}

	    public ItemQuantityDTO(int itemId, int quantity) {
	        this.itemId = itemId;
	        this.quantity = quantity;
	    }

	    // Getters and Setters
	    public int getItemId() {
	        return itemId;
	    }

	    public void setItemId(int itemId) {
	        this.itemId = itemId;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

}
