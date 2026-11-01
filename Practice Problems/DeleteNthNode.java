
import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class DeleteNthNode {

    static Node deleteNthNode(Node head, int n) {

        if (head == null) {
            return head;
        }

        // Delete first node
        if (n == 1) {
            return head.next;
        }

        Node current = head;

        // Reach node before target
        for (int i = 1; i < n - 1; i++) {
            current = current.next;
        }

        // Skip the target node
        current.next = current.next.next;

        return head;
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

        int position = sc.nextInt();

        head = deleteNthNode(head, position);

        display(head);
        sc.close();
    }
}
