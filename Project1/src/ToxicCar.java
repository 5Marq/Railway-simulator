public class ToxicCar extends HeavyFreightCar {

    private boolean isHarmfulForPeople;
    private final boolean requiresDoubleCheckBeforeJourney = true;

    public ToxicCar(double netWeight, double grossWeight, double maxMaterialVolume, boolean isHarmfulForPeople, double maxLoad) {
        super(netWeight, grossWeight, 0, maxMaterialVolume, maxLoad);
        this.isHarmfulForPeople = isHarmfulForPeople;
    }

    @Override
    public String getType() {
        return "ToxicCar";
    }

    @Override
    public String toString() {
        return "ToxicCar{" +
                "isHarmfulForPeople=" + isHarmfulForPeople +
                ", requiresDoubleCheckBeforeJourney=" + requiresDoubleCheckBeforeJourney +
                ", requiresPower=" + requiresPower +
                "}";
    }
}
