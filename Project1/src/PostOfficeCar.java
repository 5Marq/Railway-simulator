public class PostOfficeCar extends RailroadCar {

    private int parcelCapacity;
    private int parcelFee;

    public PostOfficeCar(double netWeight, double grossWeight, int parcelCapacity, int parcelFee, double maxLoad) {
        super(netWeight, grossWeight, 1, maxLoad);
        this.parcelCapacity = parcelCapacity;
        this.parcelFee = parcelFee;
    }

    @Override
    public String getType() {
        return "PostOfficeCar";
    }

    @Override
    public String toString() {
        return "PostOfficeCar{" +
                "parcelCapacity=" + parcelCapacity +
                ", parcelFee=" + parcelFee +
                ", requiresPower=" + requiresPower +
                "}";
    }
}
