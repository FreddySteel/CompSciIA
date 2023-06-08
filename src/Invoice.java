import java.util.ArrayList;

public class Invoice {
    private Customer customer;
    private ArrayList<Product> products;
    private double totalCost;

    public Invoice(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
        this.totalCost = 0;
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
            if (productPrice < 0) {
                System.out.println("Error: product " + productName + " not found");
                continue;  // Skip this product
            }
            Product product = new Product(productName, productPrice);
            for (int i = 0; i < productQuantity; i++) {
                invoice.addProduct(product);
            }
        }
        // Calculate the total cost and add the invoice to the list
        invoice.calculateTotalCost();
        return invoice;

    }

    @Override
    public String toString() {
        return "Invoice: " + customer.getName() + "\n" + products.toString() + "\nTotal Cost: " + totalCost +"\n";
    }
}