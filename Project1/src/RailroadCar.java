import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
abstract class RailroadCar {
    private int ID;
    private double netWeight;
    private double grossWeight;
    public int requiresPower;
    private static int counter = 0;
    private double maxLoad;
    private double currentLoad;
    static String yn = "y";
    static double weight;

    public static void showCarMenu() {
        Helpers.println("\n_________Car Types__________");
        Helpers.println("| Option | Description     | ");
        Helpers.println("----------------------------");
        Helpers.println("|   1   | Passenger        |");
        Helpers.println("|   2   | Post Office      |");
        Helpers.println("|   3   | Baggage          |");
        Helpers.println("|   4   | Restaurant       |");
        Helpers.println("|   5   | Refrigerated     |");
        Helpers.println("|   6   | Liquid materials |");
        Helpers.println("|   7   | Gas materials    |");
        Helpers.println("|   8   | Explosives       |");
        Helpers.println("|   9   | Toxic materials  |");
        Helpers.println("|   10  | Liquid and toxic |");
        Helpers.println("|   11  | Return to menu   |");
        Helpers.println("----------------------------");
    }

    static String tempString = Presentation.tempString;

    private void updateCurrentLoad(double carWeight) {
        currentLoad += carWeight;
        grossWeight += carWeight;
    }

    public int getID() {
        return ID;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public double getCurrentLoad() {
        return currentLoad;
    }

    public static void createPassengerCar(Locomotive l, TrainSet t) {

        try {
            if (l.hasMaxRailroadCarsGrid()) {
                Helpers.print("\nYou cannot create passenger car since\nthis locomotive has maximum amount of cars connected to grid.\nReturning to the creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l);
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1); //removing earlier created trainset and locomotive
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }

            Scanner userInput = new Scanner(System.in);

            if (yn.equals("n")) TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            Helpers.print("\nProceeding to create passenger car");
            Helpers.dotAnimation();

            Helpers.print("\nConsider that when creating the car:");
            Helpers.print("\nLocomotive max load: " + l.getMaxLoad() + " current load: " + l.getCurrentLoad());

            Helpers.print("\nEnter weight: ");

            weight = userInput.nextDouble();

            if (l.checkLoadAndUpdate(weight)) {

                Helpers.print("\nEnter wagon class (any number but in reality there is 1 and 2 class): ");
                int parcelCapacity = userInput.nextInt();

                Helpers.print("\nIs air conditioned?(y/n): ");
                tempString = userInput.next();
                boolean isAirConditioned = tempString.equals("y");

                Helpers.print("\nEnter seat count: ");
                int passengerSeatCount = userInput.nextInt();

                RailroadCar passenger = new PassengerCar(weight, weight, parcelCapacity, isAirConditioned, passengerSeatCount); //setting gross weight to passengerweight because
                //there are currently no passengers on board
                Presentation.cars.add(passenger);
                t.addCar(passenger);
                Helpers.println("Passenger car was successfully created with parameters: |Weight: " + weight + " |Class: " + parcelCapacity + " |AirConditioned?: " + isAirConditioned + "" +
                        " |Seat count: " + passengerSeatCount);

                Helpers.print("\nDo you want to create another train car?(y/n): ");
                yn = userInput.next();
                if (yn.equals("y")) {
                    TrainSet.yn = "y";
                    TrainSet.createCar(l, t);
                } else Presentation.mainMenu();
            } else {
                Helpers.print("\nCannot create car since max load of this locomotive was exceeded!\nReturning to creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l);
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1);
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }
            userInput.close();
        } catch (InputMismatchException e) {
            System.out.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            createPassengerCar(l, t);
        }
        Presentation.mainMenu();
    }

    public static void createPostOfficeCar(Locomotive l, TrainSet t) {

        try {
            if (l.hasMaxRailroadCarsGrid()) {
                Helpers.print("\nYou cannot create post office car since\nthis locomotive has maximum amount of cars connected to grid.\nReturning to the creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l);
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1);
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }

            Scanner userInput = new Scanner(System.in);

            if (yn.equals("n")) TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            Helpers.print("\nProceeding to create post office car");
            Helpers.dotAnimation();

            Helpers.print("\nConsider that when creating the car.");
            Helpers.print("\nLocomotive max load: " + l.getMaxLoad() + " current load: " + l.getCurrentLoad());

            Helpers.print("\nEnter weight: ");
            weight = userInput.nextDouble();

            if (l.checkLoadAndUpdate(weight)) {

                Helpers.print("\nEnter parcel capacity: : ");
                int parcelCapacity = userInput.nextInt();

                Helpers.print("\nEnter parcel fee: ");
                int parcelFee = userInput.nextInt();

                Helpers.print("\nEnter max load: ");
                double maxLoad = userInput.nextInt();

                RailroadCar postOffice = new PostOfficeCar(weight, weight, parcelCapacity, parcelFee, maxLoad); //setting gross weight to passengerweight because
                //there are currently no passengers on board
                Presentation.cars.add(postOffice);
                t.addCar(postOffice);
                Helpers.println("Post office car was successfully created with parameters: |Weight: " + weight + " |Parcel capacity: " + parcelCapacity +
                        " |Parcel fee: " + parcelFee);

                Helpers.print("\nDo you want to create another train car?(y/n): ");
                yn = userInput.next();
                if (yn.equals("y")) {
                    TrainSet.yn = "y";
                    TrainSet.createCar(l, t);
                } else Presentation.mainMenu();
            } else {
                Helpers.print("\nCannot create car since max load of this locomotive was exceeded!\nReturning to creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l);
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1);
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }
            userInput.close();
        } catch (InputMismatchException e) {
            System.out.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            createPostOfficeCar(l, t);
        }
    }

    public static void createBaggageCar(Locomotive l, TrainSet t) {
        Scanner userInput = new Scanner(System.in);

        try {
            if (yn.equals("n")) TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            Helpers.print("\nProceeding to create baggage car");
            Helpers.dotAnimation();

            Helpers.print("\nConsider that when creating the car.");
            Helpers.print("\nLocomotive max load: " + l.getMaxLoad() + " current load: " + l.getCurrentLoad());

            Helpers.print("\nEnter weight: ");
            weight = userInput.nextDouble();

            if (l.checkLoadAndUpdate(weight)) {

                Helpers.print("\nEnter baggage capacity: ");
                int baggageCapacity = userInput.nextInt();

                Helpers.print("\nHas security?(y/n): ");
                tempString = userInput.next();
                boolean hasSecurity = tempString.equals("y");

                Helpers.print("\nEnter maxLoad: ");
                double maxLoad = userInput.nextDouble();

                RailroadCar baggage = new BaggageCar(weight, weight, baggageCapacity, hasSecurity, maxLoad); //setting gross weight to passengerweight because
                //there are currently no passengers on board
                Presentation.cars.add(baggage);
                t.addCar(baggage);
                Helpers.println("Baggage car was successfully created with parameters: |Weight: " + weight + " |Baggage capacity: " + baggageCapacity +
                        " |Has security?: " + hasSecurity + "|Max load: " + maxLoad);

                Helpers.print("\nDo you want to create another train car?(y/n): ");
                yn = userInput.next();
                if (yn.equals("y")) {
                    TrainSet.yn = "y";
                    TrainSet.createCar(l, t);
                } else Presentation.mainMenu();
            } else {
                Helpers.print("\nCannot create car since max load of this locomotive was exceeded!\nReturning to creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l);
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1);
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }
            userInput.close();
        } catch (InputMismatchException e) {
            System.out.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            createBaggageCar(l, t);
        }
    }

    public static void createRestaurantCar(Locomotive l, TrainSet t) {
        try {
            if (l.hasMaxRailroadCarsGrid()) {
                Helpers.print("\nYou cannot create restaurant car since\nthis locomotive has maximum amount of cars connected to grid.\nReturning to the creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l);
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1);
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }

            Scanner userInput = new Scanner(System.in);

            if (yn.equals("n")) TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            Helpers.print("\nProceeding to create restaurant car");
            Helpers.dotAnimation();

            Helpers.print("Consider that when creating the car.");
            Helpers.print("\nLocomotive max load: " + l.getMaxLoad() + " current load: " + l.getCurrentLoad());

            Helpers.print("\nEnter weight: ");
            weight = userInput.nextDouble();

            if (l.checkLoadAndUpdate(weight)) {

                Helpers.print("\nIs air conditioned?(y/n): ");
                tempString = userInput.next();
                boolean isAirConditioned = tempString.equals("y");

                Helpers.print("\nEnter seat count: ");
                int seatCount = userInput.nextInt();

                Helpers.print("\nIs there a waiter?(y/n): ");
                tempString = userInput.next();
                boolean hasWaiter = tempString.equals("y");

                Helpers.print("\nIs smoking allowed?(y/n): ");
                tempString = userInput.next();
                boolean isSmokingAllowed = tempString.equals("y");

                Helpers.print("\nAre there alcoholic beverages?(y/n): ");
                tempString = userInput.next();
                boolean hasAlcohol = tempString.equals("y");

                Helpers.print("\nIs there vegan food?(y/n): ");
                tempString = userInput.next();
                boolean hasVeganFood = tempString.equals("y");

                RailroadCar restaurant = new RestaurantCar(weight, weight, isAirConditioned, seatCount,
                        hasWaiter, isSmokingAllowed, hasAlcohol, hasVeganFood);
                Presentation.cars.add(restaurant);
                t.addCar(restaurant);
                Helpers.println("Restaurant car was successfully created with parameters: |Weight: " + weight + " |Is air conditioned?: " + isAirConditioned +
                        " |Seat count: " + seatCount + " |Has waiter?: " + hasWaiter + " |Is smoking allowed?: " + isSmokingAllowed + " |Has alcohol?: " + hasAlcohol +
                        " |Has vegan food?: " + hasVeganFood);

                Helpers.print("\nDo you want to create another train car?(y/n): ");
                yn = userInput.next();
                if (yn.equals("y")) {
                    TrainSet.yn = "y";
                    TrainSet.createCar(l, t);
                } else Presentation.mainMenu();
            } else {
                Helpers.print("\nCannot create car since max load of this locomotive was exceeded!\nReturning to creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l);
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1);
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }
            userInput.close();
        } catch (InputMismatchException e) {
            System.out.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            createBaggageCar(l, t);
        }
    }

    public static void createRefrigeratedCar(Locomotive l, TrainSet t) {
        try {
            if (l.hasMaxRailroadCarsGrid()) {
                Helpers.print("\nYou cannot create refrigerated car since\nthis locomotive has maximum amount of cars connected to grid.\nReturning to the creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l); //we have to remove because we added it earlier in train set creator
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1);
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }

            Scanner userInput = new Scanner(System.in);

            if (yn.equals("n")) TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);

            Helpers.print("\nProceeding to create refrigerated(basic freight) car");
            Helpers.dotAnimation();

            Helpers.print("\nConsider that when creating the car.");
            Helpers.print("\nLocomotive max load: " + l.getMaxLoad() + " current load: " + l.getCurrentLoad());

            Helpers.print("\nEnter weight: ");
            weight = userInput.nextDouble();

            if (l.checkLoadAndUpdate(weight)) {

                Helpers.print("\nEnter max material volume: ");
                double maxMaterialVolume = userInput.nextDouble();

                Helpers.print("\nEnter min temperature: ");
                double minTemperature = userInput.nextDouble();

                Helpers.print("\nEnter max temperature: ");
                double maxTemperature = userInput.nextDouble();

                Helpers.print("\nEnter max load: ");
                double maxLoad = userInput.nextDouble();

                RailroadCar refrigerated = new RefrigeratedCar(weight, weight, maxMaterialVolume, minTemperature, maxTemperature, maxLoad);
                Helpers.println("Refrigerated car was successfully created with parameters: |Weight: " + weight + " |Max material volume: " + maxMaterialVolume + " " +
                        " |Min temperature: " + minTemperature + "" + " |Max temperature: " + maxTemperature);
                Presentation.cars.add(refrigerated);
                t.addCar(refrigerated);

                Helpers.print("\nDo you want to create another train car?(y/n): ");
                yn = userInput.next();
                if (yn.equals("y")) {
                    TrainSet.yn = "y";
                    TrainSet.createCar(l, t);
                } else Presentation.mainMenu();
            } else {
                Helpers.print("\nCannot create car since max load of this locomotive was exceeded!\nReturning to creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l);
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1);
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }
            userInput.close();
        } catch (InputMismatchException e) {
            System.out.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            createBaggageCar(l, t);
        }
    }

    public static void createLiquidMaterialsCar(Locomotive l, TrainSet t) {
        Scanner userInput = new Scanner(System.in);

        try {
            if (yn.equals("n")) TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            Helpers.print("\nProceeding to create liquid materials(basic freight) car");
            Helpers.dotAnimation();

            Helpers.print("\nConsider that when creating the car.");
            Helpers.print("\nLocomotive max load: " + l.getMaxLoad() + " current load: " + l.getCurrentLoad());

            Helpers.print("\nEnter weight: ");
            weight = userInput.nextDouble();

            if (l.checkLoadAndUpdate(weight)) {

                Helpers.print("\nEnter max material volume: ");
                double maxMaterialVolume = userInput.nextDouble();

                Helpers.print("\nIs liquid toxic?(y/n): ");
                tempString = userInput.next();
                boolean isLiquidToxic = tempString.equals("y");

                int toxicLevel;

                if (isLiquidToxic) {
                    Helpers.print("\nEnter toxic level: ");
                    toxicLevel = userInput.nextInt();
                } else toxicLevel = 0;

                Helpers.print("\nEnter max load: ");
                double maxLoad = userInput.nextDouble();

                RailroadCar liquid = new LiquidCar(weight, weight, maxMaterialVolume, isLiquidToxic, toxicLevel, maxLoad);
                Helpers.println("Liquid materials car was successfully created with parameters: |Weight: " + weight + " |Max material volume: " + maxMaterialVolume + " " +
                        " |Is liquid toxic?: " + isLiquidToxic + "" + " |Toxic level: " + toxicLevel);

                Presentation.cars.add(liquid);
                t.addCar(liquid);

                Helpers.print("\nDo you want to create another train car?(y/n): ");
                yn = userInput.next();
                if (yn.equals("y")) {
                    TrainSet.yn = "y";
                    TrainSet.createCar(l, t);
                } else Presentation.mainMenu();
            } else {
                Helpers.print("\nCannot create car since max load of this locomotive was exceeded!\nReturning to creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l);
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1);
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }
            userInput.close();
        } catch (InputMismatchException e) {
            System.out.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            createBaggageCar(l, t);
        }
    }

    public static void createGasMaterialsCar(Locomotive l, TrainSet t) {
        Scanner userInput = new Scanner(System.in);

        try {
            if (yn.equals("n")) TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            Helpers.print("\nProceeding to create gas materials(basic freight) car");
            Helpers.dotAnimation();

            Helpers.print("\nConsider that when creating the car.");
            Helpers.print("\nLocomotive max load: " + l.getMaxLoad() + " current load: " + l.getCurrentLoad());

            Helpers.print("\nEnter weight: ");
            weight = userInput.nextDouble();

            if (l.checkLoadAndUpdate(weight)) {

                Helpers.print("\nEnter max material volume: ");
                double maxMaterialVolume = userInput.nextDouble();

                Helpers.print("\nIs gas explosive?(y/n): ");
                tempString = userInput.next();
                boolean isGasExplosive = tempString.equals("y");

                Helpers.print("\nIs gas toxic?(y/n): ");
                tempString = userInput.next();
                boolean isGasToxic = tempString.equals("y");

                Helpers.print("\nEnter max load: ");
                double maxLoad = userInput.nextDouble();

                RailroadCar gas = new GasCar(weight, weight, maxMaterialVolume, isGasExplosive, isGasToxic, maxLoad);
                Helpers.println("Gas car was successfully created with parameters: |Weight: " + weight + " |Max material volume: " + maxMaterialVolume + " |Is gas explosive?: " +
                        isGasExplosive + "" + " |Is gas toxic?: " + isGasToxic);

                Presentation.cars.add(gas);
                t.addCar(gas);

                Helpers.print("\nDo you want to create another train car?(y/n): ");
                yn = userInput.next();
                if (yn.equals("y")) {
                    TrainSet.yn = "y";
                    TrainSet.createCar(l, t);
                } else Presentation.mainMenu();
            } else {
                Helpers.print("\nCannot create car since max load of this locomotive was exceeded!\nReturning to creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l);
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1);
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }
            userInput.close();
        } catch (InputMismatchException e) {
            System.out.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            createBaggageCar(l, t);
        }
    }

    public static void createExplosivesCar(Locomotive l, TrainSet t) {
        Scanner userInput = new Scanner(System.in);

        try {
            if (yn.equals("n")) TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            Helpers.print("\nProceeding to create explosive materials(heavy freight) car");
            Helpers.dotAnimation();

            Helpers.print("\nConsider that when creating the car.");
            Helpers.print("\nLocomotive max load: " + l.getMaxLoad() + " current load: " + l.getCurrentLoad());

            Helpers.print("\nEnter weight: ");
            weight = userInput.nextDouble();

            if (l.checkLoadAndUpdate(weight)) {

                Helpers.print("\nEnter max material volume: ");
                double maxMaterialVolume = userInput.nextDouble();

                Helpers.print("\nEnter explosive category: ");
                int category = userInput.nextInt();

                Helpers.print("\nIs super caution required?(y/n): ");
                tempString = userInput.next();
                boolean isSuperCautionRequired = tempString.equals("y");

                Helpers.print("\nEnter max load: ");
                double maxLoad = userInput.nextDouble();


                RailroadCar explosive = new ExplosiveCar(weight, weight, maxMaterialVolume, category, isSuperCautionRequired, maxLoad);
                Helpers.println("Explosives car was successfully created with parameters: |Weight: " + weight + " |Max material volume: " + maxMaterialVolume + " |Explosive category: " +
                        category + "" + " |Is super caution required?: " + isSuperCautionRequired);

                Presentation.cars.add(explosive);
                t.addCar(explosive);

                Helpers.print("\nDo you want to create another train car?(y/n): ");
                yn = userInput.next();
                if (yn.equals("y")) {
                    TrainSet.yn = "y";
                    TrainSet.createCar(l, t);
                } else Presentation.mainMenu();
            } else {
                Helpers.print("\nCannot create car since max load of this locomotive was exceeded!\nReturning to creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l);
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1);
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }
            userInput.close();
        } catch (InputMismatchException e) {
            System.out.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            createBaggageCar(l, t);
        }
    }

    public static void createToxicMaterialsCar(Locomotive l, TrainSet t) {
        Scanner userInput = new Scanner(System.in);

        try {
            if (yn.equals("n")) TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            Helpers.print("\nProceeding to create toxic materials(heavy freight) car");
            Helpers.dotAnimation();

            Helpers.print("\nConsider that when creating the car.");
            Helpers.print("\nLocomotive max load: " + l.getMaxLoad() + " current load: " + l.getCurrentLoad());

            Helpers.print("\nEnter weight: ");
            weight = userInput.nextDouble();
            if (l.checkLoadAndUpdate(weight)) {

                Helpers.print("\nEnter max material volume: ");
                double maxMaterialVolume = userInput.nextDouble();

                Helpers.print("\nIs toxic for people?(y/n): ");
                tempString = userInput.next();
                boolean isToxicForPeople = tempString.equals("y");

                Helpers.print("\nEnter max load: ");
                double maxLoad = userInput.nextDouble();

                RailroadCar toxic = new ToxicCar(weight, weight, maxMaterialVolume, isToxicForPeople, maxLoad);
                Presentation.cars.add(toxic);
                t.addCar(toxic);
                Helpers.println("Toxic materials car was successfully created with parameters: |Weight: " + weight + " |Max material volume: " + maxMaterialVolume + " " +
                        "|Is toxic for people?: " + isToxicForPeople);

                Helpers.print("\nDo you want to create another train car?(y/n): ");
                yn = userInput.next();
                if (yn.equals("y")) {
                    TrainSet.yn = "y";
                    TrainSet.createCar(l, t);
                } else Presentation.mainMenu();
            } else {
                Helpers.print("\nCannot create car since max load of this locomotive was exceeded!\nReturning to creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l);
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1);
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }
            userInput.close();
        } catch (InputMismatchException e) {
            System.out.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            createBaggageCar(l, t);
        }
    }

    public static void createLiquidAndToxicCar(Locomotive l, TrainSet t) {
        Scanner userInput = new Scanner(System.in);

        try {
            if (yn.equals("n")) TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            Helpers.print("\nProceeding to create liquid and toxic materials(heavy freight) car");
            Helpers.dotAnimation();

            Helpers.print("\nConsider that when creating the car.");
            Helpers.print("\nLocomotive max load: " + l.getMaxLoad() + " current load: " + l.getCurrentLoad());

            Helpers.print("\nEnter weight: ");
            weight = userInput.nextDouble();

            if (l.checkLoadAndUpdate(weight)) {

                Helpers.print("\nEnter max material volume: ");
                double maxMaterialVolume = userInput.nextDouble();

                Helpers.print("\nIs liquid toxic?(y/n): ");
                tempString = userInput.next();
                boolean isLiquidToxic = tempString.equals("y");

                int toxicLevel;

                if (isLiquidToxic) {
                    Helpers.print("\nEnter toxic level: ");
                    toxicLevel = userInput.nextInt();
                } else toxicLevel = 0;

                Helpers.print("\nEnter max load: ");
                double maxLoad = userInput.nextDouble();


                RailroadCar liquid = new LiquidCar(weight, weight, maxMaterialVolume, isLiquidToxic, toxicLevel, maxLoad);
                Presentation.cars.add(liquid);
                t.addCar(liquid);
                Helpers.println("Liquid and toxic materials car was successfully created with parameters: |Weight: " + weight + " |Max material volume: " + maxMaterialVolume + " " +
                        " |Is liquid toxic?: " + isLiquidToxic + "" + " |Toxic level: " + toxicLevel);

                Helpers.print("\nDo you want to create another train car?(y/n): ");
                yn = userInput.next();
                if (yn.equals("y")) {
                    TrainSet.yn = "y";
                    TrainSet.createCar(l, t);
                } else Presentation.mainMenu();
            } else {
                Helpers.print("\nCannot create car since max load of this locomotive was exceeded!\nReturning to creator");
                Helpers.dotAnimation();
                Presentation.locomotives.remove(l);
                t.removeCar();
                TrainSet.trainSets.remove(TrainSet.trainSets.size() - 1);
                TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars);
            }
            userInput.close();
        } catch (InputMismatchException e) {
            System.out.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            createBaggageCar(l, t);
        }
    }

    public static void carLoading(ArrayList<RailroadCar> cars) {
        Scanner userInput = new Scanner(System.in);

        if (cars.isEmpty()) {
            Helpers.print("\nThere are no cars! Returning to menu");
            Helpers.println();
            Helpers.dotAnimation();
            Presentation.mainMenu();
        }
        Helpers.println("\n________Created train sets_________");
        String trainSets = "";
        for (TrainSet c : TrainSet.trainSets) {
            trainSets += c.txt() + "\n";
        }
        Helpers.println(trainSets);

        Helpers.print("\nIn case of loading passenger restaurant car\nnote that you are 'loading' passengers not kilograms of materials!");
        Helpers.print("\n\nChoose train set to display possible cars for loading cargo/passengers: ");
        try {
            int choice = userInput.nextInt();
            int choiceTemp = choice;

            if (choice > TrainSet.trainSets.size() || choice <= 0) {
                Helpers.print("\nThere is no such train set!\n");
                carLoading(cars);
            } else {
                TrainSet trainSet = TrainSet.trainSets.get(choice);
                while (choice < 0 || choice > trainSet.getCars().size() - 1) {
                    Helpers.println("\n________Created cars for this train set_________");
                    int nCar = 0;
                    for (int i = 0; i < trainSet.getCars().size(); i++) {
                        Helpers.println("|" + i + "|ID" + trainSet.getCars().get(i).getID() + " " + trainSet.getCars().get(i).toString());
                    }

                    Helpers.print("\nMy choice(choose number not ID!): ");
                    choice = userInput.nextInt();
                }
                try {
                    Helpers.println("\nCurrent car load: " + (int) trainSet.getCars().get(choice).currentLoad + "kg | Max load: " + (int) trainSet.getCars().get(choice).maxLoad + "kg");
                } catch (IndexOutOfBoundsException e) {
                    Helpers.print("\nPlease choose number not ID!");
                    Helpers.dotAnimation();
                    carLoading(Presentation.cars);
                }
                Helpers.println("Current locomotive load: " + (int) trainSet.getLocomotive().getCurrentLoad() + "kg | Max load: " + (int) trainSet.getLocomotive().getMaxLoad() + "kg");
                Helpers.print("\nEnter how much kilograms/passengers to load: ");
                double toLoad = userInput.nextDouble();

                if (toLoad + trainSet.getCars().get(choice).currentLoad <= trainSet.getCars().get(choice).maxLoad) {
                    if (toLoad + trainSet.getCars().get(choice).currentLoad + trainSet.getCars().get(choice).grossWeight <= trainSet.getLocomotive().getMaxLoad()) {
                        trainSet.getCars().get(choice).updateCurrentLoad(toLoad);
                        trainSet.getLocomotive().updateCurrentLoad(toLoad);
                    } else {
                        Helpers.println("Locomotive max load exceeded! Please try again");
                        Helpers.dotAnimation();
                        carLoading(Presentation.cars);
                    }
                } else {
                    Helpers.println("Car max load exceeded! Please try again");
                    Helpers.dotAnimation();
                    carLoading(Presentation.cars);
                }
                Helpers.println("\nCar successfully loaded!");
                Helpers.println("New car load: " + (int) trainSet.getCars().get(choice).currentLoad + "kg");
                Helpers.println("New locomotive load: " + (int) trainSet.getLocomotive().getCurrentLoad() + "kg");

                Helpers.print("\nReturning to main menu");
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    Helpers.print(".");
                }
                Helpers.println();
                Presentation.mainMenu();
            }
        } catch (InputMismatchException e) {
            System.out.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            carLoading(Presentation.cars);
        }
    }

    public static void showCars() {
        int nCar = 0;
        for (RailroadCar car : Presentation.cars) {
            System.out.print("|" + nCar + "|" + car + "\n");
            nCar++;
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

    public static void removeCar() {
        Scanner userInput = new Scanner(System.in);

        Helpers.println("Choose train set to remove car from: ");
        Helpers.println("\n________Created train sets_________");
        int nSet = 0;
        for (TrainSet trainset : TrainSet.trainSets) {
            System.out.print(trainset);
            nSet++;
        }

        Helpers.print("\nMy choice: ");
        try {
            int choice = userInput.nextInt();

            if (choice > TrainSet.trainSets.size() || choice <= 0) {
                Helpers.print("\nThere is no such train set!\n");
                removeCar();
            } else {
                TrainSet trainSet = TrainSet.trainSets.get(choice);
                while (choice < 0 || choice > trainSet.getCars().size()) {
                    Helpers.println("\n________Created cars for this train set_________");
                    int nCar = 0;
                    for (int i = 0; i < trainSet.getCars().size(); i++) {
                        Helpers.println("|" + i + "|" + trainSet.getCars().get(i).getID() + " " + trainSet.getCars().get(i).toString());
                    }
                    Helpers.print("\nMy choice(number not ID!): ");
                    choice = userInput.nextInt();
                    try {
                        Presentation.cars.remove(choice);
                        trainSet.getCars().remove(choice);
                    } catch (IndexOutOfBoundsException e) {
                        Helpers.print("\nPlease choose number not ID!");
                        Helpers.dotAnimation();
                        removeCar();
                    }
                    Helpers.println("Car successfully removed!");
                    Helpers.print("Returning to main menu");
                    Helpers.dotAnimation();
                    Presentation.mainMenu();
                }

            }
        } catch (InputMismatchException e) {
            System.out.print("\nERROR! Wrong input. Please try again");
            Helpers.dotAnimation();
            removeCar();
        }
    }

    public RailroadCar(double netWeight, double grossWeight, int requiresPower, double maxLoad) {
        this.netWeight = netWeight;
        this.grossWeight = grossWeight;
        this.requiresPower = requiresPower;
        this.maxLoad = maxLoad;
        ID = counter;
        counter++;
    }

    public String txt() {
        return "CarID" + ID + ": " + getType() + ": total weight " + (int) grossWeight + "kg, cargo loaded " + (grossWeight - netWeight) + "kg|";
    }

    public String getType() {
        return "";
    }
}
