import java.util.ArrayList;

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {

    public boolean processPayment(double amount) {
        System.out.println("Processing Credit Card payment of $" + amount);
        return true;
    }
}

class PayPalPayment implements PaymentMethod {

    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        return false;
    }
}

class BankTransferPayment implements PaymentMethod {

    public boolean processPayment(double amount) {
        System.out.println("Processing Bank Transfer of $" + amount);
        return true;
    }
}

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class OrderItem {
    Product product;
    int quantity;

    OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getTotal() {
        return product.price * quantity;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Order {
    Customer customer;
    ArrayList<OrderItem> items = new ArrayList<>();
    String status = "Pending";

    Order(Customer customer) {
        this.customer = customer;
    }

    void addProduct(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    double getTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    void pay(PaymentMethod paymentMethod) {

        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        System.out.println("Payment initiated for Order " +
                customer.name + ".");

        boolean success = paymentMethod.processPayment(getTotal());

        if (success) {
            status = "Paid";
            System.out.println("Payment for Order " +
                    customer.name + " successful.");
        } else {
            System.out.println("Payment for Order " +
                    customer.name + " failed.");
        }

        System.out.println("Order status: " + status);
    }
}

public class PaymentProcessingSystem {

    public static void main(String[] args) {

        Product productA = new Product("Product A", 100);
        Product productB = new Product("Product B", 50);
        Product productC = new Product("Product C", 200);

        Customer customerX = new Customer("X");
        Customer customerY = new Customer("Y");
        Customer customerZ = new Customer("Z");

        Order orderX = new Order(customerX);

        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        System.out.println("Order created for Customer X.");

        orderX.pay(new CreditCardPayment());

        System.out.println();

        Order orderY = new Order(customerY);

        System.out.println("Order created for Customer Y.");
        orderY.pay(new CreditCardPayment());

        System.out.println();

        Order orderZ = new Order(customerZ);

        orderZ.addProduct(productC, 1);

        System.out.println("Order created for Customer Z.");

        orderZ.pay(new PayPalPayment());
    }
}
