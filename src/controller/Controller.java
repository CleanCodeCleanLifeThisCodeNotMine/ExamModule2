package controller;

import manager.MobileManager;

import java.util.Scanner;

public class Controller {
    private MobileManager manager = new MobileManager();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            displayMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    manager.addMobile();
                    break;
                case 2:
                    manager.deleteMobile();
                    break;
                case 3:
                    manager.displayMobiles();
                    break;
                case 4:
                    manager.searchMobile();
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void displayMenu() {
        System.out.println("----- MOBILE MANAGEMENT -----");
        System.out.println("1. Add new mobile");
        System.out.println("2. Delete mobile");
        System.out.println("3. Display list of mobiles");
        System.out.println("4. Search mobile");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }
}
