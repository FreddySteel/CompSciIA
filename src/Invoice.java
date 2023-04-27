import java.util.ArrayList;
public class Invoice{
    private int id;
    private Customer customer;
    private ArrayList<Product> products;
    private double totalCost;

    public Invoice(int id, Customer customer, ArrayList<Product> products) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.totalCost = calculateTotalCost();
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double getTotalCost() {
        return totalCost;
    }

    private double calculateTotalCost() {
        double cost = 0;
        for (Product product : products) {
            cost += product.getPrice();
        }
        return cost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice ID: ").append(id).append("\n");
        sb.append("Customer Name: ").append(customer.getName()).append("\n");
        sb.append("Customer Number: ").append(customer.getPhoneNumber()).append("\n");
        sb.append("Products Ordered:\n");
        for (Product product : products) {
            sb.append("- ").append(product.getName()).append(": $").append(product.getPrice()).append("\n");
        }
        sb.append("Total Cost: $").append(totalCost);
        return sb.toString();
    }
}