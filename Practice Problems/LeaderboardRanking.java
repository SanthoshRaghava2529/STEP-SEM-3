
import java.util.Scanner;

class Participant {
    String name;
    int score;

    Participant(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

public class LeaderboardRanking {

    static void insertionSort(Participant[] p) {
        for (int i = 1; i < p.length; i++) {
            Participant key = p[i];
            int j = i - 1;

            while (j >= 0 && p[j].score < key.score) {
                p[j + 1] = p[j];
                j--;
            }

            p[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Participant[] p = new Participant[n];

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int score = sc.nextInt();

            p[i] = new Participant(name, score);
        }

        insertionSort(p);

        int rank = 1;

        for (int i = 0; i < n; i++) {

            if (i > 0 && p[i].score != p[i - 1].score) {
                rank = i + 1;
            }

            System.out.println(rank + " " +
                               p[i].name + " " +
                               p[i].score);
        }

        sc.close();
    }
}
