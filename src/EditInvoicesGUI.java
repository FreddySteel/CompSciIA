import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditInvoicesGUI extends InvoiceGUI {
    private JButton deleteInvoiceButton;
    private String currentlySelectedCustomer;

    public EditInvoicesGUI() {
        super(); // Initialize the InvoiceGUI components

        // Add buttons for editing and deleting invoices
        JPanel editPanel = new JPanel();
        deleteInvoiceButton = new JButton("Delete Invoice");
        deleteInvoiceButton.addActionListener(this::deleteInvoice);

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
        // Implementation for editing an invoice goes here
        // Example: Open a dialog to edit invoice details

    private void deleteInvoice(ActionEvent e) {
        if (currentlySelectedCustomer == null) {
            JOptionPane.showMessageDialog(this, "No customer selected for deletion.", "Delete Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete invoices for " + currentlySelectedCustomer + "?",
                "Delete Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Use public method from InvoiceGUI
                deleteInvoicesForCustomer(currentlySelectedCustomer);
                JOptionPane.showMessageDialog(this, "Invoices deleted successfully.", "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error deleting invoices: " + ex.getMessage(), "Deletion Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditInvoicesGUI().setVisible(true));
    }
}