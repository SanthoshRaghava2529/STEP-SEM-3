
import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class SplitLinkedList {

    static Node splitListInHalf(Node head) {

        if (head == null || head.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;

        // Find end of first half
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = slow.next;

        slow.next = null;

        return secondHalf;
    }

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
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        Node secondHalf = splitListInHalf(head);

        System.out.print("First half: ");
        display(head);

        System.out.print("Returned: ");
        display(secondHalf);

        sc.close();
    }
}
