import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Locomotive {
    private int ID;
    private static int counter = 0;
    private String name;
    private final String homeStation = "Warsaw";
    private String sourceStation;
    private String destinationStation;
    private double speed;
    private int maxRailroadCars;
    private double maxLoad;
    private double currentLoad;
    private int maxRailroadCarsGrid;
    private int currentRailroadCarsGrid;
    private int stopIndex;
    private double remainingDistance;

    private ArrayList<String> stops; //for train traverse

    public String getCurrentStop() {
        return stops.get(stopIndex);
    }

    public double getRoutePercent() {
        //System.out.println("**index: "+stopIndex+"size: "+stops.size());
        //if (stopIndex == 0) return 0;

        double distance = 0;
        double totalDistance = 0;

        for (int i = 1; i < stops.size() - 1; i++) {
            String stationA = stops.get(i - 1);
            String stationB = stops.get(i);
            //System.out.println("****"+stationA);
            //System.out.println("*****"+stationB);
            for (Route r : Route.routes) {
                if (r.getSource().equals(stationA) && r.getDestination().equals(stationB)) {
                    if (i <= stopIndex + 1) {
                        distance += r.getDistance();
                    }
                    totalDistance += r.getDistance(); //good
                }
            }
        }
        return (distance - remainingDistance) * 100 / totalDistance;
    }


    public String getNextStop() {
        if (stopIndex == stops.size() - 1) {
            return stops.get(stopIndex - 1); //next when returning backwards
        } else {
            return stops.get(stopIndex + 1); //next when going forward
        }
    }

    public void setStops(String s) {
        stops = new ArrayList<>();
        String[] strings = s.split(","); //split route by semicolons
        for (String i : strings) {
            stops.add(i);
        }
    }

    public ArrayList<String> getStops() {
        return stops;
    }

    public boolean checkLoadAndUpdate(double carWeight) {
        if (currentLoad + carWeight > maxLoad) return false;
        else {
            currentLoad += carWeight;
            return true;
        }
    }

    public void updateCurrentLoad(double carWeight) {
        currentLoad += carWeight;
    }

    public boolean hasMaxRailroadCarsGrid() {
        return currentRailroadCarsGrid == maxRailroadCarsGrid;
    }

    public boolean checkMaxRailroadCarsGridAndUpdate(RailroadCar car) {
        if (car.requiresPower == 1) {
            if (currentRailroadCarsGrid == maxRailroadCarsGrid) {
                return false;
            } else {
                currentRailroadCarsGrid++;
                return true;
            }
        }
        return true;
    }

    public int getID() {
        return ID;
    }

    public double getCurrentLoad() {
        return currentLoad;
    }

    public double getMaxLoad() {
        return maxLoad;
    }

    public static ArrayList<Locomotive> generateLocomotive(ArrayList<String> stations) {
        ArrayList<Locomotive> locomotives = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            int indexA = ThreadLocalRandom.current().nextInt(0, stations.size());
            int indexB = ThreadLocalRandom.current().nextInt(0, stations.size());

            while (indexA == indexB) {
                indexB = ThreadLocalRandom.current().nextInt(0, stations.size());
            }
            Locomotive obj = new Locomotive(Helpers.generateName(), stations.get(indexA), stations.get(indexB), ThreadLocalRandom.current().nextDouble(1, 150),
                    ThreadLocalRandom.current().nextDouble(700000, 1500000), ThreadLocalRandom.current().nextInt(10, 20), ThreadLocalRandom.current().nextInt(10, 20));
            locomotives.add(obj);                            //from 1000 to 1500t of max load
        }
        return locomotives;
    }

    public static Locomotive generateOneLocomotive(ArrayList<String> stations) {
        ArrayList<Locomotive> locomotives = new ArrayList<>();
        int indexA = ThreadLocalRandom.current().nextInt(0, stations.size());
        int indexB = ThreadLocalRandom.current().nextInt(0, stations.size());

        while (indexA == indexB) {
            indexB = ThreadLocalRandom.current().nextInt(0, stations.size());
        }
        Locomotive obj = new Locomotive(Helpers.generateName(), stations.get(indexA), stations.get(indexB), ThreadLocalRandom.current().nextDouble(1, 150),
                ThreadLocalRandom.current().nextDouble(700000, 1500000), ThreadLocalRandom.current().nextInt(10, 20), ThreadLocalRandom.current().nextInt(10, 20));
        //from 700 to 1500t of max load
        Presentation.locomotives.add(obj);
        return obj;
    }

    public static void locoCreator(int choice) {

        HashMap<Integer, Runnable> menuChoice = new HashMap<>();

        menuChoice.put(1, () -> TrainSet.trainSetCreator(Presentation.locomotives, Presentation.cars));
        menuChoice.put(2, () -> RailroadCar.carLoading(Presentation.cars));
        menuChoice.put(3, Locomotive::showLocomotives);
        menuChoice.put(4, RailroadCar::showCars);
        menuChoice.put(5, TrainSet::showTrainSet);
        menuChoice.put(6, TrainSet::showReport);
        menuChoice.put(7, RailroadCar::removeCar);
        menuChoice.put(8, TrainSet::removeTrainSet);
        menuChoice.put(9, Helpers::exit);

        if (menuChoice.containsKey(choice)) {
            menuChoice.get(choice).run();
        } else {
            System.out.print("\nThere is no option " + choice + "! \nReturning to the menu");
            Helpers.dotAnimation();
            Presentation.mainMenu();
        }
    }

    public static void showLocomotives() {
        Helpers.println("\n________Created locomotives_________");
        int nLoc = 0;
        for (Locomotive locomotive : Presentation.locomotives) {
            System.out.print("\n|" + nLoc + "|" + locomotive);
            nLoc++;
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

    public void run() throws RailRoadHazard { //moving along stations

        if (stopIndex < stops.size() - 1) {
            remainingDistance -= speed* 0.0002777778; //converting from km/h to km/s so the train goes in "real time"
            double distanceTravelled = speed* 0.0002777778;

            if (Math.random() > 0.5) speed *= 1.03; //will change every second because thread waits 1 second
            else speed *= 0.97;

            if (speed > 200) {
                speed *= 0.90;
                throw new RailRoadHazard("\nRailroad Hazard! Train going too fast! Decreasing speed.");
            }

            //System.out.println("Speed: "+speed+" Stop: " + stops.get(stopIndex)+" Next stop: "+stops.get(stopIndex+1)+" Remaining distance: "+remainingDistance+" Route%: "+getRoutePercent());
            if (remainingDistance <= 0) { //train going first time
                if (stopIndex == stops.size() - 1) {
                    if (TrainSet.collision(stops.get(stopIndex), stops.get(stopIndex - 1), this.ID)) {
                        //System.out.println("Waiting in the queue for the other train");
                        return;
                    }
                } else {
                    if (TrainSet.collision(stops.get(stopIndex), stops.get(stopIndex + 1), this.ID)) {
                        //System.out.println("Waiting in the queue for the other train");
                        return;
                    }
                }
                stopIndex++;//if distance left is 0 then going to next station
                //System.out.println("****"+stopIndex);
                if (stopIndex == stops.size() - 1) {
                    remainingDistance += Route.getStopsDistance(stops.get(stopIndex), stops.get(stopIndex - 1)); //get next distance, -1 because returning
                } else {
                    remainingDistance += Route.getStopsDistance(stops.get(stopIndex), stops.get(stopIndex + 1)); //+= to eliminate negative values
                }
                //Helpers.println("Waiting on station...");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    Helpers.println("Thread error");
                }
            }
        } else {
            stopIndex = 0;
            try {
                Thread.sleep(30000); //wait 30 seconds and then go back
            } catch (Exception e) {
                Helpers.println("Thread error");
            }
            Collections.reverse(stops); //reverse all stops for locomotive going back to first station
        }
    }

    public double getStationsPercent() {
        Route r = null;
        for (Route ri : Route.routes){
            if (ri.getSource().equals(getCurrentStop()) && ri.getDestination().equals(getNextStop())){
                r = ri;
            }
        }
        if (r != null) return (r.getDistance()-remainingDistance)*100/r.getDistance();
        else return 0;
    }

    public Locomotive(String name, String sourceStation, String destinationStation, double speed, double maxLoad, int maxRailroadCars, int maxRailroadCarsGrid) {
        this.name = name;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.speed = speed;
        this.maxLoad = maxLoad;
        this.maxRailroadCars = maxRailroadCars;
        this.maxRailroadCarsGrid = maxRailroadCarsGrid;
        ID = counter;
        counter++;
        String r = Route.findWay(Route.connections, sourceStation, destinationStation, ""); //setting route for every train
        setStops(r);
        stopIndex = 0;
        remainingDistance = Route.getStopsDistance(stops.get(0), stops.get(1)); //remaining distance between 2 stations
    }

    @Override
    public String toString() {
        return "ID" + ID +
                ", name:'" + name + '\'' +
                ", sourceStation:'" + sourceStation + '\'' +
                ", destinationStation:'" + destinationStation + '\'' +
                ", homeStation:'" + homeStation + '\'' +
                ", maxRailroadCars:" + maxRailroadCars +
                ", maxLoad:" + (int) maxLoad + "kg" +
                ", maxRailroadCarsGrid:" + maxRailroadCarsGrid +
                "}";
    }

    public String txt() {
        return "Locomotive ID" + ID + ": name " + name + " Home station: " + homeStation + ", current load " + (int) currentLoad + "kg| ";
    }
}
