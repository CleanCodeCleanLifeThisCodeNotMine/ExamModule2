package main;

import manager.MobileManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MobileManager manager = new MobileManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----- QUẢN LÝ ĐIỆN THOẠI -----");
            System.out.println("1. Thêm điện thoại mới");
            System.out.println("2. Xóa điện thoại");
            System.out.println("3. Hiển thị danh sách điện thoại");
            System.out.println("4. Tìm kiếm điện thoại");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");

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
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
}
