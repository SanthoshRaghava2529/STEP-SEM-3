
import java.util.*;

interface Chargeable {
    void bookChargingBay();
}

abstract class Vehicle {
    String passNumber;
    String owner;

    Vehicle(String passNumber, String owner) {
        this.passNumber = passNumber;
        this.owner = owner;
    }

    abstract int getPassFee();

    abstract String getType();
}

class Bike extends Vehicle {
    Bike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 300;
    }

    String getType() {
        return "Bike";
    }
}

class Car extends Vehicle {
    Car(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 1000;
    }

    String getType() {
        return "Car";
    }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 300;
    }

    String getType() {
        return "EBike";
    }

    public void bookChargingBay() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 1000;
    }

    String getType() {
        return "ECar";
    }

    public void bookChargingBay() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

public class CampusVehicleSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Vehicle> vehicles = new HashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("PASS")) {
                String type = sc.next();
                String number = sc.next();
                String owner = sc.next();

                Vehicle v = null;

                switch (type) {
                    case "Bike":
                        v = new Bike(number, owner);
                        break;
                    case "Car":
                        v = new Car(number, owner);
                        break;
                    case "EBike":
                        v = new EBike(number, owner);
                        break;
                    case "ECar":
                        v = new ECar(number, owner);
                        break;
                }

                if (v != null) {
                    vehicles.put(number, v);
                    System.out.println(number + " (" + type +
                            ") pass fee " + v.getPassFee());
                }
            }
            else if (operation.equals("CHARGE")) {
                String number = sc.next();

                Vehicle v = vehicles.get(number);

                if (v == null) {
                    System.out.println("Vehicle not found");
                }
                else if (v instanceof Chargeable) {
                    ((Chargeable) v).bookChargingBay();
                }
                else {
                    System.out.println(number +
                            " rejected: charging unsupported");
                }
            }
        }
        sc.close();
    }
}
