abstract class BasicFreightCar extends RailroadCar {

    private double maxMaterialVolume;
    final int axisCount = 2;

    public BasicFreightCar(double netWeight, double grossWeight, int requiresPower, double maxMaterialVolume, double maxLoad) {
        super(netWeight, grossWeight, requiresPower, maxLoad);
        this.maxMaterialVolume = maxMaterialVolume;
    }

    @Override
    public String toString() {
        return super.toString() +
                "axisCount:" + axisCount;
    }
}

