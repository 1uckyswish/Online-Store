# Java Online Store

Welcome to the Javazon Online Store Repository! Javazon Online Store is a user-friendly command-line application that offers a seamless shopping experience, enabling users to conveniently browse, add items to their cart, and manage purchases directly from their terminal.

## Main Class (Home)

This is the heart of our Javazon Online Store project. The Main class manages all store functionalities. Check out the Maven Java folder structure on the left to see how the project is organized.

![MavenShop](https://github.com/1uckyswish/online-store/assets/107442415/0d484dda-4c00-4e9e-b568-a4860a6d8ec9)


## Start of Program

This is the main screen of the Javazon Online Store interface, offering users a variety of options to explore.

```bash
------------------------------------------------------
         Welcome to Javazon
- Your Ultimate Java Online Store! - 	
------------------------------------------------------
Shopping Options:
1- Display Products
2- Display Cart
3- Exit
Please choose an option from above: 
```

## Option 1 Home Screen: Display All Products in Javazon Online Store Inventory

This option provides a comprehensive list of all products available in the Javazon Online Store inventory. Users can conveniently browse through the selection and choose their desired items for purchase.

```bash
----------------------------------------------------------------------------------------
	                    Javazon Shopping Directory
----------------------------------------------------------------------------------------

SKU: AV1051 | Name: JBL Bluetooth Speaker | Price: $89.95 | Department: Audio Video
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
SKU: AV1312 | Name: Mini 1000 Lumens Projector | Price: $149.95 | Department: Audio Video
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
SKU: AV1412 | Name: XLR Podcast Cardiod Mic | Price: $44.99 | Department: Audio Video
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
SKU: CP2012 | Name: Desktop PC Computer Intel Core i5 | Price: $139.00 | Department: Computers
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
SKU: CP2123 | Name: Wired Backlit Keyboard | Price: $21.99 | Department: Computers
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
SKU: CP2154 | Name: RGB Wireless Gaming Mouse | Price: $67.45 | Department: Computers
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
SKU: GM1032 | Name: Telestrations Board Game | Price: $17.99 | Department: Games
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
SKU: GM1075 | Name: Battleship Board Game | Price: $12.99 | Department: Games
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
SKU: GM1148 | Name: Retro Handheld Arcade | Price: $24.45 | Department: Games
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
SKU: PW1255 | Name: USB C to A Cable | Price: $13.95 | Department: Electronics
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
SKU: PW1341 | Name: Solar Powered Battery Charger | Price: $19.99 | Department: Electronics
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
SKU: PW1431 | Name: Wireless Charger iPhone | Price: $12.99 | Department: Electronics
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Shopping Options:
1- Search Products
2- Add Product To Cart
3- Return Back
Please choose an option from above:  
```

## Option 1 Menu: Proceed to Search for Products in the Inventory

This option allows users to search for products based on SKU, product names, price, and department. Users can input the relevant information to find the desired items in the inventory.

```bash
Enter search keyword: Games
------------------------------------------------------------------------
                          Search Results
------------------------------------------------------------------------
SKU: GM1032 | Name: Telestrations Board Game | Price: $17.99 | Department: Games
SKU: GM1075 | Name: Battleship Board Game | Price: $12.99 | Department: Games
SKU: GM1148 | Name: Retro Handheld Arcade | Price: $24.45 | Department: Games

Would you like to add one of the following to your cart? (yes/no) 
```

## Option 2 Sub Menu: Add Products to Cart by SKU Number

This option allows you to add products to your cart based on their SKU. After adding an item, it displays the updated quantity of the newly added product in your cart.

```bash
Would you like to add one of the following to your cart? (yes/no) yes
Enter the SKU number of the product you like to add to your cart: GM1075

------------------------------------------------------
          Product added to cart successfully!
------------------------------------------------------
Product: SKU: GM1075 | Name: Battleship Board Game | Price: $12.99 | Department: Games | Quantity: 1

Would you like to add another item to your cart? (yes/no) NO
```

## Option 2 Home Screen: Proceed to View Shopping Cart

This option allows users to input the ID of a book they wish to return. Upon returning the book, users are greeted with a thank-you message expressing gratitude for their return.
```bash
------------------------------------------------------
                    My Shopping Cart
------------------------------------------------------

Product: SKU: GM1075 | Name: Battleship Board Game | Price: $12.99 | Department: Games | Quantity: 1
Product: SKU: PW1431 | Name: Wireless Charger iPhone | Price: $12.99 | Department: Electronics | Quantity: 1
Product: SKU: AV1051 | Name: JBL Bluetooth Speaker | Price: $89.95 | Department: Audio Video | Quantity: 1

What would you like to do? 
-1- Check Out
-2- Remove Product
-Else- Go Home
Enter Option: 
```

## Option 1 Shopping Cart: User Checkout Section

In this section, users will be prompted with the total cost of all the products in their cart. They will have the opportunity to review their entire cart in detail and proceed to pay with cash. Additionally, upon completion of the transaction, a detailed receipt will be displayed, including the date of purchase, itemized list of purchased items, total cost, amount given, change, and a thank you message for the transaction. This comprehensive receipt ensures transparency and provides users with a clear record of their purchase.

```bash
------------------------------------------------------
		Check Out
------------------------------------------------------

Product: SKU: GM1075 | Name: Battleship Board Game | Price: $12.99 | Department: Games | Quantity: 1
Product: SKU: PW1431 | Name: Wireless Charger iPhone | Price: $12.99 | Department: Electronics | Quantity: 1
------------------------------------------------------
              Total Price: $25.98
------------------------------------------------------
Please insert cash amount here to pay: 30.00

Thank you for your payment!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Receipt:
Order Date: Thursday, April 18, 2024 22:44
Items Purchased:
- Product: SKU: GM1075 | Name: Battleship Board Game | Price: $12.99 | Department: Games | Quantity: 1
- Product: SKU: PW1431 | Name: Wireless Charger iPhone | Price: $12.99 | Department: Electronics | Quantity: 1
	-Sales Total: $25.98
	-Amount Paid: $30.00
	-Change Given: $4.02
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Thank you come again
Directing you back Home....
```

## Option 2 Shopping Cart Sub Menu: Remove Shopping Item

This option allows users to remove items from the cart based on the SKU. If the user decides to delete an item with a quantity larger than 1, the quantity of that item in the cart will decrement each time the user enters the SKU of that product. Additionally, there is another option in the main menu that enables users to clear all items in the cart.

```bash
Enter the SKU number of the product you like to remove from your cart: AV1051
------------------------------------------------------
                  Updated Cart!
------------------------------------------------------
Product: SKU: GM1075 | Name: Battleship Board Game | Price: $12.99 | Department: Games | Quantity: 1
Product: SKU: PW1431 | Name: Wireless Charger iPhone | Price: $12.99 | Department: Electronics | Quantity: 2
What would you like to do?
-1- Check out
-2- Remove another specific item from cart
-Else- Return Home
Enter your choice: 
```

## Error Handling Section

### Main Class (Home) Error handle

The main screen of the Javazon Online Store interface presents users with various options to choose from. It includes error handling to manage instances where users input an option that is not one of the displayed choices. If the user selects the wrong option, a message is displayed, and the menu is presented again for the user to make a correct selection.

```bash
------------------------------------------------------
         Welcome to Javazon
- Your Ultimate Java Online Store! - 	
------------------------------------------------------
Shopping Options:
1- Display Products
2- Display Cart
3- Exit
Please choose an option from above: none

Sorry, please choose one of the options displayed
```

### Error Handling For Searching Store Inventory

If a user enters a SKU number that does not exist in our inventory, they will be prompted again to input a valid SKU.

```bash
Enter search keyword: Legos

------------------------------------------------------------------------
                     Search Results
------------------------------------------------------------------------

Sorry that product doesnt exist in our inventory. Please try again.

Enter search keyword: 
```

### Error Handling: If the user tries to check out their cart, but it's empty.

If the user tries to view their cart or proceed to checkout when it's empty, they will be prompted with a message indicating that the cart is empty. Then, they will be directed back to the home screen.

```bash
Shopping Options:
1- Display Products
2- Display Cart
3- Exit
Please choose an option from above: 2

------------------------------------------------------
            0 Items Found In Cart!
------------------------------------------------------

What would you like to do? 
-1- Go Home
-Else- Exit
Enter Option:
```

## Interesting Section of code
### Similar to a real shopping experience, this code allows users to continue adding items to their cart until they choose to exit by returning to the home screen, much like walking out of the store's door.

This code replicates the authentic shopping experience by enabling users to seamlessly add items to their cart without limitations, mirroring the freedom one enjoys while browsing through physical store aisles. This immersive shopping simulation ensures that users feel fully engaged and empowered throughout their shopping journey, with the option to exit gracefully back to the home screen when they are ready to conclude their shopping session.

```java
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
```


---
"Welcome to Javazon, where we bridge communities through the magic of online shopping and the joy of discovery. Happy shopping! 🛒✨"
