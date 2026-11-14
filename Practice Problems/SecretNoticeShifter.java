
import java.util.Scanner;

public class SecretNoticeShifter {

    static String shiftNotice(String text, int k) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c >= 'a' && c <= 'z') {
                c = (char) ((c - 'a' + k) % 26 + 'a');
            }

            else if (c >= 'A' && c <= 'Z') {
                c = (char) ((c - 'A' + k) % 26 + 'A');
            }

            result.append(c);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();
        int k = sc.nextInt();

        System.out.println(shiftNotice(text, k));

        sc.close();
    }
}
