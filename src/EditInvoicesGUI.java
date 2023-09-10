import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditInvoicesGUI extends JFrame {
    private JComboBox<String> invoiceDropdown;
    private JComboBox<String> itemDropdown;
    private JTextField quantityField;
    private JButton editButton, deleteButton, addButton, saveButton;

    public EditInvoicesGUI() {
        setTitle("Edit Invoices");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        ArrayList<String> invoiceNames = getInvoiceNames();
        invoiceDropdown = new JComboBox<>(invoiceNames.toArray(new String[0]));
        add(invoiceDropdown);

        itemDropdown = new JComboBox<>();
        add(itemDropdown);

        quantityField = new JTextField(10);
        add(quantityField);

        editButton = new JButton("Edit");
        editButton.addActionListener(e -> editInvoice());
        add(editButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteInvoice());
        add(deleteButton);

        addButton = new JButton("Add");
        // addButton.addActionListener(new AddAction());   // Implement this if you want a separate add feature
        add(addButton);

        saveButton = new JButton("Save");
        // saveButton.addActionListener(new SaveAction()); // Implement this if you need to save changes manually, else, changes are saved immediately after edits.
        add(saveButton);

        pack();
        setLocationRelativeTo(null);
    }

    private ArrayList<String> getInvoiceNames() {
        ArrayList<String> invoices = FileHandling.WholeFileRead("Invoices");
        ArrayList<String> invoiceNames = new ArrayList<>();
        Pattern pattern = Pattern.compile("Invoice for (.+)");
        for (String line : invoices) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                invoiceNames.add(matcher.group(1));  // Add only customer name for display
            }
        }
        return invoiceNames;
    }

    private void editInvoice() {
        String selectedCustomer = (String) invoiceDropdown.getSelectedItem();
        if (selectedCustomer == null) {
            return;
        }

        ArrayList<String> invoices = FileHandling.WholeFileRead("Invoices");
        StringBuilder invoiceData = new StringBuilder();
        boolean record = false;
        for (String line : invoices) {
            if (line.contains("Invoice for " + selectedCustomer)) {
                record = true;
            }
            if (record) {
                invoiceData.append(line).append("\n");
                if (line.startsWith("Total Cost:")) {
                    record = false;
                }
            }
        }

        JTextArea invoiceTextArea = new JTextArea(invoiceData.toString(), 20, 40);
        int result = JOptionPane.showConfirmDialog(this, new JScrollPane(invoiceTextArea),
                "Edit Invoice", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Validate and save the changes here
        }
    }

    private void deleteInvoice() {
        String selectedCustomer = (String) invoiceDropdown.getSelectedItem();
        if (selectedCustomer == null) {
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "<html><font color='red'><b>WARNING:</b></font> Are you sure you want to delete the invoice for " + selectedCustomer + "?",
                "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            ArrayList<String> invoices = FileHandling.WholeFileRead("Invoices");
            boolean removing = false;
            for (int i = 0; i < invoices.size(); i++) {
                if (invoices.get(i).contains("Invoice for " + selectedCustomer)) {
                    removing = true;
                }
                if (removing) {
                    invoices.remove(i);
                    i--;
                    if (invoices.get(i).startsWith("Total Cost:")) {
                        removing = false;
                    }
                }
            }
            FileHandling.overwriteFile("Invoices", invoices);  // Assuming this method exists
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EditInvoicesGUI().setVisible(true);
        });
    }
}
