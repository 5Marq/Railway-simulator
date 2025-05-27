public class ExplosiveCar extends HeavyFreightCar {

    private int explosiveCategory;
    private boolean isSuperCautionRequired;

    public ExplosiveCar(double netWeight, double grossWeight, double maxMaterialVolume, int explosiveCategory, boolean isSuperCautionRequired,
                        double maxLoad) {
        super(netWeight, grossWeight, 0, maxMaterialVolume, maxLoad);
        this.explosiveCategory = explosiveCategory;
        this.isSuperCautionRequired = isSuperCautionRequired;
    }

    @Override
    public String getType() {
        return "ExplosivesCar";
    }

    @Override
    public String toString() {
        return "ExplosiveCar{" +
                "explosiveCategory:" + explosiveCategory +
                ", isSuperCautionRequired:" + isSuperCautionRequired +
                ", requiresPower:" + requiresPower +
                "}";
    }
}
