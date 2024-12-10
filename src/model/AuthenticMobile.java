package model;

public class AuthenticMobile extends model.Mobile {
    private int warrantyTime;
    private String warrantyRange;

    public AuthenticMobile(int id, String name, double price, int quantity, String manufacturer, int warrantyTime, String warrantyRange) {
        super(id, name, price, quantity, manufacturer);
        this.warrantyTime = warrantyTime;
        this.warrantyRange = warrantyRange;
    }

    @Override
    public String toCSV() {
        return getId() + "," + getName() + "," + getPrice() + "," + getQuantity() + "," + getManufacturer() + "," + warrantyTime + "," + warrantyRange;
    }

    @Override
    public void display() {
        System.out.println("ID: " + getId() + ", Name: " + getName() + ", Price: " + getPrice() + ", Quantity: " + getQuantity() +
                ", Manufacturer: " + getManufacturer() + ", Warranty Time: " + warrantyTime + " days, Warranty Range: " + warrantyRange);
    }
}
