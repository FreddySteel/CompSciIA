import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

public class OrderGUI extends JFrame {
    private JTextField[] quantities;
    private JLabel customerInfo;
    private invoiceManagers manager;
    private String[] products;
    private String[][] customers;
    private int currentCustomerIndex = 0;
    private Consumer<String[]> onClosedCallback;

    public OrderGUI(String[][] customerData, String[] products, invoiceManagers manager, NextCustomerOrderCallback callback) {
        this.onClosedCallback = callback::show;  // Assign the callback to onClosedCallback
        this.customers = customerData;  // Assign customerData to customers
        this.products = products;
        this.manager = manager;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Initialize the JLabel named customerInfo with customerData
        customerInfo = new JLabel("Customer: " + this.customers[currentCustomerIndex][0] + " Phone: " + this.customers[currentCustomerIndex][1]);
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

        JButton next = new JButton("Next \u2192");

        next.addActionListener(e -> {
            submitOrder();
            onClosedCallback.accept(products);
            this.dispose();  // Close this OrderGUI
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onClosedCallback.accept(products);
            }
        });

        JPanel nextPanel = new JPanel();
        nextPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        nextPanel.add(next);
        panel.add(nextPanel);

        this.add(panel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    private void updateCustomer() {
        customerInfo.setText("Customer: " + customers[currentCustomerIndex][0] + " Phone: " + customers[currentCustomerIndex][1]);
        for (JTextField quantity : quantities) {
            quantity.setText("");
        }
    }

    private void submitOrder() {
        if (!isValidOrder()) {
            return;
        }
        ArrayList<String> order = new ArrayList<>();
        order.add(customers[currentCustomerIndex][0]);
        order.add(customers[currentCustomerIndex][1]);

        for (int i = 0; i < products.length; i++) {
            String product = products[i];
            String quantity = quantities[i].getText().trim();

            order.add(product);
            order.add(quantity);
        }

        Invoice invoice = Invoice.invoiceGenerator(order);
        manager.addInvoice(invoice);
    }
    private boolean isValidOrder() {
        for (JTextField quantity : quantities) {
            String text = quantity.getText().trim();
            if (!text.matches("\\d+")) { // Checks if the text is a valid number
                JOptionPane.showMessageDialog(this, "Please enter a valid quantity for all products.");
                return false;
            }
        }
        return true;
    }
    public interface NextCustomerOrderCallback {
        void show(String[] products);
    }
}
