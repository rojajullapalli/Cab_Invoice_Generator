public enum RideType {
    NORMAL(10, 1, 5),
    PREMIUM(15, 2, 20);

    public final double minimumCostPerKM;
    public final double costPerTime;
    public final double minFare;

    RideType(double minimumCostPerKM, double costPerTime, double minFare) {
        this.minimumCostPerKM = minimumCostPerKM;
        this.costPerTime = costPerTime;
        this.minFare = minFare;
    }
}
