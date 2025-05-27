public class RefrigeratedCar extends BasicFreightCar{

    private double minTemperature;
    private double maxTemperature;

    public RefrigeratedCar(double netWeight, double grossWeight, double maxMaterialVolume, double minTemperature, double maxTemperature) {
        super(netWeight, grossWeight, 1, maxMaterialVolume);
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    @Override
    public String toString() {
        return "RefrigeratedCar{" +
                "minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", requiresPower=" + requiresPower +
                "} " + super.toString();
    }
}
