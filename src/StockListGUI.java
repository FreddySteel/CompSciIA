import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StockListGUI extends JFrame {
    private JTable stockTable;
    private DefaultTableModel tableModel;

    public StockListGUI() {
        setTitle("Stock List");
        setLayout(new BorderLayout());

        String[] columnNames = {"Stock", "Product Name", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        stockTable = new JTable(tableModel);

        // Populate the table
        populateStockTable();

        // Add table to a scroll pane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(stockTable);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Center the window
    }

    private void populateStockTable() {
        ArrayList<String> stockData = FileHandling.WholeFileRead("Inventory.txt");
        for (String line : stockData) {
            String[] splitLine = line.split(",");
            String productId = splitLine[0];
            String productName = splitLine[1];
            String productPrice = splitLine[2];
            Object[] row = {productId, productName, productPrice};
            tableModel.addRow(row);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StockListGUI().setVisible(true);
        });
    }
}

