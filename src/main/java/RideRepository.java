import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideRepository {
    public Map<String, ArrayList<Ride>> userRides = new HashMap<>();

    public void addRide(String userId, Ride[] ride) {
        ArrayList<Ride> rideArrayList = this.userRides.get(userId);
        if (rideArrayList == null)
           userRides.put(userId, new ArrayList<Ride>(List.of(ride)));
        else {
            rideArrayList.addAll(List.of(ride));
            userRides.put(userId, rideArrayList);
        }
    }

    public Ride[] getRides(String userId) {
        return this.userRides.get(userId).toArray(new Ride[0]);
    }
}
