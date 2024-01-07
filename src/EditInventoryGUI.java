import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
                    int stockLevel = Integer.parseInt(JOptionPane.showInputDialog("Enter stock level:"));
                    addProduct(newProduct, price, stockLevel);
                    listModel.addElement(newProduct + " - " + stockLevel + " units"); // Update listModel format if needed
                    FileHandling.removeEmptyLines("inventory.txt");
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldProductName = inventoryList.getSelectedValue();
                if (oldProductName != null) {
                    String newProduct = JOptionPane.showInputDialog("Edit product name:", oldProductName);
                    if (newProduct != null && !newProduct.trim().isEmpty()) {
                        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter product price:"));
                        int stockLevel = Integer.parseInt(JOptionPane.showInputDialog("Enter new stock level:"));
                        updateProduct(oldProductName, newProduct, price, stockLevel);
                        listModel.setElementAt(newProduct, inventoryList.getSelectedIndex());
                        FileHandling.removeEmptyLines("inventory.txt");
                    }
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = inventoryList.getSelectedValue();
                if (productName != null) {
                    removeProduct(productName);
                    listModel.removeElement(productName);
                    FileHandling.removeEmptyLines("inventory.txt");
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

    public void showGUI() {
        this.setVisible(true);
    }

    private void addProduct(String productName, double price, int stockLevel) {
        String productLine = stockLevel + "," + productName + "," + price; // Format: stockLevel,productName,price
        FileHandling.WriteToFile("inventory.txt", productLine, true); // Append to file
    }
    private void updateProduct(String oldProductName, String newProductName, double newPrice, int newStockLevel) {
        ArrayList<String> products = FileHandling.WholeFileRead("inventory.txt");
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).contains("," + oldProductName + ",")) {
                String updatedProductLine = newStockLevel + "," + newProductName + "," + newPrice;
                products.set(i, updatedProductLine);
                break;
            }
        }
        FileHandling.overwriteFile("inventory.txt", products);
    }

    private void removeProduct(String productName) {
        ArrayList<String> products = FileHandling.WholeFileRead("inventory.txt");
        products.removeIf(product -> product.contains("," + productName + ","));
        FileHandling.overwriteFile("inventory.txt", products);
    }
}
