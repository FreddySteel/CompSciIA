import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditInvoicesGUI extends InvoiceGUI {
    private JButton editInvoiceButton, deleteInvoiceButton;
    private String currentlySelectedCustomer;

    public EditInvoicesGUI() {
        super(); // Initialize the InvoiceGUI components

        // Add buttons for editing and deleting invoices
        JPanel editPanel = new JPanel();
        editInvoiceButton = new JButton("Edit Invoice");
        editInvoiceButton.addActionListener(this::editInvoice);
        deleteInvoiceButton = new JButton("Delete Invoice");
        deleteInvoiceButton.addActionListener(this::deleteInvoice);

        editPanel.add(editInvoiceButton);
        editPanel.add(deleteInvoiceButton);

        // Use public method to add components to the invoicePanel
        addToInvoicePanel(editPanel, BorderLayout.NORTH);

        // Override the list selection listener to track selected customer
        customerList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                currentlySelectedCustomer = customerList.getSelectedValue();
                if (currentlySelectedCustomer != null) {
                    displayInvoices(currentlySelectedCustomer);
                    switchToInvoicePanel();
                }
            }
        });
    }

    private void editInvoice(ActionEvent e) {
        // Edit invoice logic
    }

    private void deleteInvoice(ActionEvent e) {
        if (currentlySelectedCustomer != null) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete invoices for " + currentlySelectedCustomer + "?",
                    "Delete Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Use public method from InvoiceGUI
                deleteInvoicesForCustomer(currentlySelectedCustomer);
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditInvoicesGUI().setVisible(true));
    }
}