import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private static stockList stockList;
    private Customer customer;
    private ArrayList<Product> products;
    private double totalCost;
    private boolean isWrittenToFile;

    public Invoice(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>(); // Initialize the products list
        this.totalCost = 0; // Initialize total cost
        this.isWrittenToFile = false;
    }
    public Invoice(ArrayList<String> order) {
        this.products = new ArrayList<>();

        // Check for correct order size
        if (order.size() < 2) return;

        // Extract customer name and phone number from the first line
        String firstLine = order.get(0); // "Invoice for Customer: [Name] Phone: [Phone]"
        String[] firstLineParts = firstLine.split(" Phone: ");
        String customerName = firstLineParts[0].replace("Invoice for Customer:", "").trim();
        String customerPhone = firstLineParts.length > 1 ? firstLineParts[1].trim() : "";

        // Create the customer object
        this.customer = new Customer(customerName, customerPhone);

        // Parse and add products
        for (int i = 1; i < order.size() - 1; i++) {
            String line = order.get(i);
            String productName = line.substring(0, line.indexOf(" ($"));
            String priceString = line.substring(line.indexOf("$") + 1, line.indexOf(")"));
            double price = Double.parseDouble(priceString);
            this.products.add(new Product(productName, price));
        }

        // Calculate the total cost
        this.calculateTotalCost();
    }
    public Invoice(Customer customer, List<String> subList) {
        this.customer = customer;
        this.products = new ArrayList<>();
        this.totalCost = 0;

        for (String line : subList) {
            // Assuming each line in subList is a product line
            // Parse each line to create Product objects and add them to the products list
            // Example parsing logic (adjust as per your product line format):
            String productName = line.substring(0, line.indexOf(" ($"));
            String priceString = line.substring(line.indexOf("$") + 1, line.indexOf(")"));
            double price = Double.parseDouble(priceString);
            this.products.add(new Product(productName, price));
        }

        calculateTotalCost(); // Calculate the total cost after adding all products
    }

    public void addProduct(Product product) {
        products.add(product);
        totalCost += product.getPrice();
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

    private void calculateTotalCost() {
        totalCost = 0;
        for (Product product : products) {
            totalCost += product.getPrice();
        }
    }

    public static Invoice invoiceGenerator(ArrayList<String> order) {
        if (order.isEmpty()) {
            return null;
        }
        String customerName = order.get(0);
        String customerNumber = order.get(1);
        Customer customer = new Customer(customerName, customerNumber);
        Invoice invoice = new Invoice(customer);
        // Add the products to the invoice
        for (int j = 2; j < order.size(); j++) {
            String[] productOrder = order.get(j).split(" ");
            String productName = productOrder[0];
            int productQuantity = Integer.parseInt(productOrder[1]);
            double productPrice = stockList.getProductPrice(productName);
            Product product = new Product(productName, productPrice);
            for (int i = 0; i < productQuantity; i++) {
                invoice.addProduct(product);
            }
        }
        // Calculate the total cost and add the invoice to the list
        invoice.calculateTotalCost();
        return invoice;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer Name: ").append(this.customer.getName()).append("\n");
        sb.append("Customer Phone: ").append(this.customer.getPhoneNumber()).append("\n");
        for (Product product : this.products) {
            sb.append(product.getName()).append(" ($").append(product.getPrice()).append(")\n");
        }
        sb.append("Total Cost: $").append(this.totalCost).append("\n");
        return sb.toString();
    }
    public void updateProduct(Product oldProduct, Product newProduct) {
        int index = products.indexOf(oldProduct);
        if (index != -1) {
            products.set(index, newProduct);
            calculateTotalCost();
        }
    }
    public String getCustomerName() {
        String fullInfo = customer.getName();
        // Assuming the format is "Customer: [Name] Phone: [Number]"
        String[] parts = fullInfo.split(" "); // Split by space
        // Find the index of the word "Customer:"
        int customerIndex = -1;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("Customer:")) {
                customerIndex = i;
                break;
            }
        }
        // Return the name part if found
        if (customerIndex != -1 && customerIndex + 1 < parts.length) {
            return parts[customerIndex + 1];
        } else {
            return "Unknown"; // or any default name
        }
    }
    public void setWrittenToFile(boolean written) {
        this.isWrittenToFile = written;
    }

    public boolean isWrittenToFile() {
        return this.isWrittenToFile;
    }
}
