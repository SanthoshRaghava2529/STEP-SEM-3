
import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CounsellingListFirst {

    static Node partition(Node head, int passMark) {

        Node belowHead = null;
        Node belowTail = null;

        Node restHead = null;
        Node restTail = null;

        Node current = head;

        while (current != null) {

            Node nextNode = current.next;
            current.next = null;

            if (current.data < passMark) {

                if (belowHead == null) {
                    belowHead = belowTail = current;
                } else {
                    belowTail.next = current;
                    belowTail = current;
                }

            } else {

                if (restHead == null) {
                    restHead = restTail = current;
                } else {
                    restTail.next = current;
                    restTail = current;
                }
            }

            current = nextNode;
        }

        if (belowHead == null) {
            return restHead;
        }

        belowTail.next = restHead;

        return belowHead;
    }

    static void display(Node head) {

        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        for (int i = 0; i < n; i++) {

            Node newNode = new Node(sc.nextInt());

            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        int passMark = sc.nextInt();

        head = partition(head, passMark);

        display(head);

        sc.close();
    }
}
