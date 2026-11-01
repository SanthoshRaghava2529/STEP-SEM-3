
import java.util.Scanner;

public class NoticeBoardShuffle {

    static int[] bubbleStats(int[] scores) {

        int passes = 0;
        int swaps = 0;

        int n = scores.length;

        while (true) {

            boolean swapped = false;
            passes++;

            for (int i = 0; i < n - passes; i++) {

                if (scores[i] > scores[i + 1]) {

                    int temp = scores[i];
                    scores[i] = scores[i + 1];
                    scores[i + 1] = temp;

                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return new int[]{passes, swaps};
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] scores = new int[n];

        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }

        int[] result = bubbleStats(scores);

        System.out.print("Sorted: ");

        for (int i = 0; i < n; i++) {
            System.out.print(scores[i] + " ");
        }

        System.out.println();
        System.out.println("passes = " + result[0]);
        System.out.println("swaps = " + result[1]);

        sc.close();
    }
}
