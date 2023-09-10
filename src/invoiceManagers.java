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
            invoicesString.append(invoice.toString()).append("\n");
        }
        FileHandling.WriteToFile("Invoices", invoicesString.toString(), false);
        System.out.println("Invoices written to file: invoices");
    }
}
