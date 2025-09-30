package model;

import java.math.BigInteger;
import java.time.LocalDate;

public class Product {
    private BigInteger productId;
    private String name;
    private LocalDate dateSupplied;
    private int quantityInStock;
    private double unitPrice;

    // Default constructor
    public Product() {}

    // Constructor with all fields
    public Product(BigInteger productId, String name, LocalDate dateSupplied, int quantityInStock, double unitPrice) {
        this.productId = productId;
        this.name = name;
        this.dateSupplied = dateSupplied;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }

    // Partial constructor (example)
    public Product(BigInteger productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    // Getters and Setters
    public BigInteger getProductId() { return productId; }
    public void setProductId(BigInteger productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDateSupplied() { return dateSupplied; }
    public void setDateSupplied(LocalDate dateSupplied) { this.dateSupplied = dateSupplied; }

    public int getQuantityInStock() { return quantityInStock; }
    public void setQuantityInStock(int quantityInStock) { this.quantityInStock = quantityInStock; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}
