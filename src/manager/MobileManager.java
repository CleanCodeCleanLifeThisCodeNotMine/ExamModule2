package manager;

import model.Mobile;
import util.CSVHandler;
import exception.NotFoundProductException;

import java.util.List;
import java.util.Scanner;

public class MobileManager {
    private List<Mobile> mobiles;

    public MobileManager() {
        mobiles = CSVHandler.readMobiles();
    }

    public void deleteMobile() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Nhập ID điện thoại cần xóa:");
                int id = Integer.parseInt(scanner.nextLine());

                // Tìm điện thoại theo ID
                Mobile toDelete = null;
                for (Mobile mobile : mobiles) {
                    if (mobile.getId() == id) {
                        toDelete = mobile;
                        break;
                    }
                }

                if (toDelete == null) {
                    // Nếu ID không tồn tại, ném Exception
                    throw new NotFoundProductException("ID điện thoại không tồn tại.");
                }

                // Yêu cầu xác nhận từ người dùng
                System.out.println("Bạn có chắc chắn muốn xóa điện thoại này không? (Yes/No):");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("Yes")) {
                    // Thực hiện xóa điện thoại
                    mobiles.remove(toDelete);
                    CSVHandler.overwriteMobiles(mobiles);

                    // Hiển thị danh sách sau khi xóa
                    System.out.println("Điện thoại đã được xóa thành công.");
                    displayMobiles();
                } else {
                    System.out.println("Hủy thao tác xóa. Quay lại menu chính.");
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

    public void displayMobiles() {
        for (Mobile mobile : mobiles) {
            mobile.display();
        }
    }
}
