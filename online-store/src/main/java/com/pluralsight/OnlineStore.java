package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OnlineStore {
    static ArrayList<Product> storeInventory = new ArrayList<>();
    static HashMap<String, CartItem> userCart = new HashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        loadInventory();
        displayStoreHomeScreen();

    }

    public static void loadInventory() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("products.csv"));
        reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] productsFields = line.split(Pattern.quote("|"));
            storeInventory.add(createFormattedClassFromFields(productsFields));
        }
        reader.close();
    }

    public static Product createFormattedClassFromFields(String[] productsFields) {
        String productSKU = productsFields[0];
        String productName = productsFields[1];
        double productPrice = Double.parseDouble(productsFields[2]);
        String productDepartment = productsFields[3];
        return new Product(productSKU, productName, productPrice, productDepartment);
    }

    public static void displayStoreHomeScreen() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------------------------");
        System.out.println("\t\t\t\tWelcome to Javazon\t\t");
        System.out.println("\t\t - Your Ultimate Java Online Store! - \t");
        System.out.println("------------------------------------------------------");
        System.out.println("Shopping Options:");
        System.out.println("1- Display Products");
        System.out.println("2- Display Cart");
        System.out.println("3- Exit");
        System.out.print("Please choose an option from above: ");
        String userChoice = scanner.nextLine().trim().toLowerCase();
        System.out.println();
        switch (userChoice) {
            case "1":
                displayStoreInventoryProducts(scanner);
                break;
            case "2":
                displayUserCart(scanner);
                break;
            case "3":
                System.out.println("Exiting....");
                break;
            default:
                System.out.println("Sorry, please choose one of the options displayed");
                displayStoreHomeScreen();
                break;
        }
    }

    public static void displayStoreInventoryProducts(Scanner scanner) throws InterruptedException {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\tJavazon Shopping Directory");
        System.out.println("----------------------------------------------------------------------------------------\n");
        for (Product product : storeInventory) {
            System.out.println(product);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        System.out.println();
        System.out.println("Shopping Options:");
        System.out.println("1- Search Products");
        System.out.println("2- Add Product To Cart");
        System.out.println("3- Return Back");
        System.out.print("Please choose an option from above: ");
        String userChoice = scanner.nextLine().trim().toLowerCase();
        switch (userChoice) {
            case "1":
                searchInventoryProducts(scanner);
                break;
            case "2":
                addToUserCart(scanner);
                break;
            case "3":
                displayStoreHomeScreen();
                break;
            default:
                System.out.println("Sorry, please choose one of the options displayed");
                displayStoreInventoryProducts(scanner);
                break;
        }
    }

    public static void searchInventoryProducts(Scanner scanner) throws InterruptedException {
        System.out.print("\nEnter search keyword: ");
        String searchKeyword = scanner.nextLine().trim().toLowerCase();
        boolean itemFound = false;
        for (Product product : storeInventory) {
            if (product.getProductName().toLowerCase().contains(searchKeyword) ||
                    product.getProductDepartment().toLowerCase().contains(searchKeyword) ||
                    product.getProductSKU().toLowerCase().contains(searchKeyword)) {
                itemFound = true;
                System.out.println(product);
            }
        }

        if (!itemFound) {
            System.out.println("Sorry that product doesn't exist in our inventory. Please try again.");
            searchInventoryProducts(scanner);
        } else {
            System.out.print("Would you like to add one of the following to your cart? (yes/no) ");
            String addToCartOption = scanner.nextLine().trim();
            if (addToCartOption.equalsIgnoreCase("yes")) {
                addToUserCart(scanner);
            } else if (addToCartOption.equalsIgnoreCase("no")) {
                System.out.println("What would you like to do? ");
                System.out.println("-1- Search Another Product");
                System.out.println("-Else- Go Back Home");
                System.out.print("Enter Option: ");
                String userSearchRedo = scanner.nextLine().trim();
                switch (userSearchRedo) {
                    case "1":
                        searchInventoryProducts(scanner);
                        break;
                    default:
                        displayStoreHomeScreen();
                        break;
                }
            }
        }
    }

    public static void addToUserCart(Scanner scanner) throws InterruptedException {
        while (true) {
            System.out.print("Enter the SKU number of the product you'd like to add to your cart: ");
            String userCartInput = scanner.nextLine().trim().toLowerCase(); // Convert to an upper case for consistency

            boolean addItemFlag = false; // Flag to track if user wants to add another item

            for (Product product : storeInventory) {
                if (product.getProductSKU().toLowerCase().equals(userCartInput)) {
                    if (userCart.containsKey(userCartInput)) { // If the SKU already exists in the cart
                        CartItem updateCurrentItem = userCart.get(userCartInput);
                        updateCurrentItem.incrementQuantity(); // Increment the quantity
                    } else { // If the SKU doesn't exist in the cart
                        userCart.put(userCartInput, new CartItem(product, 1)); // Add new entry with quantity 1
                    }
                    System.out.println("Product added to cart successfully!");
                    System.out.println(userCart);
                    addItemFlag = true; // Set flag to true since user added an item
                    break; // Break out of the loop for adding another item
                }
            }

            if (!addItemFlag) { // If user didn't add an item
                System.out.println("Product not found in inventory.");
                while (true) {
                    System.out.print("Would you like to try again? (yes/no) ");
                    String tryAgain = scanner.nextLine().trim().toLowerCase();
                    if (tryAgain.equals("yes")) {
                        break;
                    } else if (tryAgain.equals("no")) {
                        displayStoreHomeScreen();
                        return;
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                }
            }

            // Prompt user to add another item only if they added an item previously
            if (addItemFlag) {
                while (true) {
                    System.out.print("Would you like to add another item to your cart? (yes/no) ");
                    String addAnotherItem = scanner.nextLine().trim().toLowerCase();
                    if (addAnotherItem.equals("yes")) {
                        break;
                    } else if (addAnotherItem.equals("no")) {
                        displayStoreHomeScreen();
                        return;
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                }
            }

        }
    }


    public static void displayUserCart(Scanner scanner) throws InterruptedException {
        if(userCart.size() > 0){
            for(CartItem item : userCart.values()){
                System.out.println(item);
            }
            System.out.println("What would you like to do? ");
            System.out.println("-1- Check Out");
            System.out.println("-2- Remove Product");
            System.out.println("-Else- Go Home");
            System.out.print("Enter Option: ");
            String userSearchRedo = scanner.nextLine().trim();
            switch (userSearchRedo) {
                case "1":
                    System.out.println();
                    checkOutUserCart(scanner);
                    break;
                case "2":
                    System.out.println();
                    removeUserProduct(scanner);
                    break;
                default:
                    System.out.println();
                    displayStoreHomeScreen();
                    break;
            }
        }else{
            System.out.println("------------------------------------------------------");
            System.out.println("\t\t\t\t0 Items Found In Cart!");
            System.out.println("------------------------------------------------------\n");
            while(true){
                System.out.println("What would you like to do? ");
                System.out.println("-1- Go Home");
                System.out.println("-Else- Exit");
                System.out.print("Enter Option: ");
                String userChoiceForReturn = scanner.nextLine().trim();
                switch (userChoiceForReturn) {
                    case "1":
                        System.out.println();
                        displayStoreHomeScreen();
                        break;
                    default:
                        System.out.println("Exiting....");
                        return;
                }
            }
        }
    }

    public static void removeUserProduct(Scanner scanner) throws InterruptedException {
        System.out.println("What would you like to do?");
        System.out.println("-1- Remove specific item from cart");
        System.out.println("-2- Remove all items from cart");
        System.out.println("-Else- Go back home");
        System.out.print("Enter your command: ");
        String userChoice = scanner.nextLine().trim();
        switch (userChoice) {
            case "1":
                System.out.println();
                removeSpecificItemFromCart(scanner);
                break;
            case "2":
                System.out.println();
                removeAllItemsFromCart();
                break;
            default:
                displayStoreHomeScreen();
                break;
        }
    }

    public static void removeSpecificItemFromCart(Scanner scanner) throws InterruptedException {
        System.out.print("Enter the SKU number of the product you'd like to remove from your cart: ");
        String userRemovalChoice = scanner.nextLine().trim().toLowerCase();
        boolean skuItemInCart = false;

        for (String key : userCart.keySet()) {
            if (key.equalsIgnoreCase(userRemovalChoice)) {
                CartItem cartItem = userCart.get(key);
                if (cartItem.getQuantity() > 1) {
                    // Decrement quantity by 1
                    cartItem.decrementQuantity();
                } else {
                    // Remove item from cart if quantity is 1
                    userCart.remove(key);
                }
                skuItemInCart = true;
                break;
            }
        }

        if(skuItemInCart){
            System.out.println("------------------------------------------------------");
            System.out.println("\t\t\t\tUpdated Cart!");
            System.out.println("------------------------------------------------------");

            for(CartItem item : userCart.values()){
                System.out.println(item);
            }
            System.out.println("What would you like to do?");
            System.out.println("-1- Check out");
            System.out.println("-2- Remove another specific item from cart");
            System.out.println("-Else- Return Home");
            System.out.print("Enter your choice: ");
            String userChoice = scanner.nextLine().trim();
            switch (userChoice) {
                case "1":
                       checkOutUserCart(scanner);
                    break;
                case "2":
                    removeSpecificItemFromCart(scanner);
                    break;
                default:
                   displayStoreHomeScreen();
                    break;
            }

        }

        if(!skuItemInCart){
            System.out.println("Looks like that item isn't currently in your cart");
            System.out.println("What would you like to do?");
            System.out.println("-1- Remove specific item from cart");
            System.out.println("-2- Check out");
            System.out.println("-Else- Return Home");
            System.out.print("Enter your choice: ");
            String userChoice = scanner.nextLine().trim();
            switch (userChoice) {
                case "1":
                    removeSpecificItemFromCart(scanner);break;
                case "2":
                       checkOutUserCart(scanner);
                    break;
                default:
                    displayStoreHomeScreen();
                    break;
            }
        }



    }

    public static void removeAllItemsFromCart() throws InterruptedException {
        userCart.clear();
        System.out.println("------------------------------------------------------");
        System.out.println("\t\t\t\t0 Items In Cart!");
        System.out.println("------------------------------------------------------");
        System.out.println("\t\t\tDirecting you back Home....\n\n");
        Thread.sleep(2500);
        displayStoreHomeScreen();
    }

    public static void checkOutUserCart(Scanner scanner) throws InterruptedException {
        double totalPrice = 0.0;
        if (userCart.size() > 0) {
            for (CartItem item : userCart.values()) {
                System.out.println(item);
                totalPrice += item.getTotalPrice(); // Add the total price for each item to the total
            }
            displayTotalAndPrint(totalPrice, scanner);
        } else {
            System.out.println("------------------------------------------------------");
            System.out.println("\t\t\t\t0 Items In Cart!");
            System.out.println("------------------------------------------------------");
            System.out.println("\t\t\tDirecting you back Home....\n\n");
            Thread.sleep(2500);
            displayStoreHomeScreen();
        }
    }

    public static void displayTotalAndPrint(double totalPrice, Scanner scanner) {
        System.out.println("------------------------------------------------------");
        System.out.printf("\t\t\t\tTotal Price: $%,.2f", totalPrice);
        System.out.println("------------------------------------------------------");

        while (true) {
            System.out.println("Please insert cash amount here to pay: ");
            double payment = scanner.nextDouble();

            if (payment < totalPrice) {
                double totalLeft = totalPrice - payment;
                System.out.printf("Sorry, you still owe $%.2f%n", totalLeft);
            } else if (payment > totalPrice) {
                double change = payment - totalPrice;
                System.out.printf("Thank you for your payment! Your change is $%.2f%n", change);
                break; // Exit the loop since the payment is greater than the total price
            } else {
                System.out.println("Thank you for your payment!");
                break; // Exit the loop if the payment equals the total price
            }
        }
    }

    public static void printReceipt(double change,  double totalPrice) {
        String formattedDate = "";
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter myFormattedDate = DateTimeFormatter.ofPattern("h:mm 'on' dd-MMM-yyyy", Locale.US);
        formattedDate = myFormattedDate.format(date.atZone(ZoneId.systemDefault()));
        System.out.println(formattedDate);
        // Print the receipt
        System.out.println("Receipt:");
        System.out.println("--------");
        // Order Date
        System.out.printf("Order Date: ", formattedDate);
        // Line items
        System.out.println("Line Items:");
        for (String item : lineItems) {
            System.out.println("- " + item);
        }
        // Sales Total
        System.out.printf("Sales Total: $%.2f%n", salesTotal);
        // Amount Paid
        System.out.printf("Amount Paid: $%.2f%n", amountPaid);
        // Change Given
        System.out.printf("Change Given: $%.2f%n", changeGiven);
    }



}
