import java.util.ArrayList;
import java.util.List;

public class invoiceManagers {

    private List<Invoice> invoices;

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public invoiceManagers() {
        this.invoices = new ArrayList<Invoice>();
    }

    public invoiceManagers(String fileName){
        this.invoices = new ArrayList<Invoice>();
        //loadInvoicesFromFile("Invoices.txt");
        ArrayList<String> invoiceStrings = FileHandling.WholeFileRead(fileName);
        StringBuilder output = new StringBuilder();
        ArrayList<String> data = new ArrayList<>();
        for (String invoiceString : invoiceStrings) {
            data.add(invoiceString);
            if (invoiceString.contains("Total Cost")){
                Invoice i = new Invoice(data);
                this.invoices.add(i); // Add invoice to list
                data = new ArrayList<>();
                System.out.println(i.toString());
            }
        }

        System.out.println(" --- ");
    }


    public List<Invoice> getAllInvoices() {
        int count = 1;
        for (Invoice invoice : invoices) {
            System.out.println("Invoice " + count + ":\n" + invoice);
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
            if (invoice.getCustomerName().equals(customerName)) {
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
            System.out.println("Reading line: " + line); // Debug print
            if (!line.trim().isEmpty()) {
                currentInvoiceData.add(line);
                if (line.startsWith("Total Cost:")) {
                    Invoice invoice = new Invoice(currentInvoiceData);
                    invoices.add(invoice);
                    System.out.println("Loaded invoice: " + invoice); // Debug print
                    currentInvoiceData = new ArrayList<>();
                }
            }
        }
    }
}
