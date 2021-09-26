public class InvoiceGenerator {
    private static double MINIMUM_COST_PER_KILOMETER;
    private static double COST_PER_TIME;
    private static double MINIMUM_FARE;
    RideRepository rideRepository;

    public InvoiceGenerator() {
        this.rideRepository = new RideRepository();
    }

    private void setValue(RideType rideType) {
        MINIMUM_COST_PER_KILOMETER = rideType.minimumCostPerKM;
        COST_PER_TIME = rideType.costPerTime;
        MINIMUM_FARE = rideType.minFare;
    }

    public double calculateFare(RideType rideType, double distance, int time) {
        setValue(rideType);
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        if (totalFare < MINIMUM_FARE)
            return MINIMUM_FARE;
        return totalFare;
    }

    public InvoiceSummary calculateAmount(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.rideType, ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] ride) {
        rideRepository.addRide(userId, ride);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateAmount(rideRepository.getRides(userId));
    }

}
