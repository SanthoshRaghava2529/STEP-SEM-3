
import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class ReverseTheQueue {

    // Reverse linked list
    static Node reverse(Node head) {

        Node previous = null;
        Node current = head;
        Node next = null;

        while (current != null) {

            next = current.next;

            current.next = previous;

            previous = current;

            current = next;
        }

        return previous;
    }

    // Display linked list
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

        head = reverse(head);

        display(head);

        sc.close();
    }
}
