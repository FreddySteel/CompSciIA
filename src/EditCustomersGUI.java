import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditCustomersGUI extends JFrame {

    private DefaultListModel<String> customerListModel;
    private JList<String> customersList;
    private JTextField customerNameField;
    private JTextField customerPhoneField;
    private JButton addButton;
    private JButton updateButton;
    private JButton removeButton;
    private final int MAX_VISIBLE_ROWS = 10; // Define max number of visible rows without scrolling


    public EditCustomersGUI() {
        setTitle("Edit Customers.txt");
        setLayout(new BorderLayout());

        // Central Panel with the customer list
        customerListModel = new DefaultListModel<>();
        populateCustomerList();
        customersList = new JList<>(customerListModel);

        // Set visible row count
        customersList.setVisibleRowCount(Math.min(customerListModel.getSize(), MAX_VISIBLE_ROWS));

        JScrollPane listScrollPane = new JScrollPane(customersList);
        add(listScrollPane, BorderLayout.CENTER);

        // South Panel with input fields and buttons
        JPanel southPanel = new JPanel(new FlowLayout());

        // Fields for name and phone
        customerNameField = new JTextField(15);
        customerPhoneField = new JTextField(15);
        southPanel.add(new JLabel("Name:"));
        southPanel.add(customerNameField);
        southPanel.add(new JLabel("Phone:"));
        southPanel.add(customerPhoneField);

        // Buttons to add, remove, and update customers
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonActions(e);
            }
        });
        southPanel.add(addButton);

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonActions(e);
            }
        });
        southPanel.add(updateButton);

        removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonActions(e);
            }
        });
        southPanel.add(removeButton);

        add(southPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // To center the window on the screen
    }

    private void handleButtonActions(ActionEvent e) {
        String name = customerNameField.getText().trim();
        String phone = customerPhoneField.getText().trim();
        String newCustomer = name + "," + phone;
        String selectedCustomer = customersList.getSelectedValue();

        if (e.getSource() == addButton) {
            FileHandling.WriteToFile("Customers.txt", newCustomer, true);
            customerListModel.addElement(newCustomer);
        } else if (e.getSource() == updateButton && selectedCustomer != null) {
            ArrayList<String> customerData = FileHandling.WholeFileRead("Customers.txt");
            for (int i = 0; i < customerData.size(); i++) {
                if (customerData.get(i).equals(selectedCustomer)) {
                    customerData.set(i, newCustomer);
                    break;
                }
            }
            for (int i = 0; i < customerData.size(); i++) {
                FileHandling.WriteToFile("Customers.txt", customerData.get(i), i != 0);
            }
            customerListModel.removeElement(selectedCustomer);
            customerListModel.addElement(newCustomer);
        } else if (e.getSource() == removeButton && selectedCustomer != null) {
            ArrayList<String> customerData = FileHandling.WholeFileRead("Customers.txt");
            customerData.remove(selectedCustomer);
            for (int i = 0; i < customerData.size(); i++) {
                FileHandling.WriteToFile("Customers.txt", customerData.get(i), i != 0);
            }
            customerListModel.removeElement(selectedCustomer);
        }
    }

    private void populateCustomerList() {
        ArrayList<String> customerData = FileHandling.WholeFileRead("Customers.txt");
        for (String customer : customerData) {
            customerListModel.addElement(customer);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EditCustomersGUI().setVisible(true);
        });
    }
}
