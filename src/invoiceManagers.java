import java.util.ArrayList;
import java.util.List;

public class invoiceManagers {
    private List<Invoice> invoices;

    public invoiceManagers() {
        this.invoices = new ArrayList<Invoice>();
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
}

