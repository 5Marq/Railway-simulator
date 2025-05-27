public class PostOfficeCar extends RailroadCar {

    private int parcelCapacity;
    private int parcelFee;

    public PostOfficeCar(double netWeight, double grossWeight, int parcelCapacity, int parcelFee){
        super(netWeight, grossWeight,1);
        this.parcelCapacity = parcelCapacity;
        this.parcelFee = parcelFee;
    }

    @Override
    public String toString() {
        return "PostOfficeCar{" +
                "parcelCapacity=" + parcelCapacity +
                ", parcelFee=" + parcelFee +
                ", requiresPower=" + requiresPower +
                "} " + super.toString();
    }
}
