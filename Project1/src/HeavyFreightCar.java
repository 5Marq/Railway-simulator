abstract class HeavyFreightCar extends RailroadCar {

    private double maxMaterialVolume;
    private final int axisCount = 4;

    public HeavyFreightCar(double netWeight, double grossWeight, int requiresPower, double maxMaterialVolume, double maxLoad) {
        super(netWeight, grossWeight, requiresPower, maxLoad);
        this.maxMaterialVolume = maxMaterialVolume;
    }
}
