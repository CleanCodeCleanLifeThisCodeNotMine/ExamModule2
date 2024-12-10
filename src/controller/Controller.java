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
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("----- CHƯƠNG TRÌNH QUẢN LÝ ĐIỆN THOẠI -----");
        System.out.println("1. Thêm mới");
        System.out.println("2. Xóa ");
        System.out.println("3. Xem danh sách");
        System.out.println("4. Tìm kiếm");
        System.out.println("5. Thoát");
        System.out.print("Chọn chức năng: ");
    }
}
