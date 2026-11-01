
import java.util.*;

abstract class BankAccount {
    String id;
    double balance;

    BankAccount(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    abstract void withdraw(double amount);
}

class Savings extends BankAccount {
    Savings(String id, double balance) {
        super(id, balance);
    }

    void withdraw(double amount) {
        if (balance - amount >= 1000) {
            balance -= amount;
            System.out.println(id + " balance " + (int)balance);
        } else {
            System.out.println(id + " rejected: minimum balance 1000");
        }
    }
}

class Current extends BankAccount {
    Current(String id, double balance) {
        super(id, balance);
    }

    void withdraw(double amount) {
        if (balance - amount >= -5000) {
            balance -= amount;
            System.out.println(id + " balance " + (int)balance);
        } else {
            System.out.println(id + " rejected: overdraft limit 5000");
        }
    }
}

public class BankAccountSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, BankAccount> accounts = new HashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("Savings")) {
                String id = sc.next();
                double balance = sc.nextDouble();
                accounts.put(id, new Savings(id, balance));
            }
            else if (operation.equals("Current")) {
                String id = sc.next();
                double balance = sc.nextDouble();
                accounts.put(id, new Current(id, balance));
            }
            else if (operation.equals("WITHDRAW")) {
                String id = sc.next();
                double amount = sc.nextDouble();

                if (accounts.containsKey(id)) {
                    accounts.get(id).withdraw(amount);
                } else {
                    System.out.println("Account not found");
                }
            }
        }
        sc.close();
    }
}
