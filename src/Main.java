import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    //todo eliminate redundancy and optimize by creating more functions
    public static void print(String message) {
        System.out.print(message);
    }
    public static void println(String message) {
        System.out.println(message);
    }
    public static void println() {
        System.out.println();
    }
    public static void dotAnimation(){
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            print(".");
        }
    }
    static String yn = "y";
    static double weight;
    static String tempString;
    static ArrayList<Locomotive> locomotives = new ArrayList<>();
    static ArrayList<RailroadCar> cars = new ArrayList<>();
    public static void trainsetCreator(){

    }
    public static void carCreator() {

        Scanner userInput = new Scanner(System.in);
        print("\nMy choice: ");
        int choice = userInput.nextInt();

        while (choice<1 || choice>10) {
            println("\n_________Car Types__________");
            println("| Option | Description     | ");
            println("----------------------------");
            println("|   1   | Passenger        |");
            println("|   2   | Post Office      |");
            println("|   3   | Baggage          |");
            println("|   4   | Restaurant       |");
            println("|   5   | Refrigerated     |");
            println("|   6   | Liquid materials |");
            println("|   7   | Gas materials    |");
            println("|   8   | Explosives       |");
            println("|   9   | Toxic materials  |");
            println("|   10  | Liquid and toxic |");

            print("\nMy choice: ");
            choice = userInput.nextInt();
            }

            switch (choice) {
                    case 1: {
                        if (yn.equals("n")) break;
                        print("\nProceeding to create passenger car");
                        dotAnimation();

                        print("\nEnter weight: ");
                        weight = userInput.nextDouble(); //todo what if weight is string?

                        print("\nEnter wagon class (any number but in reality there is 1 and 2 class): ");
                        int parcelCapacity = userInput.nextInt();

                        print("\nIs air conditioned?(y/n): "); //todo what if airconditioned is integer?
                        tempString = userInput.next();
                        boolean isAirConditioned = tempString.equals("y");

                        print("\nEnter seat count: ");
                        int passengerSeatCount = userInput.nextInt();

                        RailroadCar passenger = new PassengerCar(weight, weight, parcelCapacity, isAirConditioned, passengerSeatCount); //setting gross weight to passengerWeight because
                        //there are currently no passengers on board
                        cars.add(passenger);
                        println("Passenger car was successfully created with parameters: |Weight: " + weight + " |Class: " + parcelCapacity + " |AirConditioned?: " + isAirConditioned + "" +
                                " |Seat count: " + passengerSeatCount);

                        print("\nDo you want to create another train car?(y/n): ");
                        yn = userInput.next();
                        if (yn.equals("y")) carCreator();
                        else break;
                    } //todo format to look nicely
                    case 2: {
                        if (yn.equals("n")) break;
                        print("\nProceeding to create post office car");
                        dotAnimation();

                        print("\nEnter weight: ");
                        weight = userInput.nextDouble(); //todo what if weight is string?

                        print("\nEnter parcel capacity: : ");
                        int parcelCapacity = userInput.nextInt();

                        print("\nEnter parcel fee: ");
                        int parcelFee = userInput.nextInt();

                        RailroadCar postOffice = new PostOfficeCar(weight, weight, parcelCapacity, parcelFee); //setting gross weight to passengerWeight because
                        //there are currently no passengers on board
                        cars.add(postOffice);
                        println("Post office car was successfully created with parameters: |Weight: " + weight + " |Parcel capacity: " + parcelCapacity +
                                " |Parcel fee: " + parcelFee);

                        print("\nDo you want to create another train car?(y/n): ");
                        yn = userInput.next();
                        if (yn.equals("y")) carCreator();
                        else break;
                    }
                    case 3: {
                        if (yn.equals("n")) break;
                        print("\nProceeding to create baggage car");
                        dotAnimation();

                        print("\nEnter weight: ");
                        weight = userInput.nextDouble(); //todo what if weight is string?

                        print("\nEnter baggage capacity: : ");
                        int baggageCapacity = userInput.nextInt();

                        print("\nHas security?(y/n): ");
                        tempString = userInput.next();
                        boolean hasSecurity = tempString.equals("y");

                        RailroadCar baggage = new BaggageCar(weight, weight, baggageCapacity, hasSecurity); //setting gross weight to passengerWeight because
                        //there are currently no passengers on board
                        cars.add(baggage);
                        println("Baggage car was successfully created with parameters: |Weight: " + weight + " |Baggage capacity: " + baggageCapacity +
                                " |Has security?: " + hasSecurity);

                        print("\nDo you want to create another train car?(y/n): ");
                        yn = userInput.next();
                        if (yn.equals("y")) carCreator();
                        else break;
                    }
                    case 4: {
                        if (yn.equals("n")) break;
                        print("\nProceeding to create restaurant car");
                        dotAnimation();

                        print("\nEnter weight: ");
                        weight = userInput.nextDouble(); //todo what if weight is string?

                        print("\nIs air conditioned?(y/n): "); //todo what if airconditioned is integer?
                        tempString = userInput.next();
                        boolean isAirConditioned = tempString.equals("y");

                        print("\nEnter seat count: ");
                        int seatCount = userInput.nextInt();

                        print("\nIs there a waiter?(y/n): ");
                        tempString = userInput.next();
                        boolean hasWaiter = tempString.equals("y");

                        print("\nIs smoking allowed?(y/n): ");
                        tempString = userInput.next();
                        boolean isSmokingAllowed = tempString.equals("y");

                        print("\nAre there alcoholic beverages?(y/n): ");
                        tempString = userInput.next();
                        boolean hasAlcohol = tempString.equals("y");

                        print("\nIs there vegan food?(y/n): ");
                        tempString = userInput.next();
                        boolean hasVeganFood = tempString.equals("y");

                        RailroadCar restaurant = new RestaurantCar(weight, weight, isAirConditioned, seatCount,
                                hasWaiter, isSmokingAllowed, hasAlcohol, hasVeganFood);
                        cars.add(restaurant);
                        println("Restaurant car was successfully created with parameters: |Weight: " + weight + " |Is air conditioned?: " + isAirConditioned +
                                " |Seat count: " + seatCount + " |Has waiter?: " + hasWaiter + " |Is smoking allowed?: " + isSmokingAllowed + " |Has alcohol?: " + hasAlcohol +
                                " |Has vegan food?: " + hasVeganFood);

                        print("\nDo you want to create another train car?(y/n): ");
                        yn = userInput.next();
                        if (yn.equals("y")) carCreator();
                        else break;
                    }
                    case 5: {
                        if (yn.equals("n")) break;
                        print("\nProceeding to create refrigerated(basic freight) car");
                        dotAnimation();

                        print("\nEnter weight: ");
                        weight = userInput.nextDouble(); //todo what if weight is string?

                        print("\nEnter max material volume: ");
                        double maxMaterialVolume = userInput.nextDouble();

                        print("\nEnter min temperature: ");
                        double minTemperature = userInput.nextDouble();

                        print("\nEnter max temperature: ");
                        double maxTemperature = userInput.nextDouble();

                        RailroadCar refrigerated = new RefrigeratedCar(weight, weight, maxMaterialVolume, minTemperature, maxTemperature);
                        println("Refrigerated car was successfully created with parameters: |Weight: " + weight + " |Max material volume: " + maxMaterialVolume + " " +
                                " |Min temperature: " + minTemperature + "" + " |Max temperature: " + maxTemperature);
                        cars.add(refrigerated);
                        print("\nDo you want to create another train car?(y/n): ");
                        yn = userInput.next();
                        if (yn.equals("y")) carCreator();
                        else break;
                    }
                    case 6: {
                        if (yn.equals("n")) break;
                        print("\nProceeding to create liquid materials(basic freight) car");
                        dotAnimation();

                        print("\nEnter weight: ");
                        weight = userInput.nextDouble(); //todo what if weight is string?

                        print("\nEnter max material volume: ");
                        double maxMaterialVolume = userInput.nextDouble();

                        print("\nIs liquid toxic?(y/n): ");
                        tempString = userInput.next();
                        boolean isLiquidToxic = tempString.equals("y");

                        int toxicLevel;

                        if (isLiquidToxic) {
                            print("\nEnter toxic level: ");
                            toxicLevel = userInput.nextInt();
                        } else toxicLevel = 0;

                        RailroadCar liquid = new LiquidCar(weight, weight, maxMaterialVolume, isLiquidToxic, toxicLevel);
                        cars.add(liquid);
                        println("Liquid materials car was successfully created with parameters: |Weight: " + weight + " |Max material volume: " + maxMaterialVolume + " " +
                                " |Is liquid toxic?: " + isLiquidToxic + "" + " |Toxic level: " + toxicLevel);

                        print("\nDo you want to create another train car?(y/n): ");
                        yn = userInput.next();
                        if (yn.equals("y")) carCreator();
                        else break;
                    }
                    case 7: {
                        if (yn.equals("n")) break;
                        print("\nProceeding to create gas materials(basic freight) car");
                        dotAnimation();

                        print("\nEnter weight: ");
                        weight = userInput.nextDouble(); //todo what if weight is string?

                        print("\nEnter max material volume: ");
                        double maxMaterialVolume = userInput.nextDouble();

                        print("\nIs gas explosive?(y/n): "); //todo what if airconditioned is integer?
                        tempString = userInput.next();
                        boolean isGasExplosive = tempString.equals("y");

                        print("\nIs gas toxic?(y/n): "); //todo what if airconditioned is integer?
                        tempString = userInput.next();
                        boolean isGasToxic = tempString.equals("y");

                        RailroadCar gas = new GasCar(weight, weight, maxMaterialVolume, isGasExplosive, isGasToxic);
                        cars.add(gas);
                        println("Gas car was successfully created with parameters: |Weight: " + weight + " |Max material volume: " + maxMaterialVolume + " |Is gas explosive?: " +
                                isGasExplosive + "" + " |Is gas toxic?: " + isGasToxic);

                        print("\nDo you want to create another train car?(y/n): ");
                        yn = userInput.next();
                        if (yn.equals("y")) carCreator();
                        else break;
                    }
                    case 8: {
                        if (yn.equals("n")) break;
                        print("\nProceeding to create explosive materials(heavy freight) car");
                        dotAnimation();

                        print("\nEnter weight: ");
                        weight = userInput.nextDouble(); //todo what if weight is string?

                        print("\nEnter max material volume: ");
                        double maxMaterialVolume = userInput.nextDouble();

                        print("\nEnter explosive category: "); //todo what if airconditioned is integer?
                        int category = userInput.nextInt();

                        print("\nIs super caution required?(y/n): "); //todo what if airconditioned is integer?
                        tempString = userInput.next();
                        boolean isSuperCautionRequired = tempString.equals("y");

                        RailroadCar explosive = new ExplosiveCar(weight, weight, maxMaterialVolume, category, isSuperCautionRequired);
                        cars.add(explosive);
                        println("Explosives car was successfully created with parameters: |Weight: " + weight + " |Max material volume: " + maxMaterialVolume + " |Explosive category: " +
                                category + "" + " |Is super caution required?: " + isSuperCautionRequired);

                        print("\nDo you want to create another train car?(y/n): ");
                        yn = userInput.next();
                        if (yn.equals("y")) carCreator();
                        else break;
                    }
                    case 9: {
                        if (yn.equals("n")) break;
                        print("\nProceeding to create toxic materials(heavy freight) car");
                        dotAnimation();

                        print("\nEnter weight: ");
                        weight = userInput.nextDouble(); //todo what if weight is string?

                        print("\nEnter max material volume: ");
                        double maxMaterialVolume = userInput.nextDouble();

                        print("\nIs toxic for people?(y/n): "); //todo what if airconditioned is integer?
                        tempString = userInput.next();
                        boolean isToxicForPeople = tempString.equals("y");

                        RailroadCar toxic = new ToxicCar(weight, weight, maxMaterialVolume, isToxicForPeople);
                        cars.add(toxic);
                        println("Toxic materials car was successfully created with parameters: |Weight: " + weight + " |Max material volume: " + maxMaterialVolume + " " +
                                "|Is toxic for people?: " + isToxicForPeople);

                        print("\nDo you want to create another train car?(y/n): ");
                        yn = userInput.next();
                        if (yn.equals("y")) carCreator();
                        else break;
                    }
                    case 10:{
                        if (yn.equals("n")) break;
                        print("\nProceeding to create liquid and toxic materials(heavy freight) car");
                        dotAnimation();

                        print("\nEnter weight: ");
                        weight = userInput.nextDouble(); //todo what if weight is string?

                        print("\nEnter max material volume: ");
                        double maxMaterialVolume = userInput.nextDouble();

                        print("\nIs liquid toxic?(y/n): ");
                        tempString = userInput.next();
                        boolean isLiquidToxic = tempString.equals("y");

                        int toxicLevel;

                        if (isLiquidToxic) {
                            print("\nEnter toxic level: ");
                            toxicLevel = userInput.nextInt();
                        } else toxicLevel = 0;

                        RailroadCar liquid = new LiquidCar(weight, weight, maxMaterialVolume, isLiquidToxic, toxicLevel);
                        cars.add(liquid);
                        println("Liquid and toxic materials car was successfully created with parameters: |Weight: " + weight + " |Max material volume: " + maxMaterialVolume + " " +
                                " |Is liquid toxic?: " + isLiquidToxic + "" + " |Toxic level: " + toxicLevel);

                        print("\nDo you want to create another train car?(y/n): ");
                        yn = userInput.next();
                        if (yn.equals("y")) carCreator();
                        else break;
                    }
        }
    }
    public static void locoCreator(int choice) {
        Scanner userInput = new Scanner(System.in);

        switch (choice) {
            case 1: {

                print("\nEnter locomotive name: ");
                String name = userInput.next();
                print("\nEnter home station: ");
                String homeStation = userInput.next();
                print("\nEnter destination station: ");
                String destinationStation = userInput.next();
                print("\nEnter locomotive speed: ");
                double speed = userInput.nextDouble();
                Locomotive locomotive = new Locomotive(name, homeStation, destinationStation, speed);
                locomotives.add(locomotive);
                print("\nLocomotive '" + name + "' from '" + homeStation + "' to '" + destinationStation + "' created.");

                print("\nDo you want to continue with train creator?(y/n): ");
                yn = userInput.next();

                if (yn.equals("n")) {
                    print("\nCreation aborted. Restart the app.\n");
                    break;
                }

                print("Do you want to create another locomotive?(y/n): ");
                yn = userInput.next();
                if (yn.equals("y")) {
                    locoCreator(choice);
                }
            }
            case 2: {
                if (yn.equals("n")) break;
                println("\n_________Car Types__________");
                println("| Option | Description     | ");
                println("----------------------------");
                println("|   1   | Passenger        |");
                println("|   2   | Post Office      |");
                println("|   3   | Baggage          |");
                println("|   4   | Restaurant       |");
                println("|   5   | Refrigerated     |");
                println("|   6   | Liquid materials |");
                println("|   7   | Gas materials    |");
                println("|   8   | Explosives       |");
                println("|   9   | Toxic materials  |");
                println("|   10  | Liquid and toxic |");
                carCreator();
                break;
            }
        }
    }
    public static void trainTraverse (double distanceToTravel, double speed) throws RailRoadHazard {

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

            if (speed>200) {
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


            print("Distance to travel: " + distanceToTravel+" ");
            print("| Distance travelled: " + distanceTravelled+" ");
            print("| Distance left: " + distanceLeft+" ");
            print("| Current speed: " + speed+" ");
            print("| Seconds in travel: " + secondsInTravel+" ");
            println();

        }
        println();
        println("Train arrived at its destination");

    }
    public static void mainMenu(){
        println("________Railway Simulator_______");
        println("Developed by Patryk Gawin s28182");
        println("--------------------------------\n");

        println("_____________Menu______________");
        println("|Option | Description         | ");
        println("-------------------------------");
        println("|   1   | Creates new loco    |");
        println("|   2   | Creates new car     |");
        println("-------------------------------");

        Scanner userInput = new Scanner(System.in);
        print("\nMy choice: ");
        int choice = userInput.nextInt();
        locoCreator(choice);
        System.out.println("Locomotives: "+locomotives);
        System.out.println("Cars: "+cars);
    }

    public static void main(String[] args) throws RailRoadHazard {
        mainMenu();
        //trainTraverse(1,190);
    }
}