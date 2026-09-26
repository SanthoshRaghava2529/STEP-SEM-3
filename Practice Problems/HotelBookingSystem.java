import java.time.LocalDate;
import java.util.ArrayList;

abstract class Room {
    protected String roomNumber;

    Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    abstract double calculatePrice(long days);

    String getRoomNumber() {
        return roomNumber;
    }
}

class StandardRoom extends Room {
    StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    double calculatePrice(long days) {
        return days * 100;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    double calculatePrice(long days) {
        return days * 150;
    }
}

class Suite extends Room {
    Suite(String roomNumber) {
        super(roomNumber);
    }

    double calculatePrice(long days) {
        return days * 250;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Reservation {
    Customer customer;
    Room room;
    LocalDate startDate;
    LocalDate endDate;

    Reservation(Customer customer, Room room,
                LocalDate startDate, LocalDate endDate) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    double calculatePrice() {
        long days = startDate.until(endDate).getDays();
        return room.calculatePrice(days);
    }
}

public class HotelBookingSystem {

    static ArrayList<Reservation> reservations = new ArrayList<>();

    static boolean isAvailable(Room room,
                                LocalDate start,
                                LocalDate end) {

        for (Reservation r : reservations) {

            if (r.room == room &&
                    start.isBefore(r.endDate) &&
                    end.isAfter(r.startDate)) {
                return false;
            }
        }

        return true;
    }

    static Reservation reserve(Customer customer,
                               Room room,
                               LocalDate start,
                               LocalDate end) {

        if (!isAvailable(room, start, end)) {
            System.out.println(room.getRoomNumber() +
                    " is not available from " +
                    start + " to " + end + ".");
            return null;
        }

        Reservation reservation =
                new Reservation(customer, room, start, end);

        reservations.add(reservation);

        System.out.println("Reservation confirmed for " +
                customer.name + ", Room " +
                room.getRoomNumber() + " (" +
                start + " to " + end + ").");

        System.out.println("Price: $" +
                reservation.calculatePrice());

        return reservation;
    }

    static void cancelReservation(Reservation reservation) {

        if (reservation != null) {
            reservations.remove(reservation);

            System.out.println("Reservation for " +
                    reservation.customer.name + ", Room " +
                    reservation.room.getRoomNumber() +
                    " cancelled successfully.");
        }
    }

    public static void main(String[] args) {

        Room room101 = new StandardRoom("Standard Room 101");
        Room room201 = new DeluxeRoom("Deluxe Room 201");

        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");

        LocalDate jan1 = LocalDate.of(2027, 1, 1);
        LocalDate jan5 = LocalDate.of(2027, 1, 5);

        System.out.println("Checking availability...");

        if (isAvailable(room101, jan1, jan5)) {
            System.out.println("Standard Room 101 is available from " +
                    jan1 + " to " + jan5 + ".");
        }

        Reservation reservationA =
                reserve(customerA, room101, jan1, jan5);

        reserve(
                customerB,
                room101,
                LocalDate.of(2027, 1, 3),
                LocalDate.of(2027, 1, 7)
        );

        cancelReservation(reservationA);

        reserve(
                customerC,
                room201,
                LocalDate.of(2027, 2, 10),
                LocalDate.of(2027, 2, 12)
        );
    }
}
