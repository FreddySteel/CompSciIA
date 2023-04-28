import java.util.ArrayList;
public class Invoice{
    private Customer customer;
    private ArrayList<Product> products;
    private double totalCost;
    private ArrayList<ArrayList<String>> orders;

    public Invoice(Customer customer) {
        this.customer = customer;
        this.totalCost = calculateTotalCost();
    }
    public static void addProduct(Product product) {
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

    private double calculateTotalCost() {
        double cost = 0;
        for (Product product : products) {
            cost += product.getPrice();
        }
        return cost;
    }
    public void invoiceGenerator(){

    for(int i = 0;i<orders.size();i++){
        String customerName = orders.get(i).get(0);
        Customer customer = new Customer(customerName);
        Invoice invoice = new Invoice(customer);
    // Add the products to the invoice
        for (int j = 1; j < orders.get(i).; j++) {
        String[] productOrder = orders.get(i).get(j).split(" ");
        String productName = productOrder[0];
        int productQuantity = Integer.parseInt(productOrder[1]);
        Product product = new Product(productName, productQuantity);
        Invoice.addProduct(product);
    }

    // Calculate the total cost and add the invoice to the list
        invoice.calculateTotalCost();
        invoices.add(invoice);

// Print out the invoices


    for(Invoice invoice :invoices){
        System.out.println(invoice);
    }
}
