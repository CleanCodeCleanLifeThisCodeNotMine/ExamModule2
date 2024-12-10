package model;

public class HandedMobile extends model.Mobile {
    private String importedCountry;
    private String status;

    public HandedMobile(int id, String name, double price, int quantity, String manufacturer, String importedCountry, String status) {
        super(id, name, price, quantity, manufacturer);
        this.importedCountry = importedCountry;
        this.status = status;
    }

    @Override
    public String toCSV() {
        return getId() + "," + getName() + "," + getPrice() + "," + getQuantity() + "," + getManufacturer() + "," + importedCountry + "," + status;
    }

    @Override
    public void display() {
        System.out.println("ID: " + getId() + ", Name: " + getName() + ", Price: " + getPrice() + ", Quantity: " + getQuantity() +
                ", Manufacturer: " + getManufacturer() + ", Imported Country: " + importedCountry + ", Status: " + status);
    }
}
