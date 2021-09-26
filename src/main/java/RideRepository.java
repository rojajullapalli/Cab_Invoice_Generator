import java.util.*;

public class RideRepository {
    Map<String, ArrayList<Ride>> userRides = new HashMap<>();

    public void addRide(String userId, Ride[] ride) {
        ArrayList<Ride> rideArrayList = this.userRides.get(userId);
        if (rideArrayList == null)
            this.userRides.put(userId, new ArrayList<Ride>(List.of(ride)));
    }

    public Ride[] getRides(String userId) {
        return this.userRides.get(userId).toArray(new Ride[0]);
    }
}
