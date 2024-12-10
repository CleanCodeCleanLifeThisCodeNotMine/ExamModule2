package util;

import model.Mobile;
import model.AuthenticMobile;
import model.HandedMobile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    private static final String FILE_PATH = "data/mobiles.csv";

    public static List<Mobile> readMobiles() {
        List<Mobile> mobiles = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                int quantity = Integer.parseInt(parts[3]);
                String manufacturer = parts[4];

                if (parts.length == 7) { // Authentic Mobile
                    int warrantyTime = Integer.parseInt(parts[5]);
                    String warrantyRange = parts[6];
                    mobiles.add(new AuthenticMobile(id, name, price, quantity, manufacturer, warrantyTime, warrantyRange));
                } else if (parts.length == 7) { // Handed Mobile
                    String importedCountry = parts[5];
                    String status = parts[6];
                    mobiles.add(new HandedMobile(id, name, price, quantity, manufacturer, importedCountry, status));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return mobiles;
    }

    public static void writeMobile(Mobile mobile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(mobile.toCSV());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public static void overwriteMobiles(List<Mobile> mobiles) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Mobile mobile : mobiles) {
                bw.write(mobile.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error overwriting file: " + e.getMessage());
        }
    }
}
