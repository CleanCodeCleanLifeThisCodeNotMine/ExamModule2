package model;

public abstract class Mobile {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String manufacturer;

    public Mobile(int id, String name, double price, int quantity, String manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    // Abstract method toCSV to be implemented by subclasses
    public abstract String toCSV();
}
