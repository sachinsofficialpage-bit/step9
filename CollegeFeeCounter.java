import java.util.Scanner;

abstract class Student {
    String name;

    Student(String name) {
        this.name = name;
    }

    abstract double calculateFee();

    double getTransportFee() {
        return 0.0;
    }
}

interface BusUser {
    double TRANSPORT_FEE = 12000.0;
}

class DayScholar extends Student implements BusUser {
    DayScholar(String name) {
        super(name);
    }

    double calculateFee() {
        return 40000 + getTransportFee();
    }

    public double getTransportFee() {
        return TRANSPORT_FEE;
    }
}

class Hosteller extends Student {
    Hosteller(String name) {
        super(name);
    }

    double calculateFee() {
        return 40000 + 60000;
    }
}

class Scholar extends Student implements BusUser {
    Scholar(String name) {
        super(name);
    }

    double calculateFee() {
        return 20000 + getTransportFee();
    }

    public double getTransportFee() {
        return TRANSPORT_FEE;
    }
}

public class CollegeFeeCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double totalCollected = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            String name = sc.next();

            Student student;

            switch (type) {
                case "DAY_SCHOLAR":
                    student = new DayScholar(name);
                    break;
                case "HOSTELLER":
                    student = new Hosteller(name);
                    break;
                case "SCHOLAR":
                    student = new Scholar(name);
                    break;
                default:
                    continue;
            }

            double fee = student.calculateFee();
            System.out.printf("%s: %.2f%n", name, fee);
            totalCollected += fee;
        }

        System.out.printf("Total Collected: %.2f%n", totalCollected);
        sc.close();
    }
}