import java.util.*;

interface NotificationChannel {
    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {
        System.out.println(
                "[Email → " +
                student.getName() +
                "] " +
                notice.getTitle());
    }
}

class SmsChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {
        System.out.println(
                "[SMS → " +
                student.getName() +
                "] " +
                notice.getTitle());
    }
}

class AppChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {
        System.out.println(
                "[App → " +
                student.getName() +
                "] " +
                notice.getTitle());
    }
}

class Student {

    private String name;
    private String department;
    private List<NotificationChannel> channels;

    Student(String name, String department) {
        this.name = name;
        this.department = department;
        this.channels = new ArrayList<>();
    }

    void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    String getName() {
        return name;
    }

    String getDepartment() {
        return department;
    }

    List<NotificationChannel> getChannels() {
        return channels;
    }
}

class Notice {

    private String title;
    private Set<String> departments;

    Notice(String title, Set<String> departments) {
        this.title = title;
        this.departments = departments;
    }

    boolean isValid() {
        return title != null &&
                !title.trim().isEmpty() &&
                departments != null &&
                !departments.isEmpty();
    }

    String getTitle() {
        return title;
    }

    Set<String> getDepartments() {
        return departments;
    }
}

class NoticeBoard {

    private List<Student> students;

    NoticeBoard() {
        students = new ArrayList<>();
    }

    void addStudent(Student student) {
        students.add(student);
    }

    void postNotice(Notice notice) {

        if (!notice.isValid()) {
            System.out.println(
                    "Cannot post notice: At least one target department is required.");
            return;
        }

        System.out.print(
                "Notice '" +
                notice.getTitle() +
                "' posted to ");

        int count = 0;

        for (String department : notice.getDepartments()) {

            if (count > 0) {
                System.out.print(", ");
            }

            System.out.print(department);
            count++;
        }

        System.out.println(".");

        for (Student student : students) {

            if (notice.getDepartments()
                    .contains(student.getDepartment())) {

                for (NotificationChannel channel :
                        student.getChannels()) {

                    channel.send(student, notice);
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {

    public static void main(String[] args) {

        NoticeBoard board = new NoticeBoard();

        Student asha =
                new Student("Asha", "CSE");

        Student ravi =
                new Student("Ravi", "ECE");

        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        ravi.addChannel(new SmsChannel());

        board.addStudent(asha);
        board.addStudent(ravi);

        Set<String> cse =
                new HashSet<>();

        cse.add("CSE");

        Notice notice1 =
                new Notice(
                        "Lab Closed Tomorrow",
                        cse);

        board.postNotice(notice1);

        Set<String> departments =
                new HashSet<>();

        departments.add("CSE");
        departments.add("ECE");

        Notice notice2 =
                new Notice(
                        "Fee Deadline Extended",
                        departments);

        board.postNotice(notice2);

        Notice notice3 =
                new Notice(
                        "Sports Day",
                        new HashSet<>());

        board.postNotice(notice3);
    }
}
