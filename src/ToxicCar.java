public class ToxicCar extends HeavyFreightCar{

    private boolean isHarmfulForPeople;
    private final boolean requiresDoubleCheckBeforeJourney = true;

    public ToxicCar(double netWeight, double grossWeight, double maxMaterialVolume, boolean isHarmfulForPeople) {
        super(netWeight, grossWeight, 0, maxMaterialVolume);
        this.isHarmfulForPeople = isHarmfulForPeople;
    }

    @Override
    public String toString() {
        return "ToxicCar{" +
                "isHarmfulForPeople=" + isHarmfulForPeople +
                ", requiresDoubleCheckBeforeJourney=" + requiresDoubleCheckBeforeJourney +
                ", requiresPower=" + requiresPower +
                "} " + super.toString();
    }
}
