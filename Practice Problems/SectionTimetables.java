
import java.util.ArrayList;
import java.util.Scanner;

class Timetable {
    String section;
    ArrayList<String> periods;

    Timetable(String section, ArrayList<String> periods) {
        this.section = section;
        this.periods = periods;
    }

    Timetable copy(String newSection) {
        ArrayList<String> newPeriods =
                new ArrayList<>(periods);

        return new Timetable(newSection, newPeriods);
    }

    void setPeriod(int period, String subject) {
        if (period >= 1 && period <= periods.size()) {
            periods.set(period - 1, subject);
        }
    }

    void display() {
        System.out.println(section + ": " + periods);
    }
}

public class SectionTimetables {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String originalName = sc.next();
        int n = sc.nextInt();

        ArrayList<String> subjects = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            subjects.add(sc.next());
        }

        Timetable original =
                new Timetable(originalName, subjects);

        String newName = sc.next();

        Timetable copied = original.copy(newName);

        int period = sc.nextInt();
        String subject = sc.next();

        copied.setPeriod(period, subject);

        original.display();
        copied.display();

        sc.close();
    }
}
