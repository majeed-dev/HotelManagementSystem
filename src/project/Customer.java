package project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Customer extends User {

    public Customer(String username, String password, String name, String email) {
        super(username, password, name, email);
    }

    private void viewAvailableRooms() {
        File file = new File("Rooms.txt");
        System.out.println("\n--- Available Rooms ---");

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(",");
                System.out.println("Room " + parts[0] + " , Type " + parts[1] + " ,  Price $" + parts[2]);
            }
        } catch (IOException e) {
            System.out.println("Error reading room file: " + e.getMessage());
        }
    }

    private void bookRoom() {
        Scanner ss = new Scanner(System.in);
        System.out.println("Enter Room Number to Book: ");
        String roomNumber = ss.nextLine();

        System.out.println("Enter number of Nights: ");
        int nights = ss.nextInt();

        File roomFile = new File("Rooms.txt");
        boolean roomFound = false;
        String roomType = "";
        double pricePerNight = 0;

        try (Scanner reader = new Scanner(roomFile)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(roomNumber)) {
                    roomFound = true;
                    roomType = parts[1];
                    pricePerNight = Double.parseDouble(parts[2]);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading rooms: " + e.getMessage());
            return;
        }

        if (!roomFound) {
            System.out.println("Room not found!");
            return;
        }

        double total = pricePerNight * nights;

        try (FileWriter writer = new FileWriter("Bookings.txt", true)) {
            writer.write(getUsername() + "," + roomNumber + "," + roomType + "," + nights + "," + total + "\n");
            System.out.println("Room booked successfully!");
            System.out.printf("Total cost: $%.2f\n", total);
        } catch (IOException e) {
            System.out.println("Error saving booking: " + e.getMessage());
        }
    }

    private void viewMyBookings() {
        System.out.println("\n--- Your Bookings ---");
        File file = new File("Bookings.txt");

        try (Scanner ss = new Scanner(file)) {
            boolean found = false;
            while (ss.hasNextLine()) {
                String line = ss.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(getUsername())) {
                    System.out.println("Room: " + parts[1] + ", Type: " + parts[2] + ", Nights: " + parts[3] + ", Total: $" + parts[4]);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No bookings found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading bookings: " + e.getMessage());
        }
    }

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. View My Bookings");
            System.out.println("4. Exit");

            System.out.print("Select choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    viewMyBookings();
                    break;
                case 4:
                    System.out.println("Exiting customer menu.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
