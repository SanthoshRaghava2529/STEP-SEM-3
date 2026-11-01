
import java.util.Scanner;

class DNode {
    int data;
    DNode prev, next;

    DNode(int data) {
        this.data = data;
        prev = next = null;
    }
}

public class InsertTailDoubly {

    static DNode insertAtTailDoubly(DNode head, int value) {

        DNode newNode = new DNode(value);

        if (head == null) {
            return newNode;
        }

        DNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
        newNode.prev = temp;

        return head;
    }

    static void display(DNode head) {
        DNode temp = head;

        while (temp != null) {
            System.out.print(temp.data);

            if (temp.next != null) {
                System.out.print(" <-> ");
            }

            temp = temp.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DNode head = null;

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            head = insertAtTailDoubly(head, sc.nextInt());
        }

        int value = sc.nextInt();

        head = insertAtTailDoubly(head, value);

        display(head);
        sc.close();
    }
}
