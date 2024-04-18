package com.pluralsight;

public class Product {
    private String productSKU;
    private String productName;
    private double productPrice;
    private String productDepartment;

    public Product(String productSKU, String productName, double productPrice, String productDepartment) {
        this.productSKU = productSKU;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDepartment = productDepartment;
    }

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

    @Override
    public String toString() {
        return "SKU: " + productSKU + ", Name: " + productName + ", Price: $" + productPrice +
                ", Department: " + productDepartment;
    }

}