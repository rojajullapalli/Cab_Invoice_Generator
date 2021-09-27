import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvoiceServiceTest {

    @Test
    void givenDistanceAndTime_ShoudReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = RideType.calculateFare(RideType.NORMAL, distance, time);
        Assertions.assertEquals(25, fare, 0.0);
    }

    @Test
    void givenLessDistanceAndTime_ShouldReturnMinFare() {
        double distance = 0.1;
        int time = 1;
        double fare = RideType.calculateFare(RideType.NORMAL, distance, time);
        Assertions.assertEquals(5, fare, 0.0);
    }

    @Test
    void givenMultipleRides_ShouldReturnTotalFareAndAverageFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        Ride[] rides = {new Ride(RideType.NORMAL, 2.0, 5), new Ride(RideType.NORMAL, 0.1, 1)};
        InvoiceSummary summary = invoiceGenerator.calculateAmount(rides);
        InvoiceSummary expectedInvoice = new InvoiceSummary(2, 30.0);
        Assertions.assertEquals(expectedInvoice, summary);
    }

    @Test
    void givenUserIdAndMultipleRides_ShouldReturnInvoiceSummary() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        String userId = "roja";
        Ride[] ride = {new Ride(RideType.NORMAL, 2.0, 5), new Ride(RideType.NORMAL, 0.1, 1)};
        invoiceGenerator.addRides(userId, ride);
        InvoiceSummary invoiceSummary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assertions.assertEquals(expectedInvoiceSummary, invoiceSummary);

    }

    @Test
    public void givenPremiumUserIdAndRides_ShouldReturnInvoiceSummary() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        String userId = "Roja";
        Ride[] rides = {new Ride(RideType.PREMIUM, 2.0, 5), new Ride(RideType.PREMIUM, 0.1, 1),
                new Ride(RideType.PREMIUM, 4.0, 25), new Ride(RideType.PREMIUM, 3.0, 20)};
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4, 255.0);
        Assertions.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenPremiumUserId_GenerateTotalFare_ShouldReturnInvoiceSummery() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        String userId = "Roja";
        Ride[] rides = {new Ride(RideType.PREMIUM, 35.0, 45),
                new Ride(RideType.PREMIUM, 10.55, 30),
                new Ride(RideType.NORMAL, 20, 30)};
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary invoiceSummery = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedSummery = new InvoiceSummary(3, 1063.25);
        Assertions.assertEquals(expectedSummery, invoiceSummery);
    }

}
