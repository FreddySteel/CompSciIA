import java.util.ArrayList;
import java.util.List;

public class invoiceManagers {

    private List<Invoice> invoices;


    public invoiceManagers() {
        this.invoices = new ArrayList<Invoice>();
    }
    public invoiceManagers(String fileName){
        ArrayList<String> invoices = FileHandling.WholeFileRead(fileName);
        StringBuilder output = new StringBuilder();
        ArrayList<String> data = new ArrayList<>();
        for (String invoice : invoices) {
            data.add(invoice);
            if (invoice.contains("Total Cost")){
                Invoice i = new Invoice(data);
                data = new ArrayList<>();
                System.out.println(i.toString());
            }
        }

        System.out.println(" --- ");
    }

    public void addInvoice(Invoice invoice) {
        this.invoices.add(invoice);  // this should add a new invoice to the list
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

