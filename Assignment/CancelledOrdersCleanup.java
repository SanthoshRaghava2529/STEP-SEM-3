
import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CancelledOrdersCleanup {

    // Remove all occurrences of given code
    static Node removeAll(Node head, int code) {

        // Remove matching nodes from beginning
        while (head != null && head.data == code) {
            head = head.next;
        }

        Node temp = head;

        // Remove matching nodes from remaining list
        while (temp != null && temp.next != null) {

            if (temp.next.data == code) {
                temp.next = temp.next.next;
            }
            else {
                temp = temp.next;
            }
        }

        return head;
    }

    // Display list
    static void display(Node head) {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Node head = null;
        Node tail = null;

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            Node newNode = new Node(sc.nextInt());

            if (head == null) {
                head = tail = newNode;
            }
            else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        int code = sc.nextInt();

        head = removeAll(head, code);

        display(head);

        sc.close();
    }
}
