package product;

import product.dto.Product;

import java.util.ArrayList;

public class ProductService {

    private ArrayList<Product> products = new ArrayList<>();

    public String addProduct(Product product) {
        // next step add validation and throw exceptions for missing values
        // or empty strings
        this.products.add(product);
        return "product added successfully";
    }
}
