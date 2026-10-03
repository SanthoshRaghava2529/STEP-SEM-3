interface MembershipPlan {
    String getName();
    int getMonths();
    double calculateFee();
}

class MonthlyPlan implements MembershipPlan {

    public String getName() {
        return "Monthly";
    }

    public int getMonths() {
        return 1;
    }

    public double calculateFee() {
        return 1000;
    }
}

class QuarterlyPlan implements MembershipPlan {

    public String getName() {
        return "Quarterly";
    }

    public int getMonths() {
        return 3;
    }

    public double calculateFee() {
        return 1000 * 3 * 0.90;
    }
}

class AnnualPlan implements MembershipPlan {

    public String getName() {
        return "Annual";
    }

    public int getMonths() {
        return 12;
    }

    public double calculateFee() {
        return 1000 * 12 * 0.75;
    }
}

class Member {
    private String name;

    Member(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class Membership {

    private Member member;
    private MembershipPlan plan;
    private String status;

    Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = "Active";
    }

    void checkIn() {
        if (status.equals("Active")) {
            System.out.println(
                    member.getName() +
                    " checked in successfully.");
        } else {
            System.out.println(
                    "Check-in denied: " +
                    member.getName() +
                    "'s membership is " + status + ".");
        }
    }

    void freeze() {
        if (status.equals("Active")) {
            status = "Frozen";

            System.out.println(
                    member.getName() +
                    "'s membership frozen. Status: Frozen.");
        } else if (status.equals("Expired")) {
            System.out.println(
                    "Cannot freeze an Expired membership.");
        } else {
            System.out.println(
                    "Membership is already Frozen.");
        }
    }

    void unfreeze() {
        if (status.equals("Frozen")) {
            status = "Active";

            System.out.println(
                    member.getName() +
                    "'s membership unfrozen. Status: Active.");
        } else if (status.equals("Expired")) {
            System.out.println(
                    "Cannot unfreeze an Expired membership.");
        }
    }

    void expire() {
        status = "Expired";

        System.out.println(
                member.getName() +
                "'s membership expired. Status: Expired.");
    }

    String getStatus() {
        return status;
    }
}

public class FitZoneMembershipDesk {

    public static void main(String[] args) {

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership =
                new Membership(asha, new QuarterlyPlan());

        Membership raviMembership =
                new Membership(ravi, new MonthlyPlan());

        System.out.printf(
                "Quarterly membership created for Asha. Fee: ₹%.2f. Status: Active.%n",
                2700.0);

        System.out.printf(
                "Monthly membership created for Ravi. Fee: ₹%.2f. Status: Active.%n",
                1000.0);

        ashaMembership.checkIn();

        ashaMembership.freeze();

        ashaMembership.checkIn();

        raviMembership.expire();

        raviMembership.freeze();
    }
}
