import java.util.Arrays;

class BookingReceipt {

    // Final fields
    private final String bookingId;
    private final String[] seatNumbers;

    // Constructor
    public BookingReceipt(String bookingId, String[] seatNumbers) {

        this.bookingId = bookingId;

        // Defensive copy
        this.seatNumbers = seatNumbers.clone();
    }

    // Getter for booking ID
    public String getBookingId() {
        return bookingId;
    }

    // Getter with defensive copy
    public String[] getSeatNumbers() {
        return seatNumbers.clone();
    }

    // Wither method
    public BookingReceipt withUpdatedSeat(
            int index, String newSeat) {

        // Make a copy of the original array
        String[] newSeats = seatNumbers.clone();

        // Change only the copy
        newSeats[index] = newSeat;

        // Return a new object
        return new BookingReceipt(
                bookingId,
                newSeats
        );
    }

    // Nightly settlement
    public static String processNightlySettlement(
            BookingReceipt[] receipts) {

        int processed = 0;
        int nullCount = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (BookingReceipt receipt : receipts) {

            // Handle null entry
            if (receipt == null) {
                nullCount++;
                continue;
            }

            processed++;

            // Check whether it is a group booking
            if (receipt instanceof GroupBookingReceipt) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | "
                + nullCount + " null skipped | "
                + groupCount + " group | "
                + individualCount + " individual";
    }
}


// GroupBookingReceipt inherits BookingReceipt
class GroupBookingReceipt extends BookingReceipt {

    private final int groupSize;

    public GroupBookingReceipt(
            String bookingId,
            String[] seatNumbers,
            int groupSize) {

        super(bookingId, seatNumbers);

        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}


// Main class
public class Problem5_BookingReceipt {

    public static void main(String[] args) {

        // --------------------------------
        // TEST 1: Defensive Copy
        // --------------------------------

        BookingReceipt b =
                new BookingReceipt(
                        "CH-1001",
                        new String[]{"A1", "A2"}
                );

        String[] seats = b.getSeatNumbers();

        // Try to change the returned array
        seats[0] = "X";

        // Original receipt should still contain A1
        System.out.println(
                b.getSeatNumbers()[0]
        );


        // --------------------------------
        // TEST 2: Wither Method
        // --------------------------------

        BookingReceipt updated =
                b.withUpdatedSeat(1, "A3");

        System.out.println(
                Arrays.toString(
                        b.getSeatNumbers()
                )
        );

        System.out.println(
                Arrays.toString(
                        updated.getSeatNumbers()
                )
        );


        // --------------------------------
        // TEST 3: Nightly Settlement
        // --------------------------------

        BookingReceipt[] receipts = {

                new GroupBookingReceipt(
                        "CH-2002",
                        new String[]{"B1", "B2"},
                        2
                ),

                null,

                new BookingReceipt(
                        "CH-3003",
                        new String[]{"C1"}
                )
        };

        System.out.println(
                BookingReceipt.processNightlySettlement(
                        receipts
                )
        );
    }
}