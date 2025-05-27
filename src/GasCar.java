public class GasCar extends BasicFreightCar{

    private boolean isExplosive;
    private boolean isToxic;

    public GasCar(double netWeight, double grossWeight, double maxMaterialVolume, boolean isExplosive, boolean isToxic) {
        super(netWeight, grossWeight, 0, maxMaterialVolume);
        this.isExplosive = isExplosive;
        this.isToxic = isToxic;
    }

    @Override
    public String toString() {
        return "GasCar{" +
                "isExplosive=" + isExplosive +
                ", isToxic=" + isToxic +
                ", requiresPower=" + requiresPower +
                "} " + super.toString();
    }
}
