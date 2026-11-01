
import java.util.*;

public class ClassMarksAnalysis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> names = new ArrayList<>();
        ArrayList<int[]> marksList = new ArrayList<>();

        while (sc.hasNext()) {
            String name = sc.next();

            String line = sc.next();
            line = line.replace("[", "").replace("]", "");

            String[] values = line.split(",");

            int[] marks = new int[3];

            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(values[i].trim());
            }

            names.add(name);
            marksList.add(marks);
        }

        int n = names.size();
        int[][] marks = new int[n][3];

        int[] totals = new int[n];
        double[] averages = new double[3];

        int topperIndex = 0;

        for (int i = 0; i < n; i++) {
            marks[i] = marksList.get(i);

            for (int j = 0; j < 3; j++) {
                totals[i] += marks[i][j];
                averages[j] += marks[i][j];
            }

            if (totals[i] > totals[topperIndex]) {
                topperIndex = i;
            }
        }

        System.out.print("Totals ");

        for (int i = 0; i < n; i++) {
            System.out.print(names.get(i) + " " + totals[i]);

            if (i < n - 1) System.out.print(", ");
        }

        System.out.println();

        System.out.printf("averages ");

        for (int j = 0; j < 3; j++) {
            averages[j] /= n;

            System.out.printf("%.2f", averages[j]);

            if (j < 2) System.out.print(", ");
        }

        System.out.println();

        System.out.println("topper " +
                names.get(topperIndex) +
                " (" + totals[topperIndex] + ")");

        sc.close();
    }
}
