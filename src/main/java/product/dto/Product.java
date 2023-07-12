package product.dto;

import product.dto.Category;

import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private double price;
    private double quantity;
    private Category category;
    public boolean isAvailable;

    public Product() {
    }

    public Product(UUID id, String name, double price, double quantity, Category category, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        String isAvailable = this.isAvailable ? "yes" : "no";
        return name +
                " | \t" + price +
                " | \t" + quantity +
                " | \t" + category +
                " | \t" + isAvailable +
                " | \t" + id;
    }
}
