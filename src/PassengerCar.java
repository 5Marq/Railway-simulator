public class PassengerCar extends RailroadCar {

    int wagonClass;
    boolean isAirConditioned;
    int seatCount;

    public PassengerCar(double netWeight, double grossWeight, int wagonClass, boolean isAirConditioned, int seatCount){
        super(netWeight,grossWeight,1);
        this.wagonClass = wagonClass;
        this.isAirConditioned = isAirConditioned;
        this.seatCount = seatCount;
    }

    @Override
    public String toString() {
        return "PassengerCar{" +
                "wagonClass=" + wagonClass +
                ", isAirConditioned=" + isAirConditioned +
                ", seatCount=" + seatCount +
                ", requiresPower=" + requiresPower +
                "} " + super.toString();
    }
}
