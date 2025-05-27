public class HeavyFreightCar extends RailroadCar {

    private double maxMaterialVolume;
    private final int axisCount = 4;

    public HeavyFreightCar(double netWeight, double grossWeight, int requiresPower, double maxMaterialVolume) {
        super(netWeight, grossWeight, requiresPower);
        this.maxMaterialVolume = maxMaterialVolume;
    }

    @Override
    public String toString() {
        return "HeavyFreightCar{" +
                "maxMaterialVolume=" + maxMaterialVolume +
                ", axisCount=" + axisCount +
                ", requiresPower=" + requiresPower +
                "} " + super.toString();
    }
}
