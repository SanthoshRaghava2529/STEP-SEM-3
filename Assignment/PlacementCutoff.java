
import java.util.Scanner;

public class PlacementCutoff {

    static int kthHighest(int[] marks, int k) {

        int target = marks.length - k;

        int low = 0;
        int high = marks.length - 1;

        while (low <= high) {

            int pivotIndex = partition(marks, low, high);

            if (pivotIndex == target) {
                return marks[pivotIndex];
            }

            else if (pivotIndex > target) {
                high = pivotIndex - 1;
            }

            else {
                low = pivotIndex + 1;
            }
        }

        return -1;
    }

    static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j] <= pivot) {

                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] marks = new int[n];

        for (int i = 0; i < n; i++) {
            marks[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        if (k < 1 || k > n) {
            System.out.println("Invalid k");
        } else {
            System.out.println(kthHighest(marks, k));
        }

        sc.close();
    }
}
