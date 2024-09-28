import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

// Product Class - Represent individual products with their attributes
// like productId, name, price and quantity.
class Product implements Serializable {
    private String productId;
    private String name;
    private int quantity;
    private double price;

    public Product(String productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter Methods
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Update quantity after an order
    public void reduceQuantity(int quantityOrdered) {
        this.quantity -= quantityOrdered;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity;
    }
}


// Cart Class - Stores selected products and their quantities
class Cart implements Serializable {
    private HashMap<String, Integer> cartItems = new HashMap<>();   // productId -> quantity

    public void addToCart(String productId, int quantity) {
        cartItems.put(productId, cartItems.getOrDefault(productId, 0) + quantity);
    }

    public HashMap<String, Integer> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }
}


// E-Commerce Platform
public class MiniECommercePlatform {
    private HashMap<String, Product> products = new HashMap<>();
    private Cart cart = new Cart();
    private static final String FILE_NAME = "products.dat";
    private Scanner scan = new Scanner(System.in);

    public MiniECommercePlatform() {
        loadProductsFromFile();
    }

    // Load Product
    @SuppressWarnings("unchecked")
    private void loadProductsFromFile() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            products = (HashMap<String, Product>) ois.readObject();
            System.out.println("Products loaded successfully !!!!!");
        } catch (FileNotFoundException e) {
            System.out.println("Product file not found. Starting with an empty product list.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading products !!!!!");
        }
    }


    // Save Products
    private void saveProductsToFile() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(products);
            System.out.println("Products saved successfully !!!!!");
        } catch (IOException e) {
            System.out.println("Error saving products !!!!!");
        }
    }

    // Add product to platform (Admin feature)
    public void addProduct() {

        System.out.println("Enter Product ID: ");
        String productId = scan.nextLine();

        System.out.println("Enter Product Name: ");
        String name = scan.nextLine();

        System.out.println("Enter Product Price: ");
        double price = scan.nextDouble();

        System.out.println("Enter Quantity: ");
        int quantity = scan.nextInt();

        Product product = new Product(productId, name, price, quantity);
        products.put(productId, product);
        saveProductsToFile();
    }


    // Browse Products
    public void browserProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products.values()) {
                System.out.println(product.toString());
            }
        }
    }


    // Add to Cart
    public void addToCart() {

        System.out.println("Enter Product ID to add to cart: ");
        String productId = scan.nextLine();

        if (products.containsKey(productId)) {
            Product product = products.get(productId);
            
            System.out.println("Enter Quantity: ");
            int quantity = scan.nextInt();
            if (product.getQuantity() >= quantity) {
                cart.addToCart(productId, quantity);
                System.out.println("Added to cart.");
            } else {
                System.out.println("Insufficient stock !!!!!");
            }
        } else {
            System.out.println("Product not found !!!!!");
        }
    }


    // View Cart
    public void viewCart() {
        HashMap<String, Integer> cartItems = cart.getCartItems();
        if (cartItems.isEmpty()) {
            System.out.println("Cart is Empty !!!!!");
        } else {
            double total = 0;
            for (String productId : cartItems.keySet()) {
                Product product = products.get(productId);
                int quantity = cartItems.get(productId);
                total += product.getPrice() * quantity;
                System.out.println("Product: " + product.getName() + ", Quantity: " + quantity + ", Subtotal: " + (product.getPrice() * quantity));
            }
            System.out.println("Total: $" + total);
        }
    }


    // Place Order
    public void placeOrder() {
        HashMap<String, Integer> cartItems = cart.getCartItems();
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty. Cannot place an order.");
        } else {
            for (String productId : cartItems.keySet()) {
                Product product = products.get(productId);
                int quantity = cartItems.get(productId);
                product.reduceQuantity(quantity);
            }
            cart.clearCart();
            saveProductsToFile();
            System.out.println("Order placed successfully !!!!!");
        }
    }

    // Main Program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MiniECommercePlatform platform = new MiniECommercePlatform();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n --------------------------------");
            System.out.println(" Mini E-Commerce Platform ");
            System.out.println("\n --------------------------------");

            System.out.println("1. Browse Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Place Order");
            System.out.println("5. Add Product (Admin)");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    platform.browserProducts();
                    break;
                
                case 2:
                    platform.addToCart();
                    break;

                case 3:
                    platform.viewCart();
                    break;

                case 4:
                    platform.placeOrder();
                    break;

                case 5:
                    platform.addProduct();
                    break;

                case 6:
                    exit = true;
                    System.out.println("Exiting Mini E-Commerce Platform.");
                    break;
            
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        sc.close();
    }
}