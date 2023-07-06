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

                break;
            case "4":
                //Delete Product

                break;
            case "5":
                // update product
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

    private void displayProducts() {
        ArrayList<Product> products = this.productService.getAllProducts();

        displayMessage("Name \t | Price \t | Quantity \t | Category \t | isAvailable \t | ID");
        for (Product currentProduct: products) {
            // do something with product
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
