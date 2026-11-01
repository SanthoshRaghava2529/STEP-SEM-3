
import java.util.Scanner;

public class AttendanceDisorderScore {

    static long countInversions(int[] roll) {

        int[] temp = new int[roll.length];

        return mergeSort(roll, temp, 0, roll.length - 1);
    }

    static long mergeSort(int[] arr, int[] temp,
                          int left, int right) {

        long count = 0;

        if (left < right) {

            int mid = left + (right - left) / 2;

            count += mergeSort(arr, temp, left, mid);

            count += mergeSort(arr, temp, mid + 1, right);

            count += merge(arr, temp, left, mid, right);
        }

        return count;
    }

    static long merge(int[] arr, int[] temp,
                      int left, int mid, int right) {

        int i = left;
        int j = mid + 1;
        int k = left;

        long count = 0;

        while (i <= mid && j <= right) {

            if (arr[i] <= arr[j]) {

                temp[k++] = arr[i++];

            } else {

                temp[k++] = arr[j++];

                count += (mid - i + 1);
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        return count;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] roll = new int[n];

        for (int i = 0; i < n; i++) {
            roll[i] = sc.nextInt();
        }

        System.out.println(countInversions(roll));

        sc.close();
    }
}
