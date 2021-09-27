import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InvoiceServiceTest {
    InvoiceGenerator invoiceGenerator;
    InvoiceSummary expectedSummery;
    Ride[] rides;
    String userId;

    @BeforeEach
    void setUp() {
        invoiceGenerator = new InvoiceGenerator();
        userId = "Roja";
        rides = new Ride[]{new Ride(RideType.PREMIUM, 2.0, 5), new Ride(RideType.PREMIUM, 0.1, 1),
                new Ride(RideType.PREMIUM, 4.0, 25), new Ride(RideType.PREMIUM, 3.0, 20)};
        expectedSummery = new InvoiceSummary(4, 255.0);
    }

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
        InvoiceSummary summary = invoiceGenerator.calculateAmount(rides);
        Assertions.assertEquals(expectedSummery, summary);
    }

    @Test
    public void givenUserIdAndMultipleRides_GenerateTotalFare_ShouldReturnInvoiceSummeryOfGivenUserId() {
        invoiceGenerator.addRides(userId, rides);
        String user = "prav";
        Ride[] ride1 = {new Ride(RideType.NORMAL, 2.0, 5), new Ride(RideType.NORMAL, 0.1, 1)};
        invoiceGenerator.addRides(user, ride1);
        InvoiceSummary expectedInvoice = new InvoiceSummary(2, 30.0);
        InvoiceSummary invoiceSummery = invoiceGenerator.getInvoiceSummary(user);
        Assertions.assertEquals(expectedInvoice, invoiceSummery);
    }

    @Test
    public void givenUserIdAndMultipleRides_WantToModifyExistingUserId_Should_Return_InvoiceSummary() {
        invoiceGenerator.addRides(userId, rides);
        Ride[] ride1 = {new Ride(RideType.NORMAL, 2.0, 5), new Ride(RideType.NORMAL, 0.1, 1)};
        invoiceGenerator.addRides(userId, ride1);
        InvoiceSummary expectedInvoicesum = new InvoiceSummary(6, 285.0);
        InvoiceSummary invoiceSummery = invoiceGenerator.getInvoiceSummary(userId);
        Assertions.assertEquals(expectedInvoicesum, invoiceSummery);
    }


    @Test
    public void givenPremiumUserIdAndRides_ShouldReturnInvoiceSummary() {
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        Assertions.assertEquals(expectedSummery, summary);
    }

}
