public class RestaurantCar extends RailroadCar {

    private boolean isAirConditioned;
    private int seatCount;
    private boolean hasWaiter;
    private boolean isSmokingAllowed;
    private boolean hasAlcoholicBeverages;
    private boolean hasVeganFood;

    public RestaurantCar(double netWeight, double grossWeight, boolean isAirConditioned, int seatCount, boolean hasWaiter,
                         boolean isSmokingAllowed, boolean hasAlcoholicBeverages, boolean hasVeganFood) {
        super(netWeight, grossWeight, 1, seatCount);
        this.isAirConditioned = isAirConditioned;
        this.seatCount = seatCount;
        this.hasWaiter = hasWaiter;
        this.isSmokingAllowed = isSmokingAllowed;
        this.hasAlcoholicBeverages = hasAlcoholicBeverages;
        this.hasVeganFood = hasVeganFood;
    }

    @Override
    public String getType() {
        return "RestaurantCar";
    }

    @Override
    public String toString() {
        return "RestaurantCar{" +
                "isAirConditioned:" + isAirConditioned +
                ", seatCount:" + seatCount +
                ", hasWaiter:" + hasWaiter +
                ", isSmokingAllowed:" + isSmokingAllowed +
                ", hasAlcoholicBeverages:" + hasAlcoholicBeverages +
                ", hasVeganFood:" + hasVeganFood +
                ", requiresPower:" + requiresPower +
                "}";
    }
}
