package Question1;

public class MovieApp {
    public static void main(String[] args) {
        // Single-dimensional arrays for movies and months
        String[] movies = {"Napoleon", "Oppenheimer"};
        String[] months = {"JAN", "FEB", "MAR"};

        // Two-dimensional array for monthly ticket sales
        // Row 0: Napoleon (Jan: 3000, Feb: 1500, Mar: 1700)
        // Row 1: Oppenheimer (Jan: 3500, Feb: 1200, Mar: 1600)
        int[][] monthlySales = {
                {3000, 1500, 1700},
                {3500, 1200, 1600}
        };

        MovieTickets ticketProcessor = new MovieTickets();
        int[] totalSales = new int[movies.length];

        System.out.println("MOVIE TICKET SALES REPORT - 2024");
        System.out.printf("%-15s %-8s %-8s %-8s%n", "", months[0], months[1], months[2]);
        System.out.println("___________________________________________________");

        for (int i = 0; i < movies.length; i++) {
            totalSales[i] = ticketProcessor.TotalMovieSales(monthlySales[i]);
            System.out.printf("%-15s %-8d %-8d %-8d%n", movies[i], monthlySales[i][0], monthlySales[i][1], monthlySales[i][2]);
        }

        System.out.println();
        for (int i = 0; i < movies.length; i++) {
            System.out.println("Total movie ticket sales for " + movies[i] + ": " + totalSales[i]);
        }

        System.out.println();
        String topMovie = ticketProcessor.TopMovie(movies, totalSales);
        System.out.println("Top performing movie: " + topMovie);
    }
}