package Assignments;

public class ProductInventoryCSVParser {

    static void parseInventoryRecord(String csvLine) {

        String[] parts = csvLine.split(",");

        if (parts.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String productName = parts[0];
        String sku = parts[1];
        String quantity = parts[2];

        System.out.println("Product: " + productName
                + " | SKU: " + sku
                + " | Qty: " + quantity);
    }

    public static void main(String[] args) {

        String csvLine = "Wireless Mouse,WM-2201,150";

        parseInventoryRecord(csvLine);
    }
}