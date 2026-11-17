package Question1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTicketsTest {

    @Test
    public void CalculateTotalSales_ReturnsExpectedTotalSales() {
        MovieTickets tickets = new MovieTickets();
        int[] napoleonSales = {3000, 1500, 1700};
        int expectedTotal = 6200;
        int actualTotal = tickets.TotalMovieSales(napoleonSales);
        assertEquals(expectedTotal, actualTotal, "Total sales for Napoleon should be 6200");
    }

    @Test
    public void TopMovieSales_ReturnsTopMovie() {
        MovieTickets tickets = new MovieTickets();
        String[] movies = {"Napoleon", "Oppenheimer"};
        int[] totalSales = {6200, 6300};
        String expectedTopMovie = "Oppenheimer";
        String actualTopMovie = tickets.TopMovie(movies, totalSales);
        assertEquals(expectedTopMovie, actualTopMovie, "Top performing movie should be Oppenheimer");
    }
}