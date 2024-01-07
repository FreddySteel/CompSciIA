import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

public class InvoiceGUI extends JFrame {
    private JTextField searchField;
    JList<String> customerList;
    private DefaultListModel<String> listModel;
    private JTextArea invoiceTextArea;
    private JPanel mainPanel, invoicePanel;
    private invoiceManagers invoiceManager;
    private List<String> allCustomers; // Stores all customer names

    public InvoiceGUI() {
        invoiceManager = new invoiceManagers();
        invoiceManager.loadInvoicesFromFile("Invoices.txt");
        setTitle("Invoice GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load all customers
        allCustomers = getAllCustomers();

        // Main panel for customer list
        mainPanel = new JPanel(new BorderLayout());

        // Label for customer list
        JLabel customersLabel = new JLabel("Customers");
        mainPanel.add(customersLabel, BorderLayout.NORTH);

        // Search field setup
        searchField = new JTextField("Search customer names", 20);
        searchField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search customer names")) {
                    searchField.setText("");
                }
            }
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search customer names");
                }
            }
        });
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                filterList();
            }
            public void removeUpdate(DocumentEvent e) {
                filterList();
            }
            public void insertUpdate(DocumentEvent e) {
                filterList();
            }
        });
        mainPanel.add(searchField, BorderLayout.SOUTH);

        // Customer list setup
        listModel = new DefaultListModel<>();
        customerList = new JList<>(listModel);
        populateCustomerList();
        JScrollPane listScrollPane = new JScrollPane(customerList);
        mainPanel.add(listScrollPane, BorderLayout.CENTER);

        // Invoice panel setup
        invoicePanel = new JPanel(new BorderLayout());
        invoiceTextArea = new JTextArea();
        invoicePanel.add(new JScrollPane(invoiceTextArea), BorderLayout.CENTER);

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> switchToMainPanel());
        invoicePanel.add(returnButton, BorderLayout.SOUTH);

        // Adding main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Selection listener for customer list
        customerList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedCustomer = customerList.getSelectedValue();
                if (selectedCustomer != null) {
                    displayInvoices(selectedCustomer);
                    switchToInvoicePanel();
                }
            }
        });

        setVisible(true);
    }
    public void addToInvoicePanel(Component component, Object constraints) {
        invoicePanel.add(component, constraints);
    }
    private void populateCustomerList() {
        listModel.clear();
        for (String customer : allCustomers) {
            listModel.addElement(customer);
        }
    }
    public void deleteInvoicesForCustomer(String customerName) {
        invoiceManager.deleteInvoicesForCustomer(customerName);
        // You may need to implement this method in invoiceManagers
    }
    private void filterList() {
        String search = searchField.getText().toLowerCase();
        listModel.clear();
        for (String customer : allCustomers) {
            if (customer.toLowerCase().contains(search)) {
                listModel.addElement(customer);
            }
        }
    }

    void switchToInvoicePanel() {
        remove(mainPanel);
        add(invoicePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void switchToMainPanel() {
        remove(invoicePanel);
        add(mainPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    void displayInvoices(String customerName) {
        List<Invoice> invoices = invoiceManager.getInvoicesByCustomer(customerName);
        StringBuilder invoiceText = new StringBuilder();
        int invoiceCount = 1;
        for (Invoice invoice : invoices) {
            invoiceText.append("Invoice ").append(invoiceCount++).append(":\n")
                    .append(invoice.toString()).append("\n\n");
        }
        invoiceTextArea.setText(invoiceText.toString());
    }

    private List<String> getAllCustomers() {
        ArrayList<String> fileData = FileHandling.WholeFileRead("Customers.txt");
        List<String> customerNames = new ArrayList<>();
        for (String line : fileData) {
            String[] splitLine = line.split(",");
            customerNames.add(splitLine[0]);
        }
        return customerNames;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InvoiceGUI().setVisible(true));
    }
}