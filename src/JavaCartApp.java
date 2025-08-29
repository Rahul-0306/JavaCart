import database.Database;
import models.Cart;
import models.Product;
import utils.OutOfStockException;
import utils.ProductNotFoundException;

import java.util.List;
import java.util.Scanner;

public class JavaCartApp {
    static List<Product> productList;
    static Scanner scanner = new Scanner(System.in);
    static Cart cart = new Cart();

    public static void main(String[] args) {
        productList = Database.getAllProducts();

        while (true) {
            System.out.println("\n--- JavaCart Menu ---");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> viewProducts();
                case 2 -> addToCart();
                case 3 -> viewCart();
                case 4 -> checkout();
                case 0 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void viewProducts() {
        productList.forEach(System.out::println);
    }

    private static void addToCart() {
        System.out.print("Enter product ID: ");
        String id = scanner.next();
        try {
            Product product = findProductById(id);
            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();
            if (qty > product.getStock()) {
                throw new OutOfStockException("Only " + product.getStock() + " left in stock.");
            }
            cart.addItem(product, qty);
            System.out.println("Added to cart.");
        } catch (ProductNotFoundException | OutOfStockException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewCart() {
        System.out.println("Your Cart:");
        cart.getItems().forEach((p, qty) -> System.out.println(p + " x " + qty));
        System.out.println("Total: Rs." + cart.getTotalPrice());
    }

    private static void checkout() {
        System.out.println("Checkout successful! Total: Rs." + cart.getTotalPrice());
        // Here add multithreading & JDBC order persistence (optional for now)
        cart = new Cart(); // reset cart after checkout
    }

    private static Product findProductById(String id) throws ProductNotFoundException {
        return productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found."));
    }
}
