import java.util.UUID;

public class RailroadCar {
    private UUID ID;
    private double netWeight;
    private double grossWeight;
    int requiresPower;

    public RailroadCar(double netWeight, double grossWeight, int requiresPower){
        this.netWeight = netWeight;
        this.grossWeight = grossWeight;
        this.requiresPower = requiresPower;
        ID = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "RailroadCar{" +
                "ID=" + ID +
                ", netWeight=" + netWeight +
                ", grossWeight=" + grossWeight +
                ", requiresPower=" + requiresPower +
                '}';
    }
}
