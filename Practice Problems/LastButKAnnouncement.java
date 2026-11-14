
import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LastButKAnnouncement {

    static String kthFromEnd(Node head, int k) {
        Node lead = head;
        Node trail = head;

        for (int i = 0; i < k; i++) {
            if (lead == null) {
                return "Invalid k";
            }
            lead = lead.next;
        }

        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }

        return String.valueOf(trail.data);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Node head = null, tail = null;

        for (int i = 0; i < n; i++) {
            Node newNode = new Node(sc.nextInt());

            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        int k = sc.nextInt();

        String result = kthFromEnd(head, k);

        if (result.equals("Invalid k")) {
            System.out.println("Invalid k");
        } else {
            System.out.println(k + " from the end: " + result);
        }
    }
}
