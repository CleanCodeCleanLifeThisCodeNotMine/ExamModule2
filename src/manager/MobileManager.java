package manager;

import model.*;
import util.CSVHandler;
import exception.NotFoundException;

import java.util.List;
import java.util.Scanner;

public class MobileManager {
    private List<Mobile> mobiles;

    public MobileManager() {
        mobiles = CSVHandler.readMobiles();
    }

    public void addMobile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose type of mobile: 1. Authentic Mobile  2. Handed Mobile");
        int choice = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter manufacturer: ");
        String manufacturer = scanner.nextLine();

        int id = mobiles.size() + 1;

        if (choice == 1) { // Authentic Mobile
            System.out.println("Enter warranty time (days): ");
            int warrantyTime = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter warranty range (Nationwide/International): ");
            String warrantyRange = scanner.nextLine();

            Mobile mobile = new AuthenticMobile(id, name, price, quantity, manufacturer, warrantyTime, warrantyRange);
            mobiles.add(mobile);
            CSVHandler.writeMobile(mobile);

        } else if (choice == 2) { // Handed Mobile
            System.out.println("Enter imported country: ");
            String importedCountry = scanner.nextLine();
            System.out.println("Enter status (Repaired/Not Repaired): ");
            String status = scanner.nextLine();

            Mobile mobile = new HandedMobile(id, name, price, quantity, manufacturer, importedCountry, status);
            mobiles.add(mobile);
            CSVHandler.writeMobile(mobile);
        }
        System.out.println("Mobile added successfully!");
    }

    public void displayMobiles() {
        if (mobiles.isEmpty()) {
            System.out.println("The list of mobiles is empty.");
        } else {
            System.out.println("List of mobiles:");
            for (Mobile mobile : mobiles) {
                System.out.println(mobile.toString());
            }
        }
    }

    public void deleteMobile() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter ID to delete: ");
                int id = Integer.parseInt(scanner.nextLine());

                // Search for the mobile by ID
                Mobile toDelete = null;
                for (Mobile mobile : mobiles) {
                    if (mobile.getId() == id) {
                        toDelete = mobile;
                        break;
                    }
                }

                // Throw exception if not found
                if (toDelete == null) {
                    throw new NotFoundException("Mobile ID not found.");
                }

                // Confirm deletion from user
                System.out.println("Are you sure you want to delete this mobile? (Yes/No): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("Yes")) {
                    // Perform deletion
                    mobiles.remove(toDelete);
                    CSVHandler.overwriteMobiles(mobiles);

                    // Display the list after deletion
                    System.out.println("Mobile deleted successfully!");
                    displayMobiles();
                } else {
                    System.out.println("Operation canceled. Returning to the main menu.");
                }
                break; // Exit the loop after processing

            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("Press Enter to return to the main menu.");
                scanner.nextLine();
                break; // Return to the main menu
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer ID.");
            }
        }
    }

    public void searchMobile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a keyword to search (ID or Name): ");
        String keyword = scanner.nextLine().toLowerCase(); // Convert the keyword to lowercase

        boolean found = false;
        System.out.println("Search results:");

        for (Mobile mobile : mobiles) {
            // Perform approximate search by ID or Name
            if (String.valueOf(mobile.getId()).contains(keyword) || mobile.getName().toLowerCase().contains(keyword)) {
                System.out.println(mobile.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No mobiles found matching the keyword: " + keyword);
        }
    }
}
