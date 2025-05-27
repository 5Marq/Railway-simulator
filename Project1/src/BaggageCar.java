public class BaggageCar extends RailroadCar {

    private int baggageCapacity;
    private boolean hasSecurity;

    public BaggageCar(double netWeight, double grossWeight, int baggageCapacity, boolean hasSecurity, double maxLoad) {
        super(netWeight, grossWeight, 0, maxLoad);
        this.baggageCapacity = baggageCapacity;
        this.hasSecurity = hasSecurity;
    }

    @Override
    public String getType() {
        return "BaggageCar";
    } //override to get type

    @Override
    public String toString() {
        return "BaggageCar{" +
                "baggageCapacity:" + baggageCapacity +
                ", hasSecurity:" + hasSecurity +
                ", requiresPower:" + requiresPower +
                "}";
    }
}
