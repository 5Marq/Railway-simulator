public class ExplosiveCar extends HeavyFreightCar{

    private int explosiveCategory;
    private boolean isSuperCautionRequired;

    public ExplosiveCar(double netWeight, double grossWeight, double maxMaterialVolume, int explosiveCategory, boolean isSuperCautionRequired) {
        super(netWeight, grossWeight, 0, maxMaterialVolume);
        this.explosiveCategory = explosiveCategory;
        this.isSuperCautionRequired = isSuperCautionRequired;
    }

    @Override
    public String toString() {
        return "ExplosiveCar{" +
                "explosiveCategory=" + explosiveCategory +
                ", isSuperCautionRequired=" + isSuperCautionRequired +
                ", requiresPower=" + requiresPower +
                "} " + super.toString();
    }
}
