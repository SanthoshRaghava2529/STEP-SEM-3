
import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    Node head;

    // Insert at beginning
    void addFirst(int token) {
        Node newNode = new Node(token);

        newNode.next = head;
        head = newNode;
    }

    // Insert at end
    void addLast(int token) {
        Node newNode = new Node(token);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }

    // Insert at given index
    void insertAt(int index, int token) {
        if (index < 0) {
            System.out.println("Invalid index");
            return;
        }

        if (index == 0) {
            addFirst(token);
            return;
        }

        Node temp = head;

        for (int i = 0; i < index - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Invalid index");
            return;
        }

        Node newNode = new Node(token);

        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Display list
    void display() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }
}

public class PriorityTokenInsertion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        SinglyLinkedList list = new SinglyLinkedList();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            list.addLast(sc.nextInt());
        }

        int operations = sc.nextInt();

        for (int i = 0; i < operations; i++) {
            String op = sc.next();

            if (op.equals("addFirst")) {
                list.addFirst(sc.nextInt());
            }
            else if (op.equals("addLast")) {
                list.addLast(sc.nextInt());
            }
            else if (op.equals("insertAt")) {
                int index = sc.nextInt();
                int token = sc.nextInt();

                list.insertAt(index, token);
            }
        }

        list.display();
        sc.close();
    }
}
