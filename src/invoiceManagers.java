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
        ArrayList<String> invoiceStrings = FileHandling.WholeFileRead("Invoices.txt");
        ArrayList<String> data = new ArrayList<>();
        List<Invoice> customerInvoices = new ArrayList<>();

        for (String line : invoiceStrings) {
            if (!line.trim().isEmpty()) {
                data.add(line); // Add line to current invoice data
            }

            // Check if the line indicates the end of an invoice or it's the last line
            if (line.trim().isEmpty() || invoiceStrings.indexOf(line) == invoiceStrings.size() - 1) {
                // Check if the data belongs to the specified customer
                if (!data.isEmpty() && data.get(0).contains(customerName)) {
                    Invoice invoice = new Invoice(data);
                    customerInvoices.add(invoice);
                }
                data.clear(); // Clear for the next invoice
            }
        }
        return customerInvoices;
    }
}
