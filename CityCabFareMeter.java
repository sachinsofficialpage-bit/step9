import java.util.Scanner;

abstract class Cab {
    static final double MIN_FARE = 100.0;

    abstract double getRate();

    double calculateFare(double km) {
        return Math.max(MIN_FARE, km * getRate());
    }
}

interface NightService {
    double NIGHT_MULTIPLIER = 1.20;

    double applyNightFare(double fare);
}

class Mini extends Cab {
    double getRate() {
        return 10.0;
    }
}

class Sedan extends Cab implements NightService {
    double getRate() {
        return 14.0;
    }

    public double applyNightFare(double fare) {
        return fare * NIGHT_MULTIPLIER;
    }
}

class SUV extends Cab implements NightService {
    double getRate() {
        return 18.0;
    }

    public double applyNightFare(double fare) {
        return fare * NIGHT_MULTIPLIER;
    }
}

public class CityCabFareMeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double km = sc.nextDouble();
            String time = sc.next().toUpperCase();

            Cab cab;

            switch (type) {
                case "MINI":
                    cab = new Mini();
                    break;
                case "SEDAN":
                    cab = new Sedan();
                    break;
                case "SUV":
                    cab = new SUV();
                    break;
                default:
                    continue;
            }

            if (time.equals("NIGHT")
                    && !(cab instanceof NightService)) {
                System.out.println(
                    type + ": night service not available"
                );
                continue;
            }

            double fare = cab.calculateFare(km);

            if (time.equals("NIGHT")) {
                fare = ((NightService) cab).applyNightFare(fare);
            }

            System.out.printf("%s: %.2f%n", type, fare);
            total += fare;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}