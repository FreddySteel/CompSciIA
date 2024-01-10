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
        String productName = inventoryList.getSelectedValue();
        if (productName == null) {
            JOptionPane.showMessageDialog(null, "Please select a product from the list.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newProduct = JOptionPane.showInputDialog("Enter new product name:");
                if (newProduct != null && !newProduct.trim().isEmpty()) {
                    try {
                        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter product price:"));
                        int stockLevel = Integer.parseInt(JOptionPane.showInputDialog("Enter stock level:"));
                        addProduct(newProduct, price, stockLevel);
                        listModel.addElement(newProduct + " - " + stockLevel + " units");
                        FileHandling.removeEmptyLines("inventory.txt");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid price or stock level. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
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
                        try {
                            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter product price:"));
                            int stockLevel = Integer.parseInt(JOptionPane.showInputDialog("Enter new stock level:"));
                            updateProduct(oldProductName, newProduct, price, stockLevel);
                            listModel.setElementAt(newProduct, inventoryList.getSelectedIndex());
                            FileHandling.removeEmptyLines("inventory.txt");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid price or stock level. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
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
        try {
            String productLine = stockLevel + "," + productName + "," + price;
            FileHandling.WriteToFile("inventory.txt", productLine, true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updateProduct(String oldProductName, String newProductName, double newPrice, int newStockLevel) {
        try {
            ArrayList<String> products = FileHandling.WholeFileRead("inventory.txt");
            boolean productFound = false;

            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).contains("," + oldProductName + ",")) {
                    String updatedProductLine = newStockLevel + "," + newProductName + "," + newPrice;
                    products.set(i, updatedProductLine);
                    productFound = true;
                    break;
                }
            }

            if (productFound) {
                FileHandling.overwriteFile("inventory.txt", products);
            } else {
                JOptionPane.showMessageDialog(null, "Product not found: " + oldProductName, "Update Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error updating product: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeProduct(String productName) {
        try {
            ArrayList<String> products = FileHandling.WholeFileRead("inventory.txt");
            boolean productFound = products.removeIf(product -> product.contains("," + productName + ","));

            if (productFound) {
                FileHandling.overwriteFile("inventory.txt", products);
            } else {
                JOptionPane.showMessageDialog(null, "Product not found: " + productName, "Removal Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error removing product: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
