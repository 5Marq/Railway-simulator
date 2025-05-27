import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TrainSet implements Runnable {
    private Locomotive locomotive;
    private int ID;
    private static int counter = 0;
    static String yn = "n";
    private ArrayList<RailroadCar> cars;
    public static ArrayList<TrainSet> trainSets = new ArrayList<>();

    /*public static void trainTraverse(double distanceToTravel, double speed, TrainSet trainSet) throws RailRoadHazard {

        int secondsInTravel = 0;
        double distanceTravelled = 0;

        while (distanceTravelled < distanceToTravel) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            secondsInTravel++;

            if (Math.random() > 0.5) speed *= 1.03;
            else speed *= 0.97;

            if (speed > 200) {
                speed *= 0.97;
                speed *= 0.97;
                throw new RailRoadHazard("Railroad Hazard! Train going too fast! Decreasing speed.");
            }


            double kms = speed * 0.0002777778;

            distanceTravelled += kms; // km/h converted to km/s

            double distanceLeft = distanceToTravel - distanceTravelled;

            if (distanceLeft <= 0) {
                double distanceSave = distanceLeft;
                distanceLeft = 0;
                distanceTravelled = distanceToTravel;
                speed = 0;
            }


            Helpers.print("Distance to travel: " + distanceToTravel + " ");
            Helpers.print("| Distance travelled: " + distanceTravelled + " ");
            Helpers.print("| Distance left: " + distanceLeft + " ");
            Helpers.print("| Current speed: " + speed + " ");
            Helpers.print("| Seconds in travel: " + secondsInTravel + " ");
            Helpers.println();

        }
        Helpers.println();
        Helpers.println("Train arrived at its destination");

    }
*/
    public void addCar(RailroadCar c) {
        cars.add(c);
    }

    public void removeCar() {
        cars.remove(cars.size() - 1);
    }

    public void sortCars() {
        Collections.sort(cars, new Comparator<RailroadCar>() {
            @Override
            public int compare(RailroadCar o1, RailroadCar o2) {
                if (o1.getGrossWeight() > o2.getGrossWeight()) {
                    return 1;
                } else if (o1.getGrossWeight() == o2.getGrossWeight()) {
                    return 0;
                } else return -1;
            }
        });
    }

    public static void sortTrainSets() {
        for (TrainSet t : trainSets) {
            t.sortCars();
        }
        trainSets.sort(new Comparator<TrainSet>() {
            @Override
            public int compare(TrainSet o1, TrainSet o2) {
                if (o1.getLocomotive().getRoutePercent() > o2.getLocomotive().getRoutePercent()) { //positive if greater
                    return 1;
                } else if (o1.getLocomotive().getRoutePercent() == o2.getLocomotive().getRoutePercent()) {
                    return 0;
                } else return -1;
            }
        });
    }

    public static boolean collision(String s, String s1, int ID) {
        for (TrainSet t : trainSets){
            if (t.getLocomotive().getCurrentStop()!=null && t.getLocomotive().getNextStop()!= null && t.getLocomotive().getCurrentStop().equals(s)
                    && t.getLocomotive().getNextStop().equals(s1) && t.getLocomotive().getID() != ID){
                System.out.println("s"+s);
                System.out.println("s1"+s1);
                System.out.println("ID"+ID);
                System.out.println(t);
                return true;
            }
        }
        return false;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public ArrayList<RailroadCar> getCars() {
        return cars;
    }

    public static ArrayList<TrainSet> generateTrainSet(ArrayList<Locomotive> locomotives) {
        ArrayList<TrainSet> trainSets = new ArrayList<>();
        for (Locomotive l : locomotives) {
            TrainSet t = new TrainSet(l, new ArrayList<>());
            int n = ThreadLocalRandom.current().nextInt(5, 11);
            for (int i = 0; i < n; i++) {
                int type = ThreadLocalRandom.current().nextInt(0, 10);
                switch (type) {
                    case 0 -> {
                        double weight = ThreadLocalRandom.current().nextDouble(10000, 30000); //from 10 to 30tons of weight
                        RailroadCar c = new PassengerCar(weight, weight, ThreadLocalRandom.current().nextInt(1, 3),
                                ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                                ThreadLocalRandom.current().nextInt(15, 70)); //load measured in passengers
                        l.checkLoadAndUpdate(weight);
                        t.cars.add(c);
                        Presentation.cars.add(c);
                    }
                    case 1 -> {
                        double weight = ThreadLocalRandom.current().nextDouble(8000, 25000);
                        RailroadCar c = new PostOfficeCar(weight, weight, ThreadLocalRandom.current().nextInt(10, 100),
                                ThreadLocalRandom.current().nextInt(1, 15),
                                ThreadLocalRandom.current().nextInt(1000, 2000));
                        l.checkLoadAndUpdate(weight);
                        l.checkMaxRailroadCarsGridAndUpdate(c);
                        t.cars.add(c);
                        Presentation.cars.add(c);
                    }
                    case 2 -> {
                        double weight = ThreadLocalRandom.current().nextDouble(7000, 24000);
                        RailroadCar c = new BaggageCar(weight, weight, ThreadLocalRandom.current().nextInt(10, 100),
                                ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                                ThreadLocalRandom.current().nextInt(1000, 2000));
                        l.checkLoadAndUpdate(weight);
                        l.checkMaxRailroadCarsGridAndUpdate(c);
                        t.cars.add(c);
                        Presentation.cars.add(c);
                    }
                    case 3 -> {
                        double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                        RailroadCar c = new RestaurantCar(weight, weight, ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                                ThreadLocalRandom.current().nextInt(10, 40), //load measured in passengers
                                ThreadLocalRandom.current().nextDouble(0, 2) > 0, ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                                ThreadLocalRandom.current().nextDouble(0, 2) > 0, ThreadLocalRandom.current().nextDouble(0, 2) > 0);
                        l.checkLoadAndUpdate(weight);
                        l.checkMaxRailroadCarsGridAndUpdate(c);
                        t.cars.add(c);
                        Presentation.cars.add(c);
                    }
                    case 4 -> {
                        int minTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                        int maxTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                        while (maxTemperature < minTemperature) {
                            maxTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                        }

                        double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                        RailroadCar c = new RefrigeratedCar(weight, weight, ThreadLocalRandom.current().nextInt(1000, 10000),
                                minTemperature, maxTemperature, ThreadLocalRandom.current().nextInt(50000, 90000));
                        l.checkLoadAndUpdate(weight);
                        l.checkMaxRailroadCarsGridAndUpdate(c);
                        t.cars.add(c);
                        Presentation.cars.add(c);
                    }
                    case 5 -> {
                        int minTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                        int maxTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                        while (maxTemperature < minTemperature) {
                            maxTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                        }
                        double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                        RailroadCar c = new LiquidCar(weight, weight, ThreadLocalRandom.current().nextInt(500, 10000),
                                ThreadLocalRandom.current().nextDouble(0, 2) > 0, ThreadLocalRandom.current().nextInt(1, 11),
                                ThreadLocalRandom.current().nextInt(50000, 90000));
                        l.checkLoadAndUpdate(weight);
                        l.checkMaxRailroadCarsGridAndUpdate(c);
                        t.cars.add(c);
                        Presentation.cars.add(c);
                    }
                    case 6 -> {
                        int minTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                        int maxTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                        while (maxTemperature < minTemperature) {
                            maxTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                        }
                        double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                        RailroadCar c = new GasCar(weight, weight, ThreadLocalRandom.current().nextInt(500, 10000),
                                ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                                ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                                ThreadLocalRandom.current().nextInt(50000, 90000));
                        l.checkLoadAndUpdate(weight);
                        l.checkMaxRailroadCarsGridAndUpdate(c);
                        t.cars.add(c);
                        Presentation.cars.add(c);
                    }
                    case 7 -> {
                        double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                        RailroadCar c = new ExplosiveCar(weight, weight, ThreadLocalRandom.current().nextInt(500, 10000),
                                ThreadLocalRandom.current().nextInt(1, 11),
                                ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                                ThreadLocalRandom.current().nextInt(50000, 90000));
                        l.checkLoadAndUpdate(weight);
                        l.checkMaxRailroadCarsGridAndUpdate(c);
                        t.cars.add(c);
                        Presentation.cars.add(c);
                    }
                    case 8 -> {
                        double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                        RailroadCar c = new ToxicCar(weight, weight, ThreadLocalRandom.current().nextInt(500, 10000),
                                ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                                ThreadLocalRandom.current().nextInt(50000, 90000));
                        l.checkLoadAndUpdate(weight);
                        l.checkMaxRailroadCarsGridAndUpdate(c);
                        t.cars.add(c);
                        Presentation.cars.add(c);
                    }
                    case 9 -> {
                        double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                        RailroadCar c = new LiquidToxicCar(weight, weight, ThreadLocalRandom.current().nextInt(500, 10000),
                                ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                                ThreadLocalRandom.current().nextInt(1, 11),
                                ThreadLocalRandom.current().nextInt(50000, 90000));
                        l.checkLoadAndUpdate(weight);
                        l.checkMaxRailroadCarsGridAndUpdate(c);
                        t.cars.add(c);
                        Presentation.cars.add(c);
                    }
                }
            }
            trainSets.add(t);
        }
        return trainSets;
    }

    public static void generateOneTrainSet(Locomotive l) {
        ArrayList<TrainSet> trainSets = new ArrayList<>();
        TrainSet t = new TrainSet(l, new ArrayList<>());
        int n = ThreadLocalRandom.current().nextInt(5, 11);
        for (int i = 0; i < n; i++) {
            int type = ThreadLocalRandom.current().nextInt(0, 10);
            switch (type) {
                case 0 -> {
                    double weight = ThreadLocalRandom.current().nextDouble(10000, 30000); //from 10 to 30tons of weight
                    RailroadCar c = new PassengerCar(weight, weight, ThreadLocalRandom.current().nextInt(1, 3),
                            ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                            ThreadLocalRandom.current().nextInt(15, 70)); //load measured in passengers
                    l.checkLoadAndUpdate(weight);
                    t.cars.add(c);
                    Presentation.cars.add(c);
                }
                case 1 -> {
                    double weight = ThreadLocalRandom.current().nextDouble(8000, 25000);
                    RailroadCar c = new PostOfficeCar(weight, weight, ThreadLocalRandom.current().nextInt(10, 100),
                            ThreadLocalRandom.current().nextInt(1, 15),
                            ThreadLocalRandom.current().nextInt(1000, 2000));
                    l.checkLoadAndUpdate(weight);
                    l.checkMaxRailroadCarsGridAndUpdate(c);
                    t.cars.add(c);
                    Presentation.cars.add(c);
                }
                case 2 -> {
                    double weight = ThreadLocalRandom.current().nextDouble(7000, 24000);
                    RailroadCar c = new BaggageCar(weight, weight, ThreadLocalRandom.current().nextInt(10, 100),
                            ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                            ThreadLocalRandom.current().nextInt(1000, 2000));
                    l.checkLoadAndUpdate(weight);
                    l.checkMaxRailroadCarsGridAndUpdate(c);
                    t.cars.add(c);
                    Presentation.cars.add(c);
                }
                case 3 -> {
                    double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                    RailroadCar c = new RestaurantCar(weight, weight, ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                            ThreadLocalRandom.current().nextInt(10, 40), //load measured in passengers
                            ThreadLocalRandom.current().nextDouble(0, 2) > 0, ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                            ThreadLocalRandom.current().nextDouble(0, 2) > 0, ThreadLocalRandom.current().nextDouble(0, 2) > 0);
                    l.checkLoadAndUpdate(weight);
                    l.checkMaxRailroadCarsGridAndUpdate(c);
                    t.cars.add(c);
                    Presentation.cars.add(c);
                }
                case 4 -> {
                    int minTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                    int maxTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                    while (maxTemperature < minTemperature) {
                        maxTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                    }

                    double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                    RailroadCar c = new RefrigeratedCar(weight, weight, ThreadLocalRandom.current().nextInt(1000, 10000),
                            minTemperature, maxTemperature, ThreadLocalRandom.current().nextInt(50000, 90000));
                    l.checkLoadAndUpdate(weight);
                    l.checkMaxRailroadCarsGridAndUpdate(c);
                    t.cars.add(c);
                    Presentation.cars.add(c);
                }
                case 5 -> {
                    int minTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                    int maxTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                    while (maxTemperature < minTemperature) {
                        maxTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                    }
                    double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                    RailroadCar c = new LiquidCar(weight, weight, ThreadLocalRandom.current().nextInt(500, 10000),
                            ThreadLocalRandom.current().nextDouble(0, 2) > 0, ThreadLocalRandom.current().nextInt(1, 11),
                            ThreadLocalRandom.current().nextInt(50000, 90000));
                    l.checkLoadAndUpdate(weight);
                    l.checkMaxRailroadCarsGridAndUpdate(c);
                    t.cars.add(c);
                    Presentation.cars.add(c);
                }
                case 6 -> {
                    int minTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                    int maxTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                    while (maxTemperature < minTemperature) {
                        maxTemperature = ThreadLocalRandom.current().nextInt(-50, 0);
                    }
                    double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                    RailroadCar c = new GasCar(weight, weight, ThreadLocalRandom.current().nextInt(500, 10000),
                            ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                            ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                            ThreadLocalRandom.current().nextInt(50000, 90000));
                    l.checkLoadAndUpdate(weight);
                    l.checkMaxRailroadCarsGridAndUpdate(c);
                    t.cars.add(c);
                    Presentation.cars.add(c);
                }
                case 7 -> {
                    double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                    RailroadCar c = new ExplosiveCar(weight, weight, ThreadLocalRandom.current().nextInt(500, 10000),
                            ThreadLocalRandom.current().nextInt(1, 11),
                            ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                            ThreadLocalRandom.current().nextInt(50000, 90000));
                    l.checkLoadAndUpdate(weight);
                    l.checkMaxRailroadCarsGridAndUpdate(c);
                    t.cars.add(c);
                    Presentation.cars.add(c);
                }
                case 8 -> {
                    double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                    RailroadCar c = new ToxicCar(weight, weight, ThreadLocalRandom.current().nextInt(500, 10000),
                            ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                            ThreadLocalRandom.current().nextInt(50000, 90000));
                    l.checkLoadAndUpdate(weight);
                    l.checkMaxRailroadCarsGridAndUpdate(c);
                    t.cars.add(c);
                    Presentation.cars.add(c);
                }
                case 9 -> {
                    double weight = ThreadLocalRandom.current().nextDouble(5000, 15000);
                    RailroadCar c = new LiquidToxicCar(weight, weight, ThreadLocalRandom.current().nextInt(500, 10000),
                            ThreadLocalRandom.current().nextDouble(0, 2) > 0,
                            ThreadLocalRandom.current().nextInt(1, 11),
                            ThreadLocalRandom.current().nextInt(50000, 90000));
                    l.checkLoadAndUpdate(weight);
                    l.checkMaxRailroadCarsGridAndUpdate(c);
                    t.cars.add(c);
                    Presentation.cars.add(c);
                }
            }
        }
        trainSets.add(t);
    }

    public static void showTrainSet() {
        Helpers.println("\n________Created train sets_________");
        int nSet = 0;
        for (TrainSet trainset : trainSets) {
            System.out.print(trainset);
            nSet++;
        }
        Helpers.print("\nExiting to main menu in 3s");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            Helpers.print(".");
        }
        Presentation.mainMenu();
    }

    public String displayID() {
        return "Trainset_ID" + ID;
    }

    public static void trainSetCreator(ArrayList<Locomotive> locomotives, ArrayList<RailroadCar> cars) {
        TrainSet a = null;
        Scanner userInput = new Scanner(System.in);

        int choice = 1;

        try {
            while (true) {
                Helpers.println("\n_______Trainset creator_______");
                Helpers.println("|Option | Description         | ");
                Helpers.println("-------------------------------");
                Helpers.println("|   1   | Create by hand      |");
                Helpers.println("|   2   | Generate            |");
                Helpers.println("|   3   | Exit to menu        |");
                Helpers.println("-------------------------------");
                Helpers.print("\nMy choice: ");
                choice = userInput.nextInt();
                if (choice < 1 || choice > 3) continue;
                else break;
            }
        } catch (InputMismatchException e) {
            Helpers.print("\nERROR! Wrong input. Please try again");
            Helpers.println();
            Helpers.dotAnimation();
            trainSetCreator(Presentation.locomotives, Presentation.cars);
        }

        Locomotive locomotive;

        switch (choice) {
            case 1: {
                try {
                    Helpers.print("\nYou will now create locomotive, and then cars which will be automatically connected");
                    Helpers.dotAnimation();

                    Helpers.print("\nEnter locomotive name: ");
                    String name = userInput.next();
                    int n = 0;
                    choice = -1;
                    int prevChoice = 0;
                    try {
                        while (choice < 0 || choice > Presentation.stations.size()) {
                            Helpers.println("\nEnter home station: ");
                            for (String s : Presentation.stations) {
                                System.out.print("|" + n + "|" + s + " ");
                                n++;
                            }
                            n = 0;
                            Helpers.print("\n\nMy choice: ");
                            choice = userInput.nextInt();
                            prevChoice = choice;
                        }
                        String homeStation = Presentation.stations.get(choice);
                        n = 0;
                        choice = -1;
                        while (choice < 0 || choice > Presentation.stations.size() || choice == prevChoice) {
                            Helpers.println("\nEnter destination station: ");
                            for (String s : Presentation.stations) {
                                System.out.print("|" + n + "|" + s + " ");
                                n++;
                            }
                            n = 0;
                            Helpers.print("\n\nMy choice: ");
                            choice = userInput.nextInt();
                        }
                        String destinationStation = Presentation.stations.get(choice);
                        Helpers.print("\nEnter locomotive speed: ");
                        double speed = userInput.nextDouble();
                        Helpers.print("\nEnter max load: ");
                        double maxLoad = userInput.nextDouble();
                        Helpers.print("\nEnter max railroad cars: ");
                        int maxRailroadCars = userInput.nextInt();
                        Helpers.print("\nEnter max railroad cars connected to grid: ");
                        int maxRailroadCarsConnected = userInput.nextInt();
                        locomotive = new Locomotive(name, homeStation, destinationStation, speed, maxLoad, maxRailroadCars, maxRailroadCarsConnected);
                        locomotives.add(locomotive);
                        TrainSet trainSet = new TrainSet(locomotive, new ArrayList<>());
                        trainSets.add(trainSet);
                        Helpers.print("\nLocomotive '" + name + "' from '" + homeStation + "' to '" + destinationStation + "' created.");

                        createCar(locomotive, trainSet);
                    } catch (IndexOutOfBoundsException e) {
                        Helpers.print("\nERROR! There is no such station. Please try again");
                        Helpers.dotAnimation();
                        Helpers.println();
                        TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);

                    }
                } catch (InputMismatchException e) {
                    System.out.print("\nERROR! Wrong input. Please try again");
                    Helpers.dotAnimation();
                    TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
                }
            }
            case 2: {
                generateOneTrainSet(Locomotive.generateOneLocomotive(Presentation.stations));
                Helpers.println("New train set generated!");
                trainSetCreator(Presentation.locomotives, Presentation.cars);
                break;
            }
            case 3: {
                Presentation.mainMenu();
                break;
            }
        }
    }

    public static void removeTrainSet() {
        Scanner userInput = new Scanner(System.in);

        Helpers.println("Choose train set to remove: ");
        Helpers.println("\n________Created train sets_________");
        int nSet = 0;
        for (TrainSet trainset : trainSets) {
            System.out.print(trainset);
            nSet++;
        }
        try {
            Helpers.print("\nMy choice: ");
            int choice = userInput.nextInt();

            try {
                if (choice > trainSets.size() || choice <= 0) {
                    Helpers.print("\nThere is no such train set!\n");
                    removeTrainSet();
                } else {
                    trainSets.remove(choice);
                    Helpers.println("Train set successfully removed!");
                    Helpers.print("Returning to main menu");
                    Helpers.dotAnimation();
                    Presentation.mainMenu();
                }
            } catch (IndexOutOfBoundsException e) {
                Helpers.print("\nThere is no such element! Please try again");
                Helpers.dotAnimation();
                removeTrainSet();
            }
        } catch (InputMismatchException e) {
            Helpers.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            removeTrainSet();
        }
        userInput.close();
    }

    public static void createCar(Locomotive locomotive, TrainSet trainSet) {
        Helpers.println(" Now choose which car to connect: ");

        Scanner userInput = new Scanner(System.in);
        int choice;


        while (true) {
            RailroadCar.showCarMenu();
            Helpers.print("\nMy choice: ");
            choice = userInput.nextInt();
            if (choice < 1 || choice > 11) continue;
            else break;
        }

        HashMap<Integer, Runnable> carChoice = new HashMap<>();

        carChoice.put(1, () -> RailroadCar.createPassengerCar(locomotive, trainSet));
        carChoice.put(2, () -> RailroadCar.createPostOfficeCar(locomotive, trainSet));
        carChoice.put(3, () -> RailroadCar.createBaggageCar(locomotive, trainSet));
        carChoice.put(4, () -> RailroadCar.createRestaurantCar(locomotive, trainSet));
        carChoice.put(5, () -> RailroadCar.createRefrigeratedCar(locomotive, trainSet));
        carChoice.put(6, () -> RailroadCar.createLiquidMaterialsCar(locomotive, trainSet));
        carChoice.put(7, () -> RailroadCar.createGasMaterialsCar(locomotive, trainSet));
        carChoice.put(8, () -> RailroadCar.createExplosivesCar(locomotive, trainSet));
        carChoice.put(9, () -> RailroadCar.createToxicMaterialsCar(locomotive, trainSet));
        carChoice.put(10, () -> RailroadCar.createLiquidAndToxicCar(locomotive, trainSet));
        carChoice.put(11, Presentation::mainMenu);

        if (carChoice.containsKey(choice)) {
            carChoice.get(choice).run();
        } else {
            System.out.println("There is no option " + choice + "! \nReturning to the menu");
            Helpers.dotAnimation();
            trainSetCreator(Presentation.locomotives, Presentation.cars);
        }
        userInput.close();
    }

    public double totalGoodsTransported() {
        double totalTransported = 0;
        for (RailroadCar c : cars) {
            totalTransported += c.getCurrentLoad();
        }
        return totalTransported;
    }

    public static void showReport() {
        Scanner userInput = new Scanner(System.in);
        int choice = -1;

        try {
            try {
                while (choice < 0 || choice > trainSets.size()) {
                    Helpers.println("\n________Created train sets_________");
                    int nSet = 0;
                    for (TrainSet trainset : trainSets) {
                        System.out.print(trainset.displayID() + " ");
                        nSet++;
                    }

                    Helpers.print("\n\nChoose the train set to display more detailed report: ");
                    choice = userInput.nextInt();
                }
            } catch (InputMismatchException e) {
                Helpers.print("ERROR! Wrong input. Please try again");
                Helpers.dotAnimation();
                showReport();
            }
            TrainSet t = trainSets.get(choice);
            System.out.println("\n% of total distance completed: " + t.getLocomotive().getRoutePercent()) ;
            System.out.println("\n% of distance between station '"+t.locomotive.getCurrentStop()+"' and station '"+t.locomotive.getNextStop()+": "+t.locomotive.getStationsPercent());
            System.out.println("\nLocomotive: " + t.getLocomotive());
            System.out.println("\nCars: " + t.getCars());
            System.out.println("\nTotal goods transported: "+t.totalGoodsTransported());
        } catch (IndexOutOfBoundsException e) {
            Helpers.print("ERROR! There is no such train set. Please try again");
            Helpers.dotAnimation();
            showReport();
        }
        Helpers.print("\n\nExiting to main menu in 3s");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            Helpers.print(".");
        }
        Presentation.mainMenu();

    }

    public TrainSet(Locomotive locomotive, ArrayList<RailroadCar> cars) {
        this.locomotive = locomotive;
        this.cars = cars;
        ID = counter;
        counter++;
    }

    @Override
    public String toString() {
        return "|" + ID + "|" +
                "locomotive=" + locomotive +
                ", ID=" + ID +
                ", cars=" + cars +
                '}' + "\n";
    }

    public String txt() {
        String trainSet = "_TrainSetID" + ID + ": " + locomotive.txt() + "% of route travelled: " + getLocomotive().getRoutePercent() + " Cars: ";
        String trainSets = "";
        for (RailroadCar c : cars) {
            trainSet += c.txt() + " ";
        }
        return trainSet;
    }

    @Override
    public void run() {
        while (true) {
            try {
                locomotive.run();
            } catch (RailRoadHazard e) {
                System.out.println(" TrainsetID"+ID+" LocomotiveID"+locomotive.getID());
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                Helpers.println("Thread error");
            }
        }


    }
}