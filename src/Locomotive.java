import java.util.UUID; //class for generating random unique id's

public class Locomotive {

    private UUID ID;
    private String name;
    private String homeStation;
    private String destinationStation;
    private double speed;
    private int maxRailroadCars;
    private double maxLoad;
    private int maxRailroadCarsGrid; // ???

    public Locomotive(String name, String homeStation, String destinationStation, double speed){
        this.name = name;
        this.homeStation = homeStation;
        this.destinationStation = destinationStation;
        this.speed = speed;
        ID = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Locomotive{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", homeStation='" + homeStation + '\'' +
                ", destinationStation='" + destinationStation + '\'' +
                ", speed=" + speed +
                ", maxRailroadCars=" + maxRailroadCars +
                ", maxLoad=" + maxLoad +
                ", maxRailroadCarsGrid=" + maxRailroadCarsGrid +
                "}\n";
    }
}
