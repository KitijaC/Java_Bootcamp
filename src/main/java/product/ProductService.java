package product;

import product.dto.Product;

import java.util.ArrayList;

public class ProductService {

    private ArrayList<Product> products = new ArrayList<>();

    public String addProduct(Product product) throws Exception {
        // next step add validation and throw exceptions for missing values
        // or empty strings
        if (product.getName().isEmpty()) throw new Exception("Please provide product name!");
        if (product.getCategory() == null) throw new Exception("Please provide product category!");

        this.products.add(product);
        return "product " + product.getName() + " added successfully!";
    }

    public ArrayList<Product> getAllProducts() {
        return this.products;
    }
}
