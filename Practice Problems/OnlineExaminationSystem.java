import java.util.*;

abstract class Question {
    protected String questionText;
    protected int marks;

    Question(String questionText, int marks) {
        this.questionText = questionText;
        this.marks = marks;
    }

    abstract int evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    MultipleChoiceQuestion(String questionText, String correctAnswer, int marks) {
        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    int evaluate(String answer) {
        return answer.equalsIgnoreCase(correctAnswer) ? marks : 0;
    }
}

class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    TrueFalseQuestion(String questionText, boolean correctAnswer, int marks) {
        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    int evaluate(String answer) {
        boolean userAnswer = Boolean.parseBoolean(answer);
        return userAnswer == correctAnswer ? marks : 0;
    }
}

class ShortAnswerQuestion extends Question {
    private String correctAnswer;

    ShortAnswerQuestion(String questionText, String correctAnswer, int marks) {
        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    int evaluate(String answer) {
        return answer.equalsIgnoreCase(correctAnswer) ? marks : 0;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Examination {
    String name;
    ArrayList<Question> questions = new ArrayList<>();

    Examination(String name) {
        this.name = name;
    }

    void addQuestion(Question question) {
        questions.add(question);
    }
}

class Attempt {
    Student student;
    Examination examination;
    HashMap<Integer, String> answers = new HashMap<>();
    boolean submitted = false;

    Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    void answerQuestion(int questionNumber, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }

        answers.put(questionNumber, answer);
        System.out.println("Answer recorded for Question " + questionNumber + ".");
    }

    void submit() {
        if (submitted) {
            System.out.println("Examination already submitted.");
            return;
        }

        submitted = true;

        System.out.println("\n" + examination.name +
                " submitted by " + student.name + ".");

        int total = 0;
        int maximum = 0;

        for (int i = 0; i < examination.questions.size(); i++) {
            Question question = examination.questions.get(i);
            maximum += question.marks;

            String answer = answers.get(i + 1);

            if (answer == null) {
                answer = "";
            }

            int score = question.evaluate(answer);
            total += score;

            if (score > 0) {
                System.out.println("Result: Question " + (i + 1) +
                        ": Correct (" + score + " points)");
            } else {
                System.out.println("Result: Question " + (i + 1) +
                        ": Incorrect (0 points)");
            }
        }

        System.out.println("Total score: " + total + "/" + maximum);
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {

        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        exam.addQuestion(
                new MultipleChoiceQuestion(
                        "Which is a programming language?",
                        "C",
                        5
                )
        );

        exam.addQuestion(
                new TrueFalseQuestion(
                        "Java supports OOP.",
                        false,
                        5
                )
        );

        Attempt attempt = new Attempt(student, exam);

        System.out.println("Exam A started by Student 1.");

        attempt.answerQuestion(1, "C");
        attempt.answerQuestion(2, "True");

        attempt.submit();

        System.out.println();
        attempt.answerQuestion(1, "Java");
    }
}
