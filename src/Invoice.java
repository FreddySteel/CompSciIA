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
        String firstLine = order.get(0); // "Customer: [Name] Phone: [Phone]"
        String[] parts = firstLine.split(" Phone: ");
        String customerName = parts[0].replace("Customer:", "").trim();
        String customerPhone = parts.length > 1 ? parts[1].trim() : "";

        // Create a Customer object
        Customer customer = new Customer(customerName, customerPhone);

        // Create an Invoice object with the customer
        Invoice invoice = new Invoice(customer);
        // Add the products to the invoice
        for (int j = 1; j < order.size(); j++) {
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
        sb.append("Invoice for Customer: ").append(getCustomerName())
                .append(" Phone: ").append(this.customer.getPhoneNumber()).append("\n");
        for (Product product : this.products) {
            sb.append(product.getName()).append(" ($").append(String.format("%.2f", product.getPrice())).append(")\n");
        }
        sb.append("Total Cost: $").append(String.format("%.2f", this.totalCost));
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
        return fullInfo;
    }
    public void setWrittenToFile(boolean written) {
        this.isWrittenToFile = written;
    }

    public boolean isWrittenToFile() {
        return this.isWrittenToFile;
    }
}
