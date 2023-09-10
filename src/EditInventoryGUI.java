import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditInventoryGUI extends JFrame {

    private JList<String> inventoryList;
    private JButton addButton;
    private JButton editButton;
    private JButton removeButton;
    private DefaultListModel<String> listModel;

    public EditInventoryGUI() {
        setTitle("Edit Inventory");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listModel = new DefaultListModel<>();

        // Populate the JList with current inventory items
        for (String product : stockList.productsInStock()) {
            listModel.addElement(product);
        }

        inventoryList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(inventoryList);

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        removeButton = new JButton("Remove");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newProduct = JOptionPane.showInputDialog("Enter new product name:");
                if (newProduct != null && !newProduct.trim().isEmpty()) {
                    double price = Double.parseDouble(JOptionPane.showInputDialog("Enter product price:"));
                    // Add product to the inventory (File)
                    // ... Write code to add to inventory
                    listModel.addElement(newProduct);
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = inventoryList.getSelectedValue();
                if (selectedItem != null) {
                    String newProduct = JOptionPane.showInputDialog("Edit product name:", selectedItem);
                    if (newProduct != null && !newProduct.trim().isEmpty()) {
                        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter product price:", stockList.getProductPrice(selectedItem)));
                        // Update product in the inventory (File)
                        // ... Write code to update inventory
                        listModel.setElementAt(newProduct, inventoryList.getSelectedIndex());
                    }
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = inventoryList.getSelectedValue();
                if (selectedItem != null) {
                    // Remove product from the inventory (File)
                    // ... Write code to remove from inventory
                    listModel.removeElement(selectedItem);
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center window on screen
    }

    // Assuming you'll add this method in your SettingsGUI actionPerformed method
    public void showGUI() {
        this.setVisible(true);
    }

    // ... rest of the class
}
