import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

    @Mock
    RideRepository rideRepository;

    @InjectMocks
    InvoiceGenerator invoiceGenerator;
    Ride[] rides;
    String userId;

    @BeforeEach
    public void setUp() {
        userId = "roja";
        rides = new Ride[]{new Ride(RideType.PREMIUM, 2.0, 5), new Ride(RideType.PREMIUM, 0.1, 1),
                new Ride(RideType.PREMIUM, 4.0, 25), new Ride(RideType.PREMIUM, 3.0, 20)};
    }

    @Test
    void givenMultipleRides_WhenCalculated_ShouldReturnInvoiceSummary() {
        InvoiceSummary actualInvoice = invoiceGenerator.calculateAmount(rides);
        InvoiceSummary exceptedInvoice = new InvoiceSummary(4, 255.0);
        Assertions.assertEquals(actualInvoice, exceptedInvoice);
    }

    @Test
    public void givenUserIdAndExsistingRides_GenerateTotalFare_ShouldReturnInvoiceSummeryOfExsistingUserId() throws ExceptionType {
        invoiceGenerator.addRides(userId, rides);
        when(rideRepository.getRides(userId)).thenReturn(rides);
        InvoiceSummary expectedInvoice = new InvoiceSummary(4, 255.0);
        InvoiceSummary invoiceSummery = invoiceGenerator.getInvoiceSummary(userId);
        Assertions.assertEquals(expectedInvoice, invoiceSummery);
    }

    @Test
    public void givenUserIdAndMultipleRides_GenerateTotalFare_ShouldReturnInvoiceSummeryOfExistingUserId() throws ExceptionType {
        invoiceGenerator.addRides(userId, rides);
        when(rideRepository.getRides(userId)).thenReturn(rides);
        String user = "prav";
        Ride[] ride1 = {new Ride(RideType.NORMAL, 2.0, 5), new Ride(RideType.NORMAL, 0.1, 1)};
        invoiceGenerator.addRides(user, ride1);
        InvoiceSummary expectedInvoice = new InvoiceSummary(4, 255.0);
        InvoiceSummary invoiceSummery = invoiceGenerator.getInvoiceSummary(userId);
        Assertions.assertEquals(expectedInvoice, invoiceSummery);
    }

    @Test
    public void givenUserIdAndMultipleRides_WantToModifyExistingUserId_Should_Return_InvoiceSummary() throws ExceptionType {
        invoiceGenerator.addRides(userId, rides);
        Ride[] rides = {new Ride(RideType.NORMAL, 2.0, 5), new Ride(RideType.NORMAL, 0.1, 1),
                new Ride(RideType.PREMIUM, 2.0, 5), new Ride(RideType.PREMIUM, 0.1, 1),
                new Ride(RideType.PREMIUM, 4.0, 25), new Ride(RideType.PREMIUM, 3.0, 20)};
        invoiceGenerator.addRides(userId, rides);
        when(rideRepository.getRides(userId)).thenReturn(rides);
        InvoiceSummary invoiceSummery = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoicesum = new InvoiceSummary(6, 285.0);
        Assertions.assertEquals(expectedInvoicesum, invoiceSummery);
    }

    @Test
    public void givenPremiumUserIdAndRides_ShouldReturnInvoiceSummary() throws ExceptionType {
        String user = "bhava";
        Ride[] ride = new Ride[]{new Ride(RideType.PREMIUM, 2.0, 5), new Ride(RideType.PREMIUM, 0.1, 1),
                new Ride(RideType.PREMIUM, 4.0, 25), new Ride(RideType.PREMIUM, 3.0, 20)};
        invoiceGenerator.addRides(user, ride);
        when(rideRepository.getRides(user)).thenReturn(ride);
        InvoiceSummary expectedSummery = new InvoiceSummary(4, 255.0);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(user);
        Assertions.assertEquals(expectedSummery, summary);
    }

    @Test
    public void givenNormalAndPremiumUserIdAndRides_ShouldReturnInvoiceSummary() throws ExceptionType {
        Ride[] ride = {new Ride(RideType.PREMIUM, 35.0, 45),
                new Ride(RideType.PREMIUM, 10.55, 30),
                new Ride(RideType.NORMAL, 20, 30)};
        String user = "prav";
        invoiceGenerator.addRides(user, ride);
        when(rideRepository.getRides(user)).thenReturn(ride);
        InvoiceSummary expectedSummery = new InvoiceSummary(3, 1063.25);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(user);
        Assertions.assertEquals(expectedSummery, summary);
    }

    @Test
    void WhenGivenUserIdIsNotValid_ShouldReturnEnterTheValidUserId() {
        try {
            when(rideRepository.getRides(userId)).thenThrow(new ExceptionType(ExceptionType.ExceptionName.NAME_NOT_FOUND, "Enter Valid User Id"));
            invoiceGenerator.getInvoiceSummary(userId);
        } catch (ExceptionType e) {
            System.out.println(e.getMessage());
        }
    }
}
