public class Ride {
    public final double distance;
    public final int time;
    public final RideType rideType;

    public Ride(RideType rideType, double distance, int time) {
        this.rideType = rideType;
        this.distance = distance;
        this.time = time;
    }
}
