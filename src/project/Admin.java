
package project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Admin extends User {

    public Admin(String username, String password, String name, String email) {
        super(username, password, name, email);
    }

    private void addRoom() {
        Scanner ss = new Scanner(System.in);
        try (FileWriter writer = new FileWriter("Rooms.txt", true)) {
            System.out.println("Enter room number: ");
            String roomNumber = ss.nextLine();

            System.out.println("Enter room type: ");
            String roomType = ss.nextLine();

            System.out.println("Enter room price: ");
            String roomPrice = ss.nextLine();

            writer.write(roomNumber + "," + roomType + "," + roomPrice + "\n");

            System.out.println("Room Added");
        } catch (IOException e) {
            System.out.println("Error while writing to file " + e.getMessage());
        }
    }

    private void deleteRoom() {
        Scanner ss = new Scanner(System.in);
        System.out.println("Enter Room Number to Delete: ");
        String room = ss.nextLine();

        File inputFile = new File("Rooms.txt");
        File tempFile = new File("roomsTemp.txt");

        boolean found = false;

        try (Scanner reader = new Scanner(inputFile);
                FileWriter writer = new FileWriter(tempFile)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(",");
                if (!parts[0].equals(room)) {
                    writer.write(line + "\n");
                } else {
                    found = true;
                }
            }

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
            return;
        }
        if (found) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                System.out.println("Room Deleted");
            } else {
                System.out.println("Error updating file");
            }
        } else {
            tempFile.delete();
            System.out.println("Room not found");
        }

    }

    public void generateReport() {
        File file = new File("Rooms.txt");
        System.out.println("\n--- Rooms Report ---");

        try (Scanner reader = new Scanner(file)) {
            int count = 0;
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] parts = line.split(",");
                System.out.println("Room " + parts[0] + " , Type " + parts[1] + " ,  Price" + parts[2]);
                count++;
            }
            System.out.println("Total rooms: " + count);
        } catch (IOException e) {
            System.out.println("Error reading file " + e.getMessage());
        }
    }

    @Override
    public void menu() {
        Scanner ss = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Admin Menu---");
            System.out.println("1. Add Room");
            System.out.println("2. Delete Rooom");
            System.out.println("3. Generate Report");
            System.out.println("4. Exit Menu");

            System.out.println("Select choice: ");
            int c = ss.nextInt();
            ss.nextLine();

            switch (c) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    deleteRoom();
                    break;
                case 3:
                    generateReport();
                    break;
                case 4:
                    System.out.println("Exiting admin menu");
                    return;
                default:
                    System.out.println("Invalid choice");
            }

        }
    }

}
