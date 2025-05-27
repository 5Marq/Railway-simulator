public class BaggageCar extends RailroadCar{

    private int baggageCapacity;
    private boolean hasSecurity;

    public BaggageCar(double netWeight, double grossWeight, int baggageCapacity, boolean hasSecurity){
        super(netWeight, grossWeight,0);
        this.baggageCapacity = baggageCapacity;
        this.hasSecurity = hasSecurity;
    }

    @Override
    public String toString() {
        return "BaggageCar{" +
                "baggageCapacity=" + baggageCapacity +
                ", hasSecurity=" + hasSecurity +
                ", requiresPower=" + requiresPower +
                "} " + super.toString();
    }
}
