package manager;

import model.*;
import util.CSVHandler;
import exception.NotFoundProductException;

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
            System.out.println("Enter warranty range (Toan Quoc/Quoc Te): ");
            String warrantyRange = scanner.nextLine();

            Mobile mobile = new AuthenticMobile(id, name, price, quantity, manufacturer, warrantyTime, warrantyRange);
            mobiles.add(mobile);
            CSVHandler.writeMobile(mobile);

        } else if (choice == 2) { // Handed Mobile
            System.out.println("Enter imported country: ");
            String importedCountry = scanner.nextLine();
            System.out.println("Enter status (Da sua chua/Chua sua chua): ");
            String status = scanner.nextLine();

            Mobile mobile = new HandedMobile(id, name, price, quantity, manufacturer, importedCountry, status);
            mobiles.add(mobile);
            CSVHandler.writeMobile(mobile);
        }
        System.out.println("Mobile added successfully!");
    }

    public void displayMobiles() {
        if (mobiles.isEmpty()) {
            System.out.println("Danh sách điện thoại trống.");
        } else {
            System.out.println("Danh sách điện thoại:");
            for (Mobile mobile : mobiles) {
                // Sử dụng toString() để hiển thị chi tiết
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

                // Tìm kiếm điện thoại theo ID
                Mobile toDelete = null;
                for (Mobile mobile : mobiles) {
                    if (mobile.getId() == id) {
                        toDelete = mobile;
                        break;
                    }
                }

                // Nếu không tìm thấy, ném Exception
                if (toDelete == null) {
                    throw new NotFoundProductException("ID điện thoại không tồn tại.");
                }

                // Yêu cầu xác nhận từ người dùng
                System.out.println("Bạn có chắc chắn muốn xóa điện thoại này không? (Yes/No): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("Yes")) {
                    // Thực hiện xóa
                    mobiles.remove(toDelete);
                    CSVHandler.overwriteMobiles(mobiles);

                    // Hiển thị danh sách sau khi xóa
                    System.out.println("Mobile deleted successfully!");
                    displayMobiles();
                } else {
                    System.out.println("Operation canceled. Returning to main menu.");
                }
                break; // Thoát vòng lặp sau khi xử lý xong

            } catch (NotFoundProductException e) {
                System.out.println(e.getMessage());
                System.out.println("Nhấn Enter để quay lại menu chính.");
                scanner.nextLine();
                break; // Quay lại menu chính
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ cho ID.");
            }
        }
    }
}
