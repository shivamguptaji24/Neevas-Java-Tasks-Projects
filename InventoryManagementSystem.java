import java.io.*;
import java.util.HashMap;
import java.util.Scanner;


// This class contains the core logic to handle the inventory items
public class InventoryManagementSystem {
    private HashMap<String, Item> inventory = new HashMap<>();
    private static final String File_NAME = "inventory.txt";
    private Scanner scan = new Scanner(System.in);

    public InventoryManagementSystem() {
        loadInventoryFromFile();
    }

    // Add new item
    public void addItem() {
        
        System.out.println("Enter Item ID: ");
        String id = scan.nextLine();

        System.out.println("Enter Item Name: ");
        String name = scan.nextLine();

        System.out.println("Enter Quantity: ");
        int quantity = scan.nextInt();

        System.out.println("Enter Price:");
        double price = scan.nextDouble();

        Item item = new Item(id, name, quantity, price);
        inventory.put(id, item);
        System.out.println("Item added successfully !!!!!");
        saveInventoryToFile();
    }

    // Update existing item
    public void updateItem() {

        System.out.println("Enter Item ID to update: ");
        String id = scan.nextLine();
        
        if (inventory.containsKey(id)) {
            System.out.println("Enter new Quantity: ");
            int quantity = scan.nextInt();
            System.out.println("Enter new Price: ");
            double price = scan.nextDouble();

            Item item = inventory.get(id);
            item.setQuantity(quantity);
            item.setPrice(price);
            System.out.println("Item updated successfully !!!!!");
            saveInventoryToFile();
        } else {
            System.out.println("Item not found !!!!!");
        }
    }

    // View all items
    public void viewInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty !!!!!");
        } else {
            for (Item item : inventory.values()) {
                System.out.println(item.toString());
            }
        }
    }

    // Save inventory data to a file
    private void saveInventoryToFile() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(File_NAME))) {
            oos.writeObject(inventory);
            System.out.println("Inventory saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving inventory to file !!!!!");
        }
    }

    // Load inventory data from a file
    @SuppressWarnings("unchecked")
    private void loadInventoryFromFile() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(File_NAME))) {
            inventory = (HashMap<String, Item>) ois.readObject();
            System.out.println("Inventory loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found. Starting with an empty inventory.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading inventory from file !!!!!");
        }
    }
}


// This class contains inventory items with their properties like item ID, name, quantity, and price.
class Item implements Serializable {
    private String itemId;
    private String name;
    private int quantity;
    private double price;

    public Item(String itemId, String name, int quantity, double price) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter and Setter methods
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item ID: " + itemId + ", Name: " + name + ", Quantity: " + quantity + ", Price: " + price;
    }
}


// Main Class
class Main {
    public static void main(String[] args) {
        InventoryManagementSystem inventoryManagement = new InventoryManagementSystem();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n --------------------------------");
            System.out.println(" Inventory Management System ");
            System.out.println("\n --------------------------------");

            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. View Inventory");
            System.out.println("4. Exit");

            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    inventoryManagement.addItem();
                    break;
            
                case 2:
                    inventoryManagement.updateItem();
                    break;

                case 3:
                    inventoryManagement.viewInventory();
                    break;

                case 4:
                    exit = true;
                    System.out.println("Exiting Inventory Management System.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        sc.close();
    }
}