import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class invoiceManagers {

    private List<Invoice> invoices;

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public invoiceManagers() {
        this.invoices = new ArrayList<Invoice>();
    }

    public invoiceManagers(String fileName) {
        this.invoices = new ArrayList<Invoice>();
        ArrayList<String> invoiceStrings = FileHandling.WholeFileRead(fileName);
        ArrayList<String> currentInvoiceData = new ArrayList<>();

        for (String line : invoiceStrings) {
            if (!line.trim().isEmpty()) {
                currentInvoiceData.add(line);
                if (line.startsWith("Total Cost")) {
                    // Extract customer name and phone number
                    String firstLine = currentInvoiceData.get(0); // "Invoice for Customer: [Name] Phone: [Phone]"
                    String[] parts = firstLine.split("Phone:");
                    String customerName = parts[0].replace("Invoice for Customer:", "").trim();
                    String customerPhone = parts.length > 1 ? parts[1].trim() : "";

                    // Create customer
                    Customer customer = new Customer(customerName, customerPhone);

                    // Create invoice with customer and products

                    Invoice invoice = new Invoice(customer, currentInvoiceData.subList(1, currentInvoiceData.size() - 1));
                    invoice.setWrittenToFile(true);
                    this.invoices.add(invoice);
                    currentInvoiceData.clear();
                }
            }
        }
    }

    public List<Invoice> getAllInvoices() {
        int count = 1;
        for (Invoice invoice : invoices) {
 //           System.out.println("Invoice " + count + ":\n" + invoice);
            count++;
        }
        return invoices;
    }

    public void writeInvoicesToFile() {
        StringBuilder invoicesString = new StringBuilder();
        for (Invoice invoice : invoices) {
            if (!invoice.isWrittenToFile()) {
                invoicesString.append(invoice.toString()).append("\n");
                invoice.setWrittenToFile(true);
            }
        }

        if (invoicesString.length() > 0) {
            FileHandling.WriteToFile("Invoices.txt", invoicesString.toString(), true);
            System.out.println("New invoices written to file: Invoices.txt");
        } else {
            System.out.println("No new invoices to write.");
        }
    }

    public List<Invoice> getInvoicesByCustomer(String customerName) {
        List<Invoice> customerInvoices = new ArrayList<>();
        for (Invoice invoice : this.invoices) {
            if (invoice.getCustomerName().equalsIgnoreCase(customerName)) {
                customerInvoices.add(invoice);
            }
        }
        return customerInvoices;
    }

    // Make sure to call this method after initializing the object to load invoices
    public void loadInvoicesFromFile(String fileName) {
        ArrayList<String> invoiceStrings = FileHandling.WholeFileRead(fileName);
        ArrayList<String> currentInvoiceData = new ArrayList<>();

        for (String line : invoiceStrings) {
//            System.out.println("Reading line: " + line); // Debug print
            if (!line.trim().isEmpty()) {
                currentInvoiceData.add(line);
                if (line.startsWith("Total Cost:")) {
                    Invoice invoice = new Invoice(currentInvoiceData);
                    invoices.add(invoice);
//                    System.out.println("Loaded invoice: " + invoice); // Debug print
                    currentInvoiceData = new ArrayList<>();
                }
            }
        }
    }

    public void deleteInvoicesByCustomer(String customerName) {
        if (customerName == null || customerName.isEmpty()) {
            return; // Invalid input handling
        }
        Iterator<Invoice> iterator = invoices.iterator();
        while (iterator.hasNext()) {
            Invoice invoice = iterator.next();
            if (invoice.getCustomerName().equals(customerName)) {
                iterator.remove(); // Remove the invoice from the list
            }
        }
    }
    void updateInvoiceFiles(String fileName) {
        List<String> invoiceStrings = invoices.stream()
                .map(Invoice::toString) // Assuming Invoice has a toString method formatted for file writing
                .collect(Collectors.toList());
        FileHandling.overwriteFile("Invoices.txt", new ArrayList<>(invoiceStrings));
    }
    public void deleteInvoicesForCustomer(String customerName) {
        if (customerName == null || customerName.isEmpty()) {
            return; // Handle invalid input
        }

        invoices.removeIf(invoice -> invoice.getCustomerName().equals(customerName));

        // Update the file with the modified list of invoices
        updateInvoiceFile();
    }
    private void updateInvoiceFile() {
        // Assuming you have a method to convert each Invoice object to a string
        List<String> invoiceStrings = invoices.stream()
                .map(Invoice::toString)
                .collect(Collectors.toList());
        FileHandling.overwriteFile("Invoices.txt", new ArrayList<>(invoiceStrings));
    }
}
