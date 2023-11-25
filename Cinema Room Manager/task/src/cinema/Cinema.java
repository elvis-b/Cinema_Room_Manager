package cinema;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Cinema {
    private Scanner scanner = new Scanner(System.in);
    private int numberOfRows;
    private int numberOfSeats;
    private int[][] cinemaRoomArray;
    private int currentIncome;
    private int numberOfPurchasedTickets;
    private int ticketPrice;
    private int rowNumber;
    private int totalIncome;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Cinema() {
        System.out.println("Enter the number of rows:");
        numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        numberOfSeats = scanner.nextInt();
        cinemaRoomArray = new int[numberOfRows][numberOfSeats];
        calculateTotalIncome();
    }

    public void start() {
        while (true) {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showCinemaSeats();
                    break;
                case 2:
                    buyTickets();
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public void calculateTicketPrice() {
        int totalSeats = numberOfRows * numberOfSeats;
        if (totalSeats <= 60) {
            ticketPrice = 10;
        } else {
            if (rowNumber <= numberOfRows / 2) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
    }

    public void calculateTotalIncome() {
        int totalSeats = numberOfRows * numberOfSeats;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            int frontHalf = numberOfRows / 2;
            int backHalf = numberOfRows - frontHalf;
            totalIncome = (frontHalf * numberOfSeats * 10) + (backHalf * numberOfSeats * 8);
        }
    }

    public void showCinemaSeats() {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= numberOfSeats; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int j = 0; j < numberOfRows; j++) {
            System.out.print(j+1);
            for (int k = 0; k < numberOfSeats; k++) {
                if (cinemaRoomArray[j][k] == 1) {
                    System.out.print(" B");
                } else {
                    System.out.print(" S");
                }
            }
            System.out.println();
        }
    }

    public void buyTickets() {
        int seatNumber;
        while (true) {
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            if (rowNumber > numberOfRows || rowNumber < 1) {
                System.out.println("Wrong input!");
            } else if (seatNumber > numberOfSeats || seatNumber < 1) {
                System.out.println("Wrong input!");
            } else if (cinemaRoomArray[rowNumber-1][seatNumber -1] >= 1) {
                System.out.println("That ticket has already been purchased!");
            } else {
                break;
            }
        }
        cinemaRoomArray[rowNumber-1][seatNumber -1] = 1;
        numberOfPurchasedTickets += 1;
        calculateTicketPrice();
        currentIncome += ticketPrice;
        System.out.println("Ticket price: $" + ticketPrice);
    }


    public void statistics() {
        double occupiedPercentage = (numberOfPurchasedTickets / (double) (numberOfSeats * numberOfRows)) * 100;
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets);
        System.out.println("Percentage: " + df.format(occupiedPercentage) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        cinema.start();
    }
}