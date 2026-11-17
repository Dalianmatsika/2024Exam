package Question2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTicketsGuiTest {

    @Test
    public void CalculateTotalTicketPrice_CalculatedSuccessfully() {
        MovieTicketsProcessor processor = new MovieTicketsProcessor();
        int numberOfTickets = 3;
        double ticketPrice = 100.0;
        // Calculation: (3 * 100) + 14% VAT = 300 + 42 = 342.0
        double expectedTotal = 342.0;
        double actualTotal = processor.CalculateTotalTicketPrice(numberOfTickets, ticketPrice);
        assertEquals(expectedTotal, actualTotal, 0.001, "Total ticket price including 14% VAT should be 342.0");
    }

    @Test
    public void ValidationTests_InvalidTicketPrice_ReturnsFalse() {
        MovieTicketsProcessor processor = new MovieTicketsProcessor();
        // Ticket price is set to 0, which violates validation rules
        MovieTicketData invalidData = new MovieTicketData("Napoleon", 3, 0.0);
        boolean isValid = processor.ValidateData(invalidData);
        assertFalse(isValid, "Validation should fail when ticket price is less than or equal to zero");
    }
}