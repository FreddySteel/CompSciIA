import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditCustomersGUI extends JFrame implements ActionListener {
    private JTextField customerNameField;
    private JTextField phoneNumberField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JComboBox<String> customerList;

    public EditCustomersGUI() {
        super("Edit Customers");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        customerNameField = new JTextField(20);
        phoneNumberField = new JTextField(10);

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        addButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);

        String[] customers = FileHandling.fileToArray("Customers", FileHandling.numOfLines("Customers"));
        customerList = new JComboBox<>(customers);

        add(new JLabel("Customer Name: "));
        add(customerNameField);
        add(new JLabel("Phone Number: "));
        add(phoneNumberField);
        add(addButton);
        add(editButton);
        add(deleteButton);
        add(new JLabel("Existing Customers: "));
        add(customerList);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = customerNameField.getText();
        String phone = phoneNumberField.getText();
        String selectedCustomer = (String)customerList.getSelectedItem();
        String newCustomerName = customerNameField.getText();
        String newPhoneNumber = phoneNumberField.getText();
        String customerRecord = newCustomerName + "," + newPhoneNumber;


        if (e.getSource() == addButton) {
            String newCustomer = name + "," + phone;
            FileHandling.WriteToFile("Customers", newCustomer, true); //append new customer
            customerList.addItem(newCustomer);
        } else if (e.getSource() == editButton && selectedCustomer != null) {
            if (!newCustomerName.isBlank() && !newPhoneNumber.isBlank() && selectedCustomer != null) {
                // Create an ArrayList of all customers
                ArrayList<String> customerData = FileHandling.WholeFileRead("Customers");

                // Find and replace the selected customer with the new information
                for (int i = 0; i < customerData.size(); i++) {
                    if (customerData.get(i).equals(selectedCustomer)) {
                        customerData.set(i, customerRecord);
                        break;
                    }
                }

                // Write the updated customer list back to the file
                for (int i = 0; i < customerData.size(); i++) {
                    FileHandling.WriteToFile("Customers", customerData.get(i), i != 0); // Append if not the first item
                }

                // Update the JComboBox
                customerList.removeItem(selectedCustomer);
                customerList.addItem(customerRecord);
                customerList.setSelectedItem(customerRecord);
            }
        } else if (e.getSource() == deleteButton && selectedCustomer != null) {
            if (selectedCustomer != null) {
                // Create an ArrayList of all customers
                ArrayList<String> customerData = FileHandling.WholeFileRead("Customers");

                // Find and remove the selected customer from the ArrayList
                customerData.remove(selectedCustomer);

                // Write the updated customer list back to the file
                for (int i = 0; i < customerData.size(); i++) {
                    FileHandling.WriteToFile("Customers", customerData.get(i), i != 0); // Append if not the first item
                }

                // Remove the customer from the JComboBox
                customerList.removeItem(selectedCustomer);
            }
        }
    }
}
