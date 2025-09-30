package model;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class ProductMgmtApp {
    public static void main(String[] args) {
        Product[] products = {
                new Product(new BigInteger("31288741190182539912"), "Banana", LocalDate.parse("2025-01-24"), 124, 0.55),
                new Product(new BigInteger("29274582650152771644"), "Apple", LocalDate.parse("2024-12-09"), 18, 1.09),
                new Product(new BigInteger("91899274600128155167"), "Carrot", LocalDate.parse("2025-03-31"), 89, 2.99),
                new Product(new BigInteger("31288741190182539913"), "Banana", LocalDate.parse("2025-02-13"), 240, 0.65)
        };

        printProducts(products);
    }

    public static void printProducts(Product[] products) {
        Arrays.sort(products, Comparator.comparing(Product::getName)
                .thenComparing(Comparator.comparing(Product::getUnitPrice).reversed()));

        // JSON format
        System.out.println("JSON Format:");
        System.out.println("[");
        for (int i = 0; i < products.length; i++) {
            Product p = products[i];
            System.out.printf(
                    "  {\"productId\": \"%s\", \"name\": \"%s\", \"dateSupplied\": \"%s\", \"quantityInStock\": %d, \"unitPrice\": %.2f}%s%n",
                    p.getProductId().toString(), p.getName(), p.getDateSupplied(), p.getQuantityInStock(), p.getUnitPrice(),
                    (i < products.length - 1 ? "," : "")
            );
        }
        System.out.println("]");

        // XML format
        System.out.println("\nXML Format:");
        System.out.println("<products>");
        for (Product p : products) {
            System.out.printf(
                    "  <product><productId>%s</productId><name>%s</name><dateSupplied>%s</dateSupplied><quantityInStock>%d</quantityInStock><unitPrice>%.2f</unitPrice></product>%n",
                    p.getProductId().toString(), p.getName(), p.getDateSupplied(), p.getQuantityInStock(), p.getUnitPrice()
            );
        }
        System.out.println("</products>");

        // CSV format
        System.out.println("\nCSV Format:");
        System.out.println("productId,name,dateSupplied,quantityInStock,unitPrice");
        for (Product p : products) {
            System.out.printf("%s,%s,%s,%d,%.2f%n",
                    p.getProductId().toString(), p.getName(), p.getDateSupplied(), p.getQuantityInStock(), p.getUnitPrice()
            );
        }
    }
}
