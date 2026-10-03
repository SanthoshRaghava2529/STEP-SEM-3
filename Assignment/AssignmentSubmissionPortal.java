import java.time.LocalDate;

abstract class Assignment {
    protected String title;
    protected int maxMarks;
    protected LocalDate dueDate;

    Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    abstract double applyPenalty(double marks, long lateDays);

    String getTitle() {
        return title;
    }

    int getMaxMarks() {
        return maxMarks;
    }

    LocalDate getDueDate() {
        return dueDate;
    }
}

class CodingAssignment extends Assignment {

    CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    double applyPenalty(double marks, long lateDays) {
        double penalty = lateDays * 0.10;
        return Math.max(0, marks * (1 - penalty));
    }
}

class WrittenAssignment extends Assignment {

    WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    double applyPenalty(double marks, long lateDays) {
        double penalty = lateDays * 0.20;
        return Math.max(0, marks * (1 - penalty));
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class Submission {
    private Student student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private String status;
    private double finalMarks;

    Submission(Student student, Assignment assignment, LocalDate submissionDate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = "Submitted";
    }

    void grade(double awardedMarks) {
        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade this submission.");
            return;
        }

        long lateDays = Math.max(0,
                java.time.temporal.ChronoUnit.DAYS.between(
                        assignment.getDueDate(), submissionDate));

        finalMarks = assignment.applyPenalty(awardedMarks, lateDays);
        status = "Graded";

        System.out.printf("%s graded: %.0f/%d. Status: Graded.%n",
                student.getName(),
                finalMarks,
                assignment.getMaxMarks());
    }

    void resubmit(LocalDate date) {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '" +
                    assignment.getTitle() + "' has already been graded.");
        } else {
            submissionDate = date;
            System.out.println("Resubmission received.");
        }
    }

    String getStatus() {
        return status;
    }

    long getLateDays() {
        return Math.max(0,
                java.time.temporal.ChronoUnit.DAYS.between(
                        assignment.getDueDate(), submissionDate));
    }
}

public class AssignmentSubmissionPortal {

    public static void main(String[] args) {

        Assignment coding = new CodingAssignment(
                "Linked List Lab",
                50,
                LocalDate.of(2026, 3, 10));

        Assignment written = new WrittenAssignment(
                "Design Essay",
                50,
                LocalDate.of(2026, 3, 12));

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Submission ashaSubmission = new Submission(
                asha,
                coding,
                LocalDate.of(2026, 3, 10));

        Submission raviSubmission = new Submission(
                ravi,
                written,
                LocalDate.of(2026, 3, 14));

        System.out.println("Asha's submission for 'Linked List Lab' received (on time).");
        System.out.println("Status: " + ashaSubmission.getStatus());

        System.out.println("Ravi's submission for 'Design Essay' received (" +
                raviSubmission.getLateDays() + " days late).");
        System.out.println("Status: " + raviSubmission.getStatus());

        ashaSubmission.grade(45);

        raviSubmission.grade(40);

        ashaSubmission.resubmit(LocalDate.of(2026, 3, 11));
    }
}
