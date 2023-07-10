package product;

import product.dto.Product;

import java.util.ArrayList;
import java.util.UUID;

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

    public ArrayList<Product> findProductsByDetail(String detail) {
        ArrayList<Product> products = new ArrayList<>();

        for (Product product: this.products) {
            if (
                product.getName().toLowerCase().contains(detail) ||
                product.getCategory().toString().toLowerCase().contains(detail) ||
                product.getId().toString().toLowerCase().equals(detail)
            ) products.add(product);

            // rice [rice, break, milk] v equals
            // ric [rice, break, milk] x equals
            // ic [rice, break, milk] v contains
        }

        return products;
    }

    public String removeProductById(UUID productId) {
        boolean result = this.products.removeIf(currentProduct -> currentProduct.getId().equals(productId));
        if (result) return "Product removed successfully!";
        return "Incorrect product id, please try again";
    }
}
