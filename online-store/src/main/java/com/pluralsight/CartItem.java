package com.pluralsight;

//Class to create one CartItem with 2 values
//Product value will hold a generated class
//Quantity will hold the value of the same Class
public class CartItem {
    private Product product;
    private int quantity;
    //Class Constructor will make the Class Instance
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    //Generate Setters and Getters
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //Method to increment the value of quantity
    public void incrementQuantity() {
        quantity++;
    }
    //Method to decrement the value of quantity
    public void decrementQuantity() {
        quantity--;
    }
    //Grab the product price by the Original Class method
    // and times it by the quantity to figure out total for that Product
    public double getTotalPrice() {
        return product.getProductPrice() * quantity;
    }
    //Overide the Object string method to display attributes a specific way
    @Override
    public String toString() {
        return String.format("Product: %s | Quantity: %d", product.toString(), quantity);
    }
}
