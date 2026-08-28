public class ConstructorOverloading {

    String empId;
    String empName;
    double salary;
    boolean isIntern;

    public ConstructorOverloading(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }

    public ConstructorOverloading(String empId, String empName) {
        this(empId, empName, 0);
        this.isIntern = true;
    }

    void printProfile() {
        System.out.println(
                empId + " | " +
                empName + " | Rs " +
                salary + " | Intern: " +
                isIntern
        );
    }

    public static void main(String[] args) {

        ConstructorOverloading permanent =
                new ConstructorOverloading("E-101", "Divya", 65000);

        ConstructorOverloading intern =
                new ConstructorOverloading("E-102", "Arjun");

        permanent.printProfile();
        intern.printProfile();
    }
}