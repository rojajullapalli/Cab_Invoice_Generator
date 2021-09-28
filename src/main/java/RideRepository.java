import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {
    public Map<String, ArrayList<Ride>> userRides = new HashMap<>();

    public void addRide(String userId, Ride[] ride) {
        ArrayList<Ride> rideArrayList = this.userRides.get(userId);
        if (rideArrayList == null)
            userRides.put(userId, new ArrayList<Ride>(Arrays.asList(ride)));
        else {
            rideArrayList.addAll(Arrays.asList(ride));
            userRides.put(userId, rideArrayList);
        }
    }

    public Ride[] getRides(String userId) throws ExceptionType {
        try {
            return this.userRides.get(userId).toArray(new Ride[0]);
        } catch (Exception e) {
            throw new ExceptionType(ExceptionType.ExceptionName.NAME_NOT_FOUND, "Enter The Valid User Name");
        }
    }
}
