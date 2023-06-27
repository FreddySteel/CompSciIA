import java.io.File;
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
            //if (productPrice < 0) {
           //     System.out.println("Error: product " + productName + " not found");
            //    continue;  // Skip this product
           // }
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
        StringBuilder invoiceString = new StringBuilder();
        invoiceString.append("Invoice for ").append(customer.getName()).append("\n");
        for (Product product : products) {
            invoiceString.append(product).append("\n");
        }
        invoiceString.append("Total Cost: $").append(String.format("%.2f", totalCost)).append("\n");
        return invoiceString.toString();
    }
    public void updateProduct(Product oldProduct, Product newProduct) {
        int index = products.indexOf(oldProduct);
        if (index != -1) {
            products.set(index, newProduct);
            calculateTotalCost();
        }
    }

    public static void updateInvoiceInFile(String customerName, Product oldProduct, Product newProduct) {
        ArrayList<String> invoices = FileHandling.WholeFileRead("Invoices");
        ArrayList<String> updatedInvoices = new ArrayList<>();

        for (String invoice : invoices) {
            if (invoice.contains("Invoice for " + customerName)) {
                String updatedInvoice = invoice.replace(oldProduct.toString(), newProduct.toString());
                updatedInvoices.add(updatedInvoice);
            } else {
                updatedInvoices.add(invoice);
            }
        }

        // Delete the old file
        File oldFile = new File("Invoices");
        oldFile.delete();

        // Write the updated invoices to the file
        for (String updatedInvoice : updatedInvoices) {
            FileHandling.WriteToFile("Invoices", updatedInvoice, true);
        }
    }
}