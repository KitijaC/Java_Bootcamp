package product;



import product.dto.Category;
import product.dto.Product;

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
        System.out.println("""
        Welcome to Product Management
        please choose an option
           1. Add product
           2. View products
           3. Find product
           4. Delete Product
           5. Quit
        """);

        String userChoice = scanner.nextLine();

        switch (userChoice) {
            case "1":
                //Add product

                this.createProduct();
                break;
            case "2":
                //View products

                break;
            case "3":
                //Find product

                break;
            case "4":
                //Delete Product

                break;
            case "5":
                // Quit

                System.out.println("goodbye!");
                System.exit(0); // 0 means that everything is ok, but can set what number you want
                break;
            default:
                System.out.println("Please choose an option from the menu");
                break;
        }

        // recursion - process that repeats over and over again, like a loop
        this.start();


    }

    private void createProduct() {
        Product product = this.collectProductInfo();
        String result = this.productService.addProduct(product);
        System.out.println(result);
        // use product service to add product
    }

    private Product collectProductInfo() {
        Product product = new Product();

        System.out.println("Please enter product name");
        product.setName(this.scanner.nextLine());

        System.out.println("Please enter product price:");
        product.setPrice(Double.parseDouble(this.scanner.nextLine()));

        System.out.println("Please enter product quantity:");
        product.setQuantity(Double.parseDouble(this.scanner.nextLine()));

        System.out.println("Write product category (ELECTRONICS, FOOD, DRINK, CLOTHING, APPAREL): ");
        product.setCategory(Category.valueOf(this.scanner.nextLine().toUpperCase()));

        product.setId(UUID.randomUUID());
        product.setAvailable(true);

        return product;
    }

    private void addProduct() {
        // add the product using product services

    }



}
