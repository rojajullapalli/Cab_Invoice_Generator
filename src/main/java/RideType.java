public enum RideType {
    NORMAL(10, 1, 5),
    PREMIUM(15, 2, 20);

    private final double minimumCostPerKM;
    private final double costPerTime;
    private final double minFare;

    RideType(double minimumCostPerKM, double costPerTime, double minFare) {
        this.minimumCostPerKM = minimumCostPerKM;
        this.costPerTime = costPerTime;
        this.minFare = minFare;
    }

    public static double calculateFare(RideType rideType, double distance, int time) {
        double totalFare = distance * rideType.minimumCostPerKM + time * rideType.costPerTime;
        if (totalFare < rideType.minFare)
            return rideType.minFare;
        return totalFare;
    }
}
