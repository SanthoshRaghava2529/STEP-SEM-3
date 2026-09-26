abstract class Vehicle {
    protected String name;
    protected boolean available;

    Vehicle(String name) {
        this.name = name;
        this.available = true;
    }

    abstract double calculateCharge(int days);

    boolean isAvailable() {
        return available;
    }

    void rent() {
        available = false;
    }

    void returnVehicle() {
        available = true;
    }

    String getName() {
        return name;
    }
}

class Sedan extends Vehicle {
    Sedan(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {
    SUV(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 80;
    }
}

class Truck extends Vehicle {
    Truck(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 100;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Rental {
    Vehicle vehicle;
    Customer customer;
    int days;

    Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    double getCharge() {
        return vehicle.calculateCharge(days);
    }
}

public class VehicleRentalSystem {
    static Rental rentVehicle(Vehicle vehicle, Customer customer, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getName() + " is currently unavailable.");
            return null;
        }

        vehicle.rent();
        Rental rental = new Rental(vehicle, customer, days);

        System.out.println(vehicle.getName() + " rented successfully by " + customer.name + ".");
        System.out.println("Rental charge: $" + rental.getCharge());

        return rental;
    }

    static void returnVehicle(Rental rental) {
        if (rental != null) {
            rental.vehicle.returnVehicle();
            System.out.println(rental.vehicle.getName() + " returned by " + rental.customer.name + ".");
        }
    }

    public static void main(String[] args) {
        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");

        Rental rental1 = rentVehicle(sedanA, customer1, 3);

        rentVehicle(sedanA, customer2, 2);

        returnVehicle(rental1);

        rentVehicle(suvB, customer3, 5);
    }
}
