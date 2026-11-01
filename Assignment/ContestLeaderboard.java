
import java.util.Scanner;

class Entry {
    String name;
    int marks;

    Entry(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class ContestLeaderboard {

    static Entry[] leaderboard(Entry[] entries) {

        int n = entries.length;

        int[] count = new int[101];

        Entry[] output = new Entry[n];

        // Count frequency using reversed marks
        for (int i = 0; i < n; i++) {
            int key = 100 - entries[i].marks;
            count[key]++;
        }

        // Calculate cumulative count
        for (int i = 1; i <= 100; i++) {
            count[i] += count[i - 1];
        }

        // Place elements from right to left
        for (int i = n - 1; i >= 0; i--) {

            int key = 100 - entries[i].marks;

            output[--count[key]] = entries[i];
        }

        return output;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Entry[] entries = new Entry[n];

        for (int i = 0; i < n; i++) {

            String name = sc.next();
            int marks = sc.nextInt();

            entries[i] = new Entry(name, marks);
        }

        Entry[] result = leaderboard(entries);

        for (int i = 0; i < n; i++) {

            System.out.print(
                result[i].name + " " + result[i].marks
            );

            if (i < n - 1) {
                System.out.print(", ");
            }
        }

        sc.close();
    }
}
