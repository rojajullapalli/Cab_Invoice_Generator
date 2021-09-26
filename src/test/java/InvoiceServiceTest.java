import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;

public class InvoiceServiceTest {

    @Test
    void givenDistanceAndTime_ShoudReturnTotalFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(25, fare, 0.0);
    }

    @Test
    void givenLessDistanceAndTime_ShouldReturnMinFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(5, fare, 0.0);
    }

    @Test
    void givenMultipleRides_ShouldReturnTotalFareAndAverageFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        Ride[] rides = {new Ride(2.0, 5), new Ride(0.1, 1)};
        InvoiceSummary summary = invoiceGenerator.calculateAmount(rides);
        InvoiceSummary expectedInvoice = new InvoiceSummary(2, 30.0);
        Assertions.assertEquals(expectedInvoice, summary);
    }

    @Test
    void givenUserIdAndMultipleRides_ShouldReturnInvoiceSummary(){
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        String userId = "roja";
        Ride[] ride = { new Ride(2.0, 5), new Ride(0.1, 1) };
        invoiceGenerator.addRides(userId, ride);
        InvoiceSummary invoiceSummary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2 , 30);
        Assertions.assertEquals(expectedInvoiceSummary, invoiceSummary);

    }

}
