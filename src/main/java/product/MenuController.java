package product;



import product.dto.Category;
import product.dto.Product;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class MenuController {
    private Scanner scanner = new Scanner(System.in);

    // composition - combined different classes/services/controllers in one Main class
    private final ProductService productService;

    public MenuController() {
        this.productService = new ProductService();
    }

    public void start() {
        String userChoice = this.getInfo("""
        Welcome to Product Management
        please choose an option
           1. Add product
           2. View products
           3. Find product
           4. Delete product
           5. Update product
           6. Quit
        """);

        switch (userChoice) {
            case "1":
                //Add product
                this.createProduct();
                break;
            case "2":
                //View products
                this.displayProducts();
                break;
            case "3":
                //Find product
                this.findProduct();
                break;
            case "4":
                //Delete Product
                this.deleteProduct();
                break;
            case "5":
                // update product
                this.updateProduct();
                break;
            case "6":
                // Quit
                displayMessage("goodbye!");
                System.exit(0); // 0 means that everything is ok, but can set what number you want
                break;
            default:
                displayMessage("Please choose an option from the menu");
                break;
        }

        // recursion - process that repeats over and over again, like a loop
        this.start();


    }

    private void updateProduct() {
        try {
            UUID productId = UUID.fromString(this.getInfo("Please enter product id to update: "));
            Product product = this.productService.findProductById(productId);
            this.displayMessage("To update product " + product.getName() +
                    ", please enter new values or empty space to skip a field;");
            /*
            one option is to do this way:
            String updatedName = this.getInfo("Enter name ");
            product.setName(this.getInfo("Enter name ").isEmpty() ? product.getName() : updatedName);
            */

            Product updatedProduct = this.collectProductInfo();
            product.setName(updatedProduct.getName().isBlank() ? product.getName() : updatedProduct.getName());
            //product.setAvailable(updatedProduct.getAvailable() == null ? product.getName() : updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice() <= 0 ? product.getPrice() : updatedProduct.getPrice());
            //product.setCategory(updatedProduct.getCategory(). ? product.getCategory() : updatedProduct.getCategory());
            product.setQuantity(updatedProduct.getQuantity() <= 0 ? product.getQuantity() : updatedProduct.getQuantity());

            String message = this.productService.updateProduct(product);
            this.displayMessage(message);
        } catch (Exception exception) {
            this.displayMessage(exception.getMessage());
        }
    }

    private void deleteProduct() {
        try {
            UUID productId = UUID.fromString(this.getInfo("Please enter product id to delete: "));
            String message = this.productService.removeProductById(productId);
            this.displayMessage(message);
        } catch (Exception exception) {
            this.displayMessage(exception.getMessage());
        }
    }

    private void findProduct() {
        String userInput = this.getInfo("Enter the name, ID or category of product to find: ");
        this.showProductList(this.productService.findProductsByDetail(userInput.toLowerCase()));

    }

    private void displayProducts() {
        this.showProductList(this.productService.getAllProducts());
    }

    private void showProductList(ArrayList<Product> products) {
        displayMessage("Name \t | Price \t | Quantity \t | Category \t | isAvailable \t | ID");
        for (Product currentProduct: products) {
            displayMessage(currentProduct.toString());
        }
    }

    private void createProduct() {
        try {
            Product product = this.collectProductInfo();
            String result = this.productService.addProduct(product);
            displayMessage(result);
            // use product service to add product
        } catch (Exception exception) {
            displayMessage("Error: " + exception.getMessage());
        } finally {
            // what does this do? finally will happen in any situation -
            // with or without error (clean up, wrap up etc.)
            if (this.getInfo("Do you want to add a new product? (yes / no):").equals("yes")) this.createProduct();
        }
    }

    private Product collectProductInfo() {
        Product product = new Product();
        product.setName(getInfo("Please enter product name"));
        product.setPrice(Double.parseDouble(this.getInfo("Please enter product price:")));
        product.setQuantity(Double.parseDouble(this.getInfo("Please enter product quantity:")));
        Category category = Category.valueOf(
                this.getInfo("Write product category (ELECTRONICS, FOOD, DRINK, CLOTHING, APPAREL): ").toUpperCase()
        );
        product.setCategory(category);

        product.setId(UUID.randomUUID());
        product.setAvailable(true);

        return product;
    }

//    private void addProduct() {
//        // add the product using product services
//
//    }

    public void displayMessage(String message) { // this is better way than sout
        System.out.println(message);
    }

    public String getInfo(String message) {
        System.out.println(message);
        //String userInput = scanner.nextLine(); - the same as under this
        return scanner.nextLine();
    }

}
