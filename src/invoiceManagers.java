import java.util.ArrayList;
import java.util.List;

public class invoiceManagers {
    private List<Invoice> invoices;

    public invoiceManagers() {
        this.invoices = new ArrayList<Invoice>();
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Invoice> getAllInvoices() {
        for(Invoice invoice : invoices) {
            System.out.println(invoice);
        }
        return invoices;
    }
}

