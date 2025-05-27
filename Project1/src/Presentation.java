import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Presentation {
    static String tempString; //variable to store y/n answers and then convert it into boolean
    static ArrayList<Locomotive> locomotives = new ArrayList<>();
    static ArrayList<RailroadCar> cars = new ArrayList<>();
    public static ArrayList<String> stations = Helpers.getCities(100);

    public static void mainMenu() {

        int choice = 0;

        try {
            while (true) {
                RailroadCar.yn = "y";
                Helpers.println("\n_____________Menu______________");
                Helpers.println("|Option | Description         | ");
                Helpers.println("-------------------------------");
                Helpers.println("|   1   | Creates new trainSet|");
                Helpers.println("|   2   | Loads car           |");
                Helpers.println("|   3   | Shows all locos     |");
                Helpers.println("|   4   | Shows all cars      |");
                Helpers.println("|   5   | Shows all trainSets |");
                Helpers.println("|   6   | Shows report        |");
                Helpers.println("|   7   | Removes cars        |");
                Helpers.println("|   8   | Removes trainSet    |");
                Helpers.println("|   9   | Exits               |");
                Helpers.println("-------------------------------");

                Scanner userInput = new Scanner(System.in);
                Helpers.print("\nMy choice: ");
                choice = userInput.nextInt();
                if (choice < 1 || choice > 9) continue;
                else break;
            }
        } catch (InputMismatchException e) {
            Helpers.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            Helpers.println();
            mainMenu();
        }

        Locomotive.locoCreator(choice);
    }

    public static void main(String[] args) throws RailRoadHazard {
        Helpers.println("________Railway Simulator_______");
        Helpers.println("Developed by Patryk Gawin s28182");
        Helpers.println("--------------------------------");

        Route.connections = Route.getConnections(stations);
        Route.routes = Route.getRoutes();
        /*for (String k : connections.keySet()){
            System.out.println(k+": "+connections.get(k));
        }*/

        locomotives = Locomotive.generateLocomotive(stations);
        TrainSet.trainSets = TrainSet.generateTrainSet(locomotives);

        for (TrainSet t : TrainSet.trainSets){
        Thread th = new Thread(t); //all trains simultaneously
        //System.out.println(t.getLocomotive().getStops()); //display route of locomotive
        th.start();
        }

        Thread save = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Helpers.programStateToFile();
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        Helpers.println("Thread error");
                    }
                }
            }
        });
        save.start();

        mainMenu();
    }
}
