 public class Product {
    private String name;
    private double price;
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    public String toString() {
        System.out.println(name + " ($" + String.format("%.2f", price) + ")");
        return name + " ($" + String.format("%.2f", price) + ")";

    }

 }

