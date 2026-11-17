package Question2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MovieTicketsProcessor implements IMovieTickets {

    @Override
    public double CalculateTotalTicketPrice(int numberOfTickets, double ticketPrice) {
        double subtotal = numberOfTickets * ticketPrice;
        double vat = subtotal * 0.14;
        return subtotal + vat;
    }

    @Override
    public boolean ValidateData(MovieTicketData movieTicketData) {
        if (movieTicketData == null) {
            return false;
        }
        // Movie Ticket Name Cannot be empty
        if (movieTicketData.getMovieName() == null || movieTicketData.getMovieName().trim().isEmpty()) {
            return false;
        }
        // Movie Ticket Price Cannot be less than or equal to zero (0)
        if (movieTicketData.getTicketPrice() <= 0) {
            return false;
        }
        // Number of Movie Tickets Cannot be less than or equal to zero (0)
        if (movieTicketData.getNumberOfTickets() <= 0) {
            return false;
        }
        return true;
    }

    public void saveReportToFile(String reportContent) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("report.txt"))) {
            writer.println(reportContent);
        } catch (IOException e) {
            System.err.println("Error saving report to file: " + e.getMessage());
        }
    }
}