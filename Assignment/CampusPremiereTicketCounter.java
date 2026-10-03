import java.util.*;

interface Seat {
    String getId();
    double getPrice();
}

class RegularSeat implements Seat {
    private String id;

    RegularSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 150;
    }
}

class PremiumSeat implements Seat {
    private String id;

    PremiumSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat implements Seat {
    private String id;

    ReclinerSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 400;
    }
}

class Customer {
    private String name;

    Customer(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class Show {
    private String name;
    private boolean started;
    private Set<String> bookedSeats;

    Show(String name) {
        this.name = name;
        this.started = false;
        this.bookedSeats = new HashSet<>();
    }

    boolean isAvailable(Seat seat) {
        return !bookedSeats.contains(seat.getId());
    }

    boolean bookSeat(Seat seat) {
        if (!isAvailable(seat)) {
            return false;
        }

        bookedSeats.add(seat.getId());
        return true;
    }

    void releaseSeat(Seat seat) {
        bookedSeats.remove(seat.getId());
    }

    boolean hasStarted() {
        return started;
    }

    void startShow() {
        started = true;
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean cancelled;

    Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
        this.cancelled = false;
    }

    double getTotal() {
        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    void cancel() {
        if (cancelled) {
            System.out.println("Booking already cancelled.");
            return;
        }

        if (show.hasStarted()) {
            System.out.println("Cannot cancel: show has already started.");
            return;
        }

        for (Seat seat : seats) {
            show.releaseSeat(seat);
        }

        cancelled = true;

        System.out.println(customer.getName() + "'s booking cancelled.");

        System.out.print("Seats released: ");

        for (int i = 0; i < seats.size(); i++) {
            System.out.print(seats.get(i).getId());

            if (i < seats.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println(".");
    }
}

public class CampusPremiereTicketCounter {

    static Booking createBooking(
            Customer customer,
            Show show,
            Seat... seats) {

        if (seats.length == 0 || seats.length > 6) {
            System.out.println("Booking must contain 1 to 6 seats.");
            return null;
        }

        for (Seat seat : seats) {
            if (!show.isAvailable(seat)) {
                System.out.println(
                        "Seat " + seat.getId() +
                        " is already booked for this show.");
                return null;
            }
        }

        for (Seat seat : seats) {
            show.bookSeat(seat);
        }

        List<Seat> seatList = Arrays.asList(seats);

        Booking booking =
                new Booking(customer, show, seatList);

        System.out.print("Booking confirmed for " +
                customer.getName() + ": ");

        for (int i = 0; i < seats.length; i++) {
            System.out.print(seats[i].getId());

            if (i < seats.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(". Total: ₹%.2f%n",
                booking.getTotal());

        return booking;
    }

    public static void main(String[] args) {

        Show show = new Show("7 PM Show");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Seat a1 = new RegularSeat("A1");
        Seat a2 = new RegularSeat("A2");
        Seat f5 = new PremiumSeat("F5");
        Seat r1 = new ReclinerSeat("R1");

        Booking ashaBooking =
                createBooking(asha, show, a1, a2, f5);

        createBooking(ravi, show, a2);

        createBooking(ravi, show, r1);

        ashaBooking.cancel();

        createBooking(neha, show, a2);
    }
}
