package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OnlineStore {
    static ArrayList<Product> storeInventory = new ArrayList<>();
    static HashMap<String, CartItem> userCart = new HashMap<>();

    // This method throws 2 Expected Errors
    public static void main(String[] args) throws IOException, InterruptedException {
        // Load inventory
        loadInventory();
        // Start the program
        displayStoreHomeScreen();

    }

    // Read each line from CSV for products list
    public static void loadInventory() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("products.csv"));
        // Skip the headers to start at the next line
        reader.readLine();
        // Line variable will be our reader
        String line;
        // While the line it reads each time does not equal it will continue to read
        while ((line = reader.readLine()) != null) {
            // Split the products by the (|)
            String[] productsFields = line.split(Pattern.quote("|"));
            // Apply the new filtered Class of product to a method
            // Filtered items will be parsed and will create a new item to the store
            // inventory
            storeInventory.add(createFormattedClassFromFields(productsFields));
        }
        // Close reader after use
        reader.close();
    }

    // Pass the array of fields to use to create an object
    public static Product createFormattedClassFromFields(String[] productsFields) {
        // Split each value from array to a variable
        String productSKU = productsFields[0];
        String productName = productsFields[1];
        // Parse String value to a double
        double productPrice = Double.parseDouble(productsFields[2]);
        String productDepartment = productsFields[3];
        // This method will return a new object everytime its called with the correct
        // fields
        return new Product(productSKU, productName, productPrice, productDepartment);
    }

    // Display the home screen for user
    // Scanner will get passed through the whole program
    // This method throws 2 Expected Errors
    public static void displayStoreHomeScreen() throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        // Welcome user
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
        // The input the user chooses, it will direct them like a store
        // If wrong value is issued it will redirect them to home screen
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

    // Display all items form the storeInventory ArrayList
    // This method throws 2 Expected Errors
    public static void displayStoreInventoryProducts(Scanner scanner) throws InterruptedException, IOException {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\tJavazon Shopping Directory");
        System.out
                .println("----------------------------------------------------------------------------------------\n");
        // Print each value from the ArrayList with the objects .toString override
        for (Product product : storeInventory) {
            System.out.println(product);
            System.out.println(
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        System.out.println();
        // Ask the user what they will like to do next
        System.out.println("Shopping Options:");
        System.out.println("1- Search Products");
        System.out.println("2- Add Product To Cart");
        System.out.println("3- Return Back");
        System.out.print("Please choose an option from above: ");
        String userChoice = scanner.nextLine().trim().toLowerCase();
        // The input the user chooses, it will direct them like a store
        // If the wrong value is issued it will redirect them to again to the options
        // above
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

    // Pass scanner so user can look items by any keyword
    // This method throws 2 Expected Errors
    public static void searchInventoryProducts(Scanner scanner) throws InterruptedException, IOException {
        // User input will be catched here
        System.out.print("\nEnter search keyword: ");
        String searchKeyword = scanner.nextLine().trim().toLowerCase();
        // Flag used for the condition checking below
        boolean itemFound = false;
        // Print user results
        System.out.println("\n------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\tSearch Results");
        System.out.println("------------------------------------------------------------------------");
        System.out.println();
        // Loop through the ArrayList
        // Check if the user input contains the value within the ArrayList
        for (Product product : storeInventory) {
            if (product.getProductName().toLowerCase().contains(searchKeyword) ||
                    product.getProductDepartment().toLowerCase().contains(searchKeyword) ||
                    product.getProductSKU().toLowerCase().contains(searchKeyword)) {
                // Change Flag to true for condition checking below
                itemFound = true;
                // Print each value from the ArrayList with the objects .toString override
                System.out.println(product);
            }
        }
        // If flag is false report that no products are found
        if (!itemFound) {
            System.out.println("Sorry that product doesn't exist in our inventory. Please try again.");
            // Prompt search again
            searchInventoryProducts(scanner);
        } else {
            // Ask user how they would like to continue
            System.out.println();
            // Ask users its options to continue running program
            System.out.print("Would you like to add one of the following to your cart? (yes/no) ");
            String addToCartOption = scanner.nextLine().trim();
            // Prompt user to add items to cart
            if (addToCartOption.equalsIgnoreCase("yes")) {
                addToUserCart(scanner);
                // Error handle and prompt user to continue to shop while the program still runs
            } else if (addToCartOption.equalsIgnoreCase("no")) {
                System.out.println("What would you like to do? ");
                System.out.println("-1- Search Another Product");
                System.out.println("-Else- Go Back Home");
                System.out.print("Enter Option: ");
                String userSearchRedo = scanner.nextLine().trim();
                if (userSearchRedo.equals("1")) {
                    searchInventoryProducts(scanner);
                } else {
                    displayStoreHomeScreen();
                }
            }
        }
    }

    // Pass scanner so user can add items by SKU #
    // This method throws 2 Expected Errors
    public static void addToUserCart(Scanner scanner) throws InterruptedException, IOException {
        // Use while loop to keep running until it reaches a break
        while (true) {
            // Ask user for SKU NUMBER
            System.out.print("Enter the SKU number of the product you'd like to add to your cart: ");
            // Convert to an upper case for consistency
            String userCartInput = scanner.nextLine().trim().toLowerCase();
            // Flag to track if user wants to add another item
            boolean addItemFlag = false;
            // Loop through the ArrayList to see if SKU exist
            for (Product product : storeInventory) {
                if (product.getProductSKU().toLowerCase().equals(userCartInput)) {
                    // If the SKU already exists in the cart
                    if (userCart.containsKey(userCartInput)) {
                        // If the value exist in the HashMap, dont add another value. Only update
                        // quantity
                        CartItem updateCurrentItem = userCart.get(userCartInput);
                        // Increment the quantity
                        updateCurrentItem.incrementQuantity();
                    } else {
                        // If the SKU doesn't exist in the cart
                        // Add new entry with quantity 1
                        userCart.put(userCartInput, new CartItem(product, 1));
                    }
                    System.out.println("\n------------------------------------------------------");
                    System.out.println("\t\tProduct added to cart successfully!");
                    System.out.println("------------------------------------------------------");
                    // Print out the current item in the cart added
                    System.out.println(userCart.get(userCartInput));
                    System.out.println();
                    // Set flag to true since user added an item
                    addItemFlag = true;
                    break; // Break out of the loop for adding another item
                }
            }
            // If user didn't add an item
            if (!addItemFlag) {
                System.out.println("------------------------------------------------------------------------");
                System.out.println("\t\t\t\tProduct not found in inventory!");
                System.out.println("------------------------------------------------------------------------\n");
                while (true) {
                    // Keep running till breaking out program
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
                    // Keep running till breaking out program
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

    // Pass scanner so user can select its following options
    // This method throws 2 Expected Errors
    public static void displayUserCart(Scanner scanner) throws InterruptedException, IOException {
        // If user HashMap cart isnt empty then print cart values
        if (!userCart.isEmpty()) {
            System.out.println("------------------------------------------------------");
            System.out.println("\t\tMy Shopping Cart");
            System.out.println("------------------------------------------------------");
            System.out.println();
            // get all the values ONLY in the HashMap and print it
            // Print each value from the HashMap with the objects .toString override
            for (CartItem item : userCart.values()) {
                System.out.println(item);
            }
            // Ask user how they want to continue
            System.out.println();
            System.out.println("What would you like to do? ");
            System.out.println("-1- Check Out");
            System.out.println("-2- Remove Product");
            System.out.println("-Else- Go Home");
            System.out.print("Enter Option: ");
            String userSearchRedo = scanner.nextLine().trim();
            // Have user choose its next path
            // Error handle for different answer
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
        } else {
            // If the length of the HashMap is zero then print no items in cart
            System.out.println("------------------------------------------------------");
            System.out.println("\t\t\t\t0 Items Found In Cart!");
            System.out.println("------------------------------------------------------\n");
            while (true) {
                // While loop until the user breaks out the option.
                // Goal is to have the program continue
                System.out.println("What would you like to do? ");
                System.out.println("-1- Go Home");
                System.out.println("-Else- Exit");
                System.out.print("Enter Option: ");
                String userChoiceForReturn = scanner.nextLine().trim();
                if (userChoiceForReturn.equals("1")) {
                    System.out.println();
                    displayStoreHomeScreen();
                } else {
                    System.out.println("Exiting....");
                    return;
                }
            }
        }
    }

    // Pass scanner so user can select its following options
    // This method throws 2 Expected Errors
    public static void removeUserProduct(Scanner scanner) throws InterruptedException, IOException {
        System.out.println("What would you like to do?");
        System.out.println("-1- Remove specific item from cart");
        System.out.println("-2- Remove all items from cart");
        System.out.println("-Else- Go back home");
        System.out.print("Enter your command: ");
        String userChoice = scanner.nextLine().trim();
        // Have user choose its next path
        // Error handle for different answer
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

    // Pass scanner so user can select its following options
    // This method throws 2 Expected Errors
    public static void removeSpecificItemFromCart(Scanner scanner) throws InterruptedException, IOException {
        // Ask the user for the item in the cart they want to remove
        // Remove by SKU
        System.out.print("Enter the SKU number of the product you'd like to remove from your cart: ");
        String userRemovalChoice = scanner.nextLine().trim().toLowerCase();
        // Use a flag for the following condition operations
        boolean skuItemInCart = false;
        // Loop through the HashMap by its keySET
        for (String key : userCart.keySet()) {
            // If key equals a user option then remove
            if (key.equalsIgnoreCase(userRemovalChoice)) {
                // Get the current Object
                CartItem cartItem = userCart.get(key);
                // If the Product exists already, then check its quantity
                if (cartItem.getQuantity() > 1) {
                    // Decrement quantity by 1 if true
                    cartItem.decrementQuantity();
                } else {
                    // Remove item from cart if quantity is 1
                    userCart.remove(key);
                }
                // Turn the flag to true for the following condition operations
                skuItemInCart = true;
                // Break out the loop as soon as the item is found.
                break;
            }
        }
        // If user had items in cart and updated the cart, prompt user with message
        if (skuItemInCart) {
            System.out.println("------------------------------------------------------");
            System.out.println("\t\t\t\tUpdated Cart!");
            System.out.println("------------------------------------------------------");
            // Loop through the HashMap by its values to print out the new updated cart
            for (CartItem item : userCart.values()) {
                System.out.println(item);
            }
            // Ask user what they would like to do next
            System.out.println("What would you like to do?");
            System.out.println("-1- Check out");
            System.out.println("-2- Remove another specific item from cart");
            System.out.println("-Else- Return Home");
            System.out.print("Enter your choice: ");
            String userChoice = scanner.nextLine().trim();
            // Have user choose its next path
            // Error handle for different answer
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
        // If user enters SKU # not found then return error
        if (!skuItemInCart) {
            System.out.println("\nLooks like that item isn't currently in your cart\n");
            System.out.println("What would you like to do?");
            System.out.println("-1- Remove specific item from cart");
            System.out.println("-2- Check out");
            System.out.println("-Else- Return Home");
            System.out.print("Enter your choice: ");
            String userChoice = scanner.nextLine().trim();
            // Have user choose its next path
            // Error handle for different answer
            switch (userChoice) {
                case "1":
                    removeSpecificItemFromCart(scanner);
                    break;
                case "2":
                    checkOutUserCart(scanner);
                    break;
                default:
                    displayStoreHomeScreen();
                    break;
            }
        }

    }

    // Have user clear their cart
    // Goal is to simulate a real cart with the option to clear items added
    public static void removeAllItemsFromCart() throws InterruptedException, IOException {
        // Use the HashMap method to clear all items at once
        userCart.clear();
        // Update user on what to do next
        System.out.println("------------------------------------------------------");
        System.out.println("\t\t\t\t0 Items In Cart!");
        System.out.println("------------------------------------------------------");
        System.out.println("\t\t\tDirecting you back Home....\n\n");
        // Simulate a browser like reaction to going back home
        Thread.sleep(2500);
        // Bring back home
        displayStoreHomeScreen();
    }

    // Pass scanner so user can select its following options
    // This method throws 2 Expected Errors
    public static void checkOutUserCart(Scanner scanner) throws InterruptedException, IOException {
        // Have a total price counter to get the values of all products
        double totalPrice = 0.0;
        // If cart is NOT empty then tally up the sum of all products in cart
        if (!userCart.isEmpty()) {
            System.out.println("------------------------------------------------------");
            System.out.println("\t\tCheck Out");
            System.out.println("------------------------------------------------------");
            System.out.println();
            // Loop through the HashMap by its values to print out the current cart items
            for (CartItem item : userCart.values()) {
                System.out.println(item);
                // Add the total price for each item to the total
                totalPrice += item.getTotalPrice();
            }
            // Pass scanner so user can select its following options
            // Pass the totalPrice sum to display in the following methods
            displayTotalAndPrint(totalPrice, scanner);
        } else {
            // If user cart is empty, then prompt them cart is empty and direct them home
            System.out.println("------------------------------------------------------");
            System.out.println("\t\t\t\t0 Items In Cart!");
            System.out.println("------------------------------------------------------");
            System.out.println("\t\t\tDirecting you back Home....\n\n");
            // Simulate a browser like reaction to going back home
            Thread.sleep(2500);
            displayStoreHomeScreen();
            // Bring back home
        }
    }

    // Pass scanner so user can select its following options
    // This method throws 2 Expected Errors
    // Total Sum of all products passed as an argument
    public static void displayTotalAndPrint(double totalPrice, Scanner scanner)
            throws InterruptedException, IOException {
        // Display to user the total of all products
        System.out.println("------------------------------------------------------");
        System.out.printf("\t\t\t\tTotal Price: $%,.2f\n", totalPrice);
        System.out.println("------------------------------------------------------");
        // Make variable to see how much the user submits as payment
        // Initialize total payment to 0
        double totalPayment = 0.0;

        // While loop till loop breaks
        // Loops break until user meets the payment of total
        while (true) {
            // Have user apply the amount they will pay
            System.out.print("Please insert cash amount here to pay: ");
            double payment = scanner.nextDouble();
            // Accumulate the payment
            totalPayment += payment;

            // If the total payment is less the total figure out how much is left
            if (totalPayment < totalPrice) {
                double totalLeft = totalPrice - totalPayment;
                System.out.printf("You've paid $%.2f, but you still owe $%.2f%n", totalPayment, totalLeft);
            } else if (totalPayment > totalPrice) {
                // If total payment is greater than total price then display the change needed
                // to be given
                // Pass all the values to print the receipt
                double change = totalPayment - totalPrice;
                System.out.println("\nThank you for your payment!");
                printReceipt(change, totalPrice, totalPayment);
                // Exit the loop since the total payment is greater than or equal to the total
                // price
                break;
            } else {
                // If no change is given, then set it to zero
                // Pass the other values to method
                System.out.println("\nThank you for your payment!");
                printReceipt(0.00, totalPrice, totalPayment);
                // Exit the loop if the total payment equals the total price
                break;
            }
        }

    }

    // Pass scanner so user can select its following options
    // This method throws 2 Expected Errors
    // Pass all the arguments to print values to the user command line
    public static void printReceipt(double change, double totalPrice, double totalPayment)
            throws InterruptedException, IOException {
        // Create a variable to save the date as a String
        String formattedDate;
        // Get Current date
        LocalDateTime date = LocalDateTime.now();
        // Format date by Day, Month, Day, Year, and time by hour and minutes.
        DateTimeFormatter myFormattedDate = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy HH:mm");
        // Apply formatted string to variable declaration
        formattedDate = myFormattedDate.format(date.atZone(ZoneId.of("GMT")));
        // Print the receipt
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Receipt:");
        // Print Order Date
        System.out.printf("Order Date: %s%n", formattedDate);
        // Print all items in cart
        System.out.println("Items Purchased:");
        // Get all the values Only from the HashMap and print them
        for (CartItem cartItem : userCart.values()) {
            System.out.println("- " + cartItem);
        }
        // Print Sales Total
        System.out.printf("\t-Sales Total: $%.2f%n", totalPrice);
        // Print Amount Paid
        System.out.printf("\t-Amount Paid: $%.2f%n", totalPayment);
        // Print Change Given
        System.out.printf("\t-Change Given: $%.2f%n", change);
        // Pass all the values to file Formatter to pass the receipt to sales file
        salesFileMaker(formattedDate, change, totalPrice, totalPayment);
        // Clear all HashMap values with its clear method
        userCart.clear();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Thank you come again");
        System.out.println("Directing you back Home....\n\n");
        // Simulate a browser like reaction to going back home
        Thread.sleep(2500);
        // Bring back home
        displayStoreHomeScreen();
    }

    // Make file with the variables of receipt
    public static void salesFileMaker(String formattedDate, double change, double totalPrice, double totalPayment)
            throws IOException {
        // Format file name by year, month, day, hour, min
        String filename = LocalDateTime.parse(formattedDate, DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy HH:mm"))
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        // Safe file in folder to hold receipts
        String filePath = "/Users/butterflycoupe/Desktop/YearUp/online-store/online-store/Receipts/" + filename
                + ".txt";
        // Write to file line by line
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        // Format the file by writing in specific format
        writer.write("Purchase Date - " + formattedDate);
        writer.newLine();
        writer.write("Purchased Items");
        writer.newLine();
        // Get all the values Only from the HashMap and writethem
        for (CartItem cartItem : userCart.values()) {
            writer.write(" - " + cartItem);
            writer.newLine();
        }
        // Write the following data
        writer.write(String.format("-Sales Total - $%.2f", totalPrice));
        writer.newLine();
        writer.write(String.format("-Amount Paid - $%.2f", totalPayment));
        writer.newLine();
        writer.write(String.format("-Change Given - $%.2f", change));
        // Close writer
        writer.close();
    }
}
