import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvoiceGUI extends JFrame {
    private JTextField searchField;
    private JList<String> customerList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    private invoiceManagers invoiceManager;
    private JTextArea invoiceTextArea;

    public InvoiceGUI() {
        invoiceManager = new invoiceManagers();
        setTitle("Invoice GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        invoiceTextArea = new JTextArea();
        add(new JScrollPane(invoiceTextArea), BorderLayout.SOUTH);

        // Create the search field
        searchField = new JTextField(20);
        add(searchField, BorderLayout.NORTH);

        // Create the list model and the list
        listModel = new DefaultListModel<>();
        customerList = new JList<>(listModel);
        scrollPane = new JScrollPane(customerList);
        add(scrollPane, BorderLayout.CENTER);

        // Initially populate the list with all customers
        List<String> allCustomers = getAllCustomers();  // Assume this method retrieves all customer names
        for (String customer : allCustomers) {
            listModel.addElement(customer);
        }

        // Add a document listener to the search field to filter the list as the user types
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterList();
            }

            private void filterList() {
                String searchText = searchField.getText().toLowerCase();
                listModel.clear();
                for (String customer : allCustomers) {
                    if (customer.toLowerCase().contains(searchText)) {
                        listModel.addElement(customer);
                    }
                }
            }
        });

        // Add a ListSelectionListener to the customerList to trigger displayInvoices when a customer is selected
        customerList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {  // Only handle the event once, not twice
                    String selectedCustomer = customerList.getSelectedValue();
                    if (selectedCustomer != null) {  // Check if a customer is actually selected
                        displayInvoices(selectedCustomer);
                    }
                }
            }
        });
    }

    private List<String> getAllCustomers() {
        ArrayList<String> fileData = FileHandling.WholeFileRead("Customers");
        List<String> customerNames = new ArrayList<>();
        for (String line : fileData) {
            String[] splitLine = line.split(",");
            customerNames.add(splitLine[0]);
        }
        return customerNames;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InvoiceGUI().setVisible(true);
        });
    }

    private void displayInvoices(String customerName) {
        List<Invoice> invoices = invoiceManager.getInvoicesByCustomer(customerName);
        System.out.println("displayInvoices called with customerName: " + customerName);
        System.out.println("Number of invoices found: " + invoices.size());
        StringBuilder invoiceText = new StringBuilder();
        for (Invoice invoice : invoices) {
            invoiceText.append(invoice.toString()).append("\n");  // Assuming Invoice has a meaningful toString method
        }
        // Assume you have a JTextArea or other component to display invoices
        invoiceTextArea.setText(invoiceText.toString());
    }
}
