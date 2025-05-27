import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Helpers {
    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void println() {
        System.out.println();
    }

    public static void dotAnimation() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            print(".");
        }
    }

    public static String generateName() {
        StringBuilder nameBuilder = new StringBuilder("");

        int n = ThreadLocalRandom.current().nextInt(5, 11); //name length

        for (int i = 0; i < n; i++) {
            char c = (char) ThreadLocalRandom.current().nextInt(97, 123); //casting gives us character instead of ASCII code
            nameBuilder.append(c);
        }
        return nameBuilder.toString();
    }

    public static ArrayList<String> getCities(int n) { //generate cities
        ArrayList<String> cities = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String m = generateName();

            while (cities.contains(m)) { //generating always unique cities
                m = generateName();
            }
            cities.add(m);
        }
        return cities;
    }

    public static void programStateToFile() {
        try (PrintWriter write = new PrintWriter("AppState.txt")) {

            TrainSet.sortTrainSets();

            String cars = "";
            for (RailroadCar c : Presentation.cars) {
                cars += c.txt() + "\n";
            }

            String trainSets = "";
            for (TrainSet c : TrainSet.trainSets) {
                trainSets += c.txt() + "\n";
            }

            write.println("Currently created cars: \n");
            write.println(cars);
            write.println();
            write.println("Currently created train sets: \n");
            write.println(trainSets);

        } catch (FileNotFoundException e) {
            Helpers.println("File AppState.txt not found!");
        }

    }

    public static void exit() {
        Helpers.print("\nExiting");
        Helpers.dotAnimation();
        System.exit(0);
    }
}

