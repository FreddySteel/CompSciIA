import java.util.ArrayList;
import java.util.List;

public class invoiceManagers {
    private List<Invoice> invoices;

    public void InvoiceManager() {
        this.invoices = new ArrayList<Invoice>();
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoices;
    }
}