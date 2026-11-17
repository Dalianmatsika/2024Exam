package Question2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieTicketGUIApp extends JFrame {
    private JComboBox<String> movieComboBox;
    private JTextField ticketsTextField;
    private JTextField priceTextField;
    private JTextArea reportTextArea;
    private MovieTicketsProcessor processor;

    public MovieTicketGUIApp() {
        setTitle("MOVIE TICKETS");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        processor = new MovieTicketsProcessor();

        // Menu System
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processMenuItem = new JMenuItem("Process");
        processMenuItem.addActionListener(new ProcessAction());
        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(new ClearAction());

        toolsMenu.add(processMenuItem);
        toolsMenu.add(clearMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // Form Layout
        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("MOVIE:"));
        String[] movies = {"Napoleon", "Oppenheimer", "Damsel"};
        movieComboBox = new JComboBox<>(movies);
        panel.add(movieComboBox);

        panel.add(new JLabel("NUMBER OF TICKETS:"));
        ticketsTextField = new JTextField("3");
        panel.add(ticketsTextField);

        panel.add(new JLabel("TICKET PRICE:"));
        priceTextField = new JTextField("100");
        panel.add(priceTextField);

        JPanel reportPanel = new JPanel(new BorderLayout(5, 5));
        reportPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        reportPanel.add(new JLabel("TICKET REPORT:"), BorderLayout.NORTH);

        reportTextArea = new JTextArea(8, 25);
        reportTextArea.setEditable(false);
        reportTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(reportTextArea);
        reportPanel.add(scrollPane, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(reportPanel, BorderLayout.CENTER);
    }

    private class ProcessAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String movieName = (String) movieComboBox.getSelectedItem();
                int numTickets = Integer.parseInt(ticketsTextField.getText().trim());
                double price = Double.parseDouble(priceTextField.getText().trim());

                MovieTicketData data = new MovieTicketData(movieName, numTickets, price);

                if (!processor.ValidateData(data)) {
                    JOptionPane.showMessageDialog(MovieTicketGUIApp.this,
                            "Invalid data. Tickets and price must be greater than zero.",
                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double totalPrice = processor.CalculateTotalTicketPrice(numTickets, price);

                String report = String.format("MOVIE TICKET REPORT\n" +
                                "**************************************************\n" +
                                "MOVIE NAME: %s\n" +
                                "MOVIE TICKET PRICE: R %.1f\n" +
                                "NUMBER OF TICKETS: %d\n" +
                                "TOTAL TICKET PRICE: R %.1f\n" +
                                "**************************************************",
                        movieName, price, numTickets, totalPrice);

                reportTextArea.setText(report);
                processor.saveReportToFile(report);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MovieTicketGUIApp.this,
                        "Please enter valid numeric values for tickets and price.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ClearAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            movieComboBox.setSelectedIndex(0);
            ticketsTextField.setText("");
            priceTextField.setText("");
            reportTextArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MovieTicketGUIApp().setVisible(true);
        });
    }
}