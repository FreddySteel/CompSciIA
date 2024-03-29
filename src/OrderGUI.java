import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Queue;

public class OrderGUI extends JFrame {
    private Queue<String> customersQueue;
    private JTextField[] quantities;
    private JLabel customerInfo;
    private invoiceManagers manager;
    private String[] products;

    public OrderGUI(String[] products, invoiceManagers manager, Queue<String> customersQueue) {
        this.customersQueue = customersQueue;
        this.products = products;
        this.manager = manager;

        setupGUI();
        showNextCustomer();
    }

    private void setupGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        customerInfo = new JLabel();
        panel.add(customerInfo);

        quantities = new JTextField[products.length];
        for (int i = 0; i < products.length; i++) {
            JLabel productLabel = new JLabel("How many " + products[i] + " do you want?");
            quantities[i] = new JTextField(5);
            JPanel productPanel = new JPanel();
            productPanel.add(productLabel);
            productPanel.add(quantities[i]);
            panel.add(productPanel);
        }

        JButton next = new JButton("Next →");
        next.addActionListener(e -> processAndShowNextCustomer());

        JPanel nextPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        nextPanel.add(next);
        panel.add(nextPanel);

        add(panel, BorderLayout.CENTER);
        setSize(500, 500);
        setVisible(true);
    }

    private void processAndShowNextCustomer() {
        if (!isValidOrder()) {
            return;
        }
        submitCurrentOrder();
        showNextCustomer();
    }

    private void showNextCustomer() {
        if (customersQueue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All customer orders processed.");
            dispose();
            return;
        }

        String customer = customersQueue.poll();
        String[] customerInfo = customer.split(",");
        if (customerInfo.length != 2) {
            JOptionPane.showMessageDialog(this, "Invalid customer format: " + customer, "Error!", JOptionPane.ERROR_MESSAGE);
            showNextCustomer(); // Skip to next customer
            return;
        }
        updateCustomerDisplay(customerInfo[0], customerInfo[1]);
    }

    private void updateCustomerDisplay(String name, String phone) {
        customerInfo.setText("Customer: " + name + " Phone: " + phone);
        for (JTextField quantity : quantities) {
            quantity.setText("");
        }
    }

    private void submitCurrentOrder() {
        try {
            ArrayList<String> order = new ArrayList<>();
            order.add(customerInfo.getText());

            for (int i = 0; i < products.length; i++) {
                String product = products[i];
                String quantity = quantities[i].getText().trim();
                order.add(product + " " + quantity);
            }

            Invoice invoice = Invoice.invoiceGenerator(order);
            manager.addInvoice(invoice);
            manager.writeInvoicesToFile();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error processing order: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidOrder() {
        for (JTextField quantity : quantities) {
            String text = quantity.getText().trim();
            try {
                int qty = Integer.parseInt(text);
                if (qty < 0) {
                    JOptionPane.showMessageDialog(this, "Quantities cannot be negative.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer quantity for all products.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}