import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderGUI extends JFrame {
    private JTextField[] quantities;
    private JLabel customerInfo;
    private invoiceManagers manager;
    private String[] products;
    private String[][] customers;
    private int currentCustomerIndex = 0;

    public OrderGUI(String[][] customers, String[] products, invoiceManagers manager) {
        this.customers = customers;
        this.products = products;
        this.manager = manager;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        customerInfo = new JLabel("Customer: " + customers[currentCustomerIndex][0] + " Phone: " + customers[currentCustomerIndex][1]);
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
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitOrder();
                currentCustomerIndex++;
                if (currentCustomerIndex < customers.length) {
                    updateCustomer();
                } else {
                    dispose();
                }
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
        ArrayList<String> order = new ArrayList<>();
        order.add(customers[currentCustomerIndex][0]);
        order.add(customers[currentCustomerIndex][1]);

        Invoice invoice = Invoice.invoiceGenerator(order);
        manager.addInvoice(invoice);
    }
}
