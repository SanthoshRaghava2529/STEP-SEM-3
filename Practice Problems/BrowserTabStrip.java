
import java.util.Scanner;

class Node {
    String data;
    Node prev, next;

    Node(String data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class BrowserTabStrip {

    Node head = null;
    Node current = null;

    void open(String name) {
        Node newNode = new Node(name);

        if (current == null) {
            head = current = newNode;
        } else {
            newNode.prev = current;
            newNode.next = current.next;

            if (current.next != null) {
                current.next.prev = newNode;
            }

            current.next = newNode;
            current = newNode;
        }
    }

    void close() {
        if (current == null) {
            return;
        }

        Node nextNode = current.next;
        Node prevNode = current.prev;

        if (prevNode != null) {
            prevNode.next = nextNode;
        } else {
            head = nextNode;
        }

        if (nextNode != null) {
            nextNode.prev = prevNode;
            current = nextNode;
        } else {
            current = prevNode;
        }
    }

    void back() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("BACK: already at first tab");
        }
    }

    void forward() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("FORWARD: already at last tab");
        }
    }

    void display() {
        Node temp = head;

        while (temp != null) {
            if (temp == current) {
                System.out.print("[" + temp.data + "]");
            } else {
                System.out.print(temp.data);
            }

            if (temp.next != null) {
                System.out.print(" <-> ");
            }

            temp = temp.next;
        }

        System.out.println();
    }

    void printBackward() {
        Node temp = head;

        if (temp == null) {
            System.out.println("Backward: empty");
            return;
        }

        while (temp.next != null) {
            temp = temp.next;
        }

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BrowserTabStrip browser = new BrowserTabStrip();

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String command = sc.nextLine();

            if (command.startsWith("OPEN ")) {
                String name = command.substring(5);
                browser.open(name);

                System.out.print("OPEN " + name + ": ");
                browser.display();
            }

            else if (command.equals("CLOSE")) {
                String name = browser.current == null
                        ? "" : browser.current.data;

                browser.close();

                System.out.print("CLOSE " + name + ": ");
                browser.display();
            }

            else if (command.equals("BACK")) {
                Node old = browser.current;
                browser.back();

                if (old != browser.current) {
                    System.out.print("BACK: ");
                    browser.display();
                }
            }

            else if (command.equals("FORWARD")) {
                Node old = browser.current;
                browser.forward();

                if (old != browser.current) {
                    System.out.print("FORWARD: ");
                    browser.display();
                }
            }

            else if (command.equals("PRINT BACKWARD")) {
                browser.printBackward();
            }
        }

        sc.close();
    }
}
