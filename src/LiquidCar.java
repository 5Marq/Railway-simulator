public class LiquidCar extends BasicFreightCar{

    private boolean isToxic;
    private int toxicLevel;

    public LiquidCar(double netWeight, double grossWeight, double maxMaterialVolume, boolean isToxic, int toxicLevel) {
        super(netWeight, grossWeight, 0, maxMaterialVolume);
        this.isToxic = isToxic;
        this.toxicLevel = toxicLevel;
    }

    @Override
    public String toString() {
        return "LiquidCar{" +
                "isToxic=" + isToxic +
                ", toxicLevel=" + toxicLevel +
                ", requiresPower=" + requiresPower +
                "} " + super.toString();
    }
}
