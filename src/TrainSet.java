import java.util.UUID;

public class TrainSet {

    private Locomotive locomotive;
    private RailroadCar car;
    private UUID ID;

    public TrainSet(Locomotive locomotive, RailroadCar car) {
        this.locomotive = locomotive;
        this.car = car;
        ID = UUID.randomUUID();
    }
}
