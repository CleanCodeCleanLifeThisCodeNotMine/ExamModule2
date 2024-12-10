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
        System.out.println("Chọn loại điện thoại: 1. Điện thoại chính hãng  2. Điện thoại xách tay");
        int choice = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập tên: ");
        String name = scanner.nextLine();
        System.out.println("Nhập giá: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập số lượng: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập nhà sản xuất: ");
        String manufacturer = scanner.nextLine();

        int id = mobiles.size() + 1;

        if (choice == 1) { // Authentic Mobile
            System.out.println("Nhập thời gian bảo hành (ngày): ");
            int warrantyTime = Integer.parseInt(scanner.nextLine());
            System.out.println("Nhập phạm vi bảo hành (1. Toàn quốc, 2. Quốc Tế (Default: 2): ");
            String warrantyRange = scanner.nextLine();

            // Nếu nhập rỗng, mặc định là "Quốc Tế"
            if (warrantyRange.isEmpty()) {
                warrantyRange = "Quốc Tế";
            } else if (warrantyRange.equals("1")) {
                warrantyRange = "Toàn quốc";
            } else if (warrantyRange.equals("2")) {
                warrantyRange = "Quốc Tế";
            } else {
                System.out.println("Mặc định là 'Quốc Tế'.");
                warrantyRange = "Quốc Tế";
            }

            Mobile mobile = new AuthenticMobile(id, name, price, quantity, manufacturer, warrantyTime, warrantyRange);
            mobiles.add(mobile);
            CSVHandler.writeMobile(mobile);

        } else if (choice == 2) { // Handed Mobile
            System.out.println("Nhập quốc gia nhập khẩu: ");
            String importedCountry = scanner.nextLine();
            System.out.println("Chọn trạng thái: 1. Đã sửa chữa  2. Chưa sửa chữa (Mặc định: 2)");
            int statusChoice = Integer.parseInt(scanner.nextLine());
            String status = "";

            if (statusChoice == 1) {
                status = "Đã sửa chữa";
            } else if (statusChoice == 2) {
                status = "Chưa sửa chữa";
            } else {
                System.out.println("Mặc định là 'Chưa sửa chữa'.");
                status = "Chưa sửa chữa"; // Nếu người dùng nhập không hợp lệ, mặc định là "Chưa sửa chữa"
            }

            Mobile mobile = new HandedMobile(id, name, price, quantity, manufacturer, importedCountry, status);
            mobiles.add(mobile);
            CSVHandler.writeMobile(mobile);
        }
        System.out.println("Điện thoại đã được thêm thành công!");
    }

    public void displayMobiles() {
        if (mobiles.isEmpty()) {
            System.out.println("Danh sách điện thoại trống.");
        } else {
            System.out.println("Danh sách điện thoại:");
            for (Mobile mobile : mobiles) {
                System.out.println(mobile.toString());
            }
        }
    }

    public void deleteMobile() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Nhập ID để xóa: ");
                int id = Integer.parseInt(scanner.nextLine());

                // Tìm kiếm điện thoại theo ID
                Mobile toDelete = null;
                for (Mobile mobile : mobiles) {
                    if (mobile.getId() == id) {
                        toDelete = mobile;
                        break;
                    }
                }

                // Ném ngoại lệ nếu không tìm thấy
                if (toDelete == null) {
                    throw new NotFoundException("ID điện thoại không tồn tại.");
                }

                // Xác nhận xóa từ người dùng
                System.out.println("Bạn có chắc chắn muốn xóa điện thoại này không? (Có/Không): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("Có")) {
                    // Thực hiện xóa
                    mobiles.remove(toDelete);
                    CSVHandler.overwriteMobiles(mobiles);

                    // Hiển thị danh sách sau khi xóa
                    System.out.println("Điện thoại đã được xóa thành công!");
                    displayMobiles();
                } else {
                    System.out.println("Hủy thao tác xóa. Quay lại menu chính.");
                }
                break; // Thoát vòng lặp sau khi xử lý xong

            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("Nhấn Enter để quay lại menu chính.");
                scanner.nextLine();
                break; // Quay lại menu chính
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một ID hợp lệ.");
            }
        }
    }

    public void searchMobile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập từ khóa để tìm kiếm (ID hoặc Tên): ");
        String keyword = scanner.nextLine().toLowerCase(); // Chuyển từ khóa thành chữ thường

        boolean found = false;
        System.out.println("Kết quả tìm kiếm:");

        for (Mobile mobile : mobiles) {
            // Tìm kiếm gần đúng theo ID hoặc Tên
            if (String.valueOf(mobile.getId()).contains(keyword) || mobile.getName().toLowerCase().contains(keyword)) {
                System.out.println(mobile.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy điện thoại phù hợp với từ khóa: " + keyword);
        }
    }
}
