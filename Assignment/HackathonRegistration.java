
import java.util.Scanner;

class Team {
    String name;
    int size;

    Team(String name, int size) {
        this.name = name;
        this.size = size;
    }
}

public class HackathonRegistration {

    static void sortTeams(Team[] teams) {

        for (int i = 1; i < teams.length; i++) {

            Team key = teams[i];

            int j = i - 1;

            while (j >= 0 && teams[j].size > key.size) {

                teams[j + 1] = teams[j];

                j--;
            }

            teams[j + 1] = key;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Team[] teams = new Team[n];

        for (int i = 0; i < n; i++) {

            String name = sc.next();
            int size = sc.nextInt();

            teams[i] = new Team(name, size);
        }

        sortTeams(teams);

        for (int i = 0; i < n; i++) {

            System.out.print(
                teams[i].name + "(" + teams[i].size + ")"
            );

            if (i < n - 1) {
                System.out.print(", ");
            }
        }

        sc.close();
    }
}
