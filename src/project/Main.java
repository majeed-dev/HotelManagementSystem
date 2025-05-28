
package project;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

  
        ArrayList<User> users = new ArrayList<>();
        users.add(new Admin("Majeed", "Mm1234", "admin one", example123@psu.edu.sa"));

        users.add(new Customer("Customer", "Cc1234", "cust", "cust@psu.edu.sa"));


        while (true) {
            System.out.println("\n--- Hotel Management System ---");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Customer");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 3) {
                System.out.println("Exiting system.");
                break;
            }

            System.out.print("Enter Username: ");
            String username = sc.nextLine();
            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            boolean found = false;

            for (User user : users) {
                if (user.login(username, password)) {
                    found = true;
                    if (choice == 1 && user instanceof Admin) {
                        System.out.println("Login successful as Admin: " + user.getName());
                        user.menu();
                    } else if (choice == 2 && user instanceof Customer) {
                        System.out.println("Login successful as Customer: " + user.getName());
                        user.menu();
                    } else {
                        System.out.println("User type does not match selection.");
                    }
                    break;
                }
            }

            if (!found) {
                System.out.println("Invalid username or password.");
            }
        }

        sc.close();
    }
}
