
import java.util.Scanner;

class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class CircularLinkedList {
    Node tail;

    // Insert at end
    void addLast(String name) {
        Node newNode = new Node(name);

        if (tail == null) {
            tail = newNode;
            tail.next = newNode;
            return;
        }

        newNode.next = tail.next;
        tail.next = newNode;
        tail = newNode;
    }

    // Print players for given turns
    void printTurns(int turns) {
        if (tail == null) {
            return;
        }

        Node current = tail.next;

        for (int i = 0; i < turns; i++) {
            System.out.print(current.data);

            if (i < turns - 1) {
                System.out.print(" ");
            }

            current = current.next;
        }

        System.out.println();
    }
}

public class RoundRobinGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CircularLinkedList list = new CircularLinkedList();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            list.addLast(sc.next());
        }

        int turns = sc.nextInt();

        list.printTurns(turns);

        sc.close();
    }
}
