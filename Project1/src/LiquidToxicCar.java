public class LiquidToxicCar extends HeavyFreightCar {

    private boolean isToxic;
    private int toxicLevel;

    public LiquidToxicCar(double netWeight, double grossWeight, double maxMaterialVolume, boolean isToxic, int toxicLevel, double maxLoad) {
        super(netWeight, grossWeight, 0, maxMaterialVolume, maxLoad);
        this.isToxic = isToxic;
        this.toxicLevel = toxicLevel;
    }

    @Override
    public String getType() {
        return "LiquidToxicCar";
    }

    @Override
    public String toString() {
        return "LiquidToxicCar{" +
                "isToxic:" + isToxic +
                ", toxicLevel:" + toxicLevel +
                ", requiresPower:" + requiresPower +
                "}";
    }
}
