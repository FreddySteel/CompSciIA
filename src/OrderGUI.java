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
    private int currentCustomerIndex;
    private Consumer<Object[]> onClosedCallback;

    public OrderGUI(int currentCustomerIndex, String[][] customerData, String[] products, invoiceManagers manager, NextCustomerOrderCallback callback) {
        this.currentCustomerIndex = currentCustomerIndex;
        this.onClosedCallback = args -> {
            String[] productsArray = (String[]) args[0];
            int updatedIndex = (Integer) args[1];
            callback.show(productsArray, updatedIndex);
        };
        this.customers = customerData;  // Assign customerData to customers
        this.products = products;
        this.manager = manager;


        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Initialize the JLabel named customerInfo with customerData
        customerInfo = new JLabel("Customer: " + this.customers[0][0] + " Phone: " + this.customers[0][1]);

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

        next.addActionListener(e -> {
            System.out.println("Next button pressed for customer index: " + currentCustomerIndex);
            submitOrder();
            //onClosedCallback.accept(products);
              // Close this OrderGUI
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Window closing for customer index: " + currentCustomerIndex);
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
        System.out.println("submitOrder called for customer index: " + currentCustomerIndex);
        if (!isValidOrder() || currentCustomerIndex >= customers.length) {
            return;
        }
        ArrayList<String> order = new ArrayList<>();
        order.add(customers[currentCustomerIndex][0]);
        order.add(customers[currentCustomerIndex][1]);

        for (int i = 0; i < products.length; i++) {
            String product = products[i];
            String quantity = quantities[i].getText().trim();

            order.add(product + " " + quantity);
        }
        currentCustomerIndex++;  // Move to the next customer
        if (currentCustomerIndex < customers.length) {
            onClosedCallback.accept(new Object[] {products, currentCustomerIndex});  // Pass currentCustomerIndex to the GUI class
        } else {
            this.dispose();  // Close GUI when all customers are processed
        }
        Invoice invoice = Invoice.invoiceGenerator(order);
        manager.addInvoice(invoice);
        manager.writeInvoicesToFile(); //saves the invoices to a file
        onClosedCallback.accept(products);
        System.out.println("submitOrder finished for customer index: " + currentCustomerIndex);

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
        void show(String[] products, int updatedIndex);
    }
}
