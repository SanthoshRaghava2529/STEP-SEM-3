import java.util.*;

interface WashType {
    String getName();
    int getDuration();
    double getCharge();
}

class QuickWash implements WashType {
    public String getName() {
        return "Quick";
    }

    public int getDuration() {
        return 30;
    }

    public double getCharge() {
        return 20;
    }
}

class NormalWash implements WashType {
    public String getName() {
        return "Normal";
    }

    public int getDuration() {
        return 45;
    }

    public double getCharge() {
        return 30;
    }
}

class HeavyWash implements WashType {
    public String getName() {
        return "Heavy";
    }

    public int getDuration() {
        return 60;
    }

    public double getCharge() {
        return 45;
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class WashingMachine {
    private String id;
    private boolean busy;
    private WashCycle currentCycle;

    WashingMachine(String id) {
        this.id = id;
        busy = false;
    }

    String getId() {
        return id;
    }

    boolean isBusy() {
        return busy;
    }

    void startWash(Student student, WashType type) {
        if (busy) {
            System.out.println("Machine " + id + " is currently busy.");
            return;
        }

        currentCycle = new WashCycle(student, this, type);
        busy = true;

        System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f%n",
                type.getName(), id, student.getName(),
                type.getDuration(), type.getCharge());
    }

    void completeWash() {
        if (!busy) {
            return;
        }

        System.out.println(id + " cycle completed.");
        busy = false;
        currentCycle = null;
        System.out.println(id + " is now free.");
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }
}

public class HostelLaundryQueue {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());

        m2.startWash(ravi, new HeavyWash());

        m1.completeWash();

        m1.startWash(neha, new NormalWash());
    }
}
