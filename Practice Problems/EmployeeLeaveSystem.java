abstract class Employee {
    protected String name;

    Employee(String name) {
        this.name = name;
    }

    abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class Contractor extends Employee {
    Contractor(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 5;
    }
}

class LeaveRequest {
    Employee employee;
    String startDate;
    String endDate;
    int days;
    String status;

    LeaveRequest(Employee employee, String startDate, String endDate, int days) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.status = "Pending";
    }

    void approve() {
        if (!status.equals("Pending")) {
            System.out.println("Cannot approve. Current status: " + status);
            return;
        }

        if (employee.canTakeLeave(days)) {
            status = "Approved";
            System.out.println(employee.name + "'s leave request (" +
                    startDate + "-" + endDate + ") approved.");
            System.out.println("Status: " + status);
        } else {
            status = "Rejected";
            System.out.println(employee.name + "'s leave request rejected.");
            System.out.println("Status: " + status);
        }
    }

    void reject() {
        if (!status.equals("Pending")) {
            System.out.println("Cannot reject. Current status: " + status);
            return;
        }

        status = "Rejected";
        System.out.println(employee.name + "'s leave request (" +
                startDate + "-" + endDate + ") rejected.");
        System.out.println("Status: " + status);
    }

    void changeToPending() {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change leave request status from "
                    + status + " to Pending.");
        }
    }
}

public class EmployeeLeaveSystem {
    public static void main(String[] args) {

        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest request1 =
                new LeaveRequest(john, "Jan 1", "Jan 5", 5);

        System.out.println("Leave request submitted for John (Jan 1-5).");
        System.out.println("Status: " + request1.status);

        request1.approve();

        LeaveRequest request2 =
                new LeaveRequest(jane, "Feb 10", "Feb 11", 2);

        System.out.println("\nLeave request submitted for Jane (Feb 10-11).");
        System.out.println("Status: " + request2.status);

        request2.reject();

        System.out.println();
        request1.changeToPending();
    }
}
