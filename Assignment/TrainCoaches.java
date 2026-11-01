
import java.util.Scanner;

class Node {
    String data;
    Node prev;
    Node next;

    Node(String data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    Node head;
    Node tail;

    // Insert at end
    void addLast(String name) {
        Node newNode = new Node(name);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    // Remove coach by name
    void remove(String name) {
        Node temp = head;

        while (temp != null && !temp.data.equals(name)) {
            temp = temp.next;
        }

        if (temp == null) {
            return;
        }

        // If deleting first node
        if (temp == head) {
            head = temp.next;
        }
        else {
            temp.prev.next = temp.next;
        }

        // If deleting last node
        if (temp == tail) {
            tail = temp.prev;
        }
        else {
            temp.next.prev = temp.prev;
        }

        // Detach deleted node
        temp.prev = null;
        temp.next = null;
    }

    // Print from head to tail
    void printForward() {
        Node temp = head;

        System.out.print("Forward: ");

        while (temp != null) {
            System.out.print(temp.data);

            if (temp.next != null) {
                System.out.print(" <-> ");
            }

            temp = temp.next;
        }

        System.out.println();
    }

    // Print from tail to head
    void printBackward() {
        Node temp = tail;

        System.out.print("Backward: ");

        while (temp != null) {
            System.out.print(temp.data);

            if (temp.prev != null) {
                System.out.print(" <-> ");
            }

            temp = temp.prev;
        }

        System.out.println();
    }
}

public class TrainCoaches {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DoublyLinkedList list = new DoublyLinkedList();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            list.addLast(sc.next());
        }

        String removeName = sc.next();

        list.remove(removeName);

        list.printForward();
        list.printBackward();

        sc.close();
    }
}
