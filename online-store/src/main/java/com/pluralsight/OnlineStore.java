package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OnlineStore {
    static ArrayList<Product> storeInventory = new ArrayList<>();
    static HashMap<String, Product> userCart = new HashMap<>();
    public static void main(String[] args) throws IOException {
        loadInventory();
        displayStoreHomeScreen();
    }

    public static void loadInventory() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("products.csv"));
        reader.readLine();
        String line;
        while((line = reader.readLine()) != null){
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

    public static void displayStoreHomeScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------------------------");
        System.out.println("\t\t\t\tWelcome to Javazon\t\t");
        System.out.println("\t\t - Your Ultimate Java Online Store! - \t");
        System.out.println("------------------------------------------------------");
        System.out.println("Options:");
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
//                displayUserCart(scanner);
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

    public static void displayStoreInventoryProducts(Scanner scanner){
        for(Product product : storeInventory){
            System.out.println(product);
        }
    }


}