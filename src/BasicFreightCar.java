public class BasicFreightCar extends RailroadCar{

    private double maxMaterialVolume;
    private final int axisCount = 2;

    public BasicFreightCar(double netWeight, double grossWeight, int requiresPower, double maxMaterialVolume) {
        super(netWeight, grossWeight, requiresPower);
        this.maxMaterialVolume = maxMaterialVolume;
    }

    @Override
    public String toString() {
        return "BasicFreightCar{" +
                "maxMaterialVolume=" + maxMaterialVolume +
                ", axisCount=" + axisCount +
                ", requiresPower=" + requiresPower +
                "} " + super.toString();
    }
}
