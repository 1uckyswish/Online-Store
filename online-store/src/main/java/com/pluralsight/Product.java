package com.pluralsight;
//Class to create one product with 4 values
public class Product {
    private String productSKU;
    private String productName;
    private double productPrice;
    private String productDepartment;
    //Class constructor to modify the 4 values
    public Product(String productSKU, String productName, double productPrice, String productDepartment) {
        this.productSKU = productSKU;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDepartment = productDepartment;
    }
    //Generate Setters and Getters
    public String getProductSKU() {
        return productSKU;
    }
    public void setProductSKU(String productSKU) {
        this.productSKU = productSKU;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public String getProductDepartment() {
        return productDepartment;
    }
    public void setProductDepartment(String productDepartment) {
        this.productDepartment = productDepartment;
    }
    //Overide the Object string method to display attributes a specific way
    @Override
    public String toString() {
        return String.format("SKU: %s | Name: %s | Price: $%.2f | Department: %s",
                productSKU, productName, productPrice, productDepartment);
    }
}