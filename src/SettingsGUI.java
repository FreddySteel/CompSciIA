import javax.swing.*;
import java.awt.*;

public class SettingsGUI extends JPanel {
    private JButton editCustomersButton;
    private JButton editInventoryButton;
    private JButton editInvoicesButton;

    public SettingsGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create buttons with reduced size
        editCustomersButton = createButton("Edit Customers");
        editInventoryButton = createButton("Edit Inventory");
        editInvoicesButton = createButton("Edit Invoices");

        // Add buttons to panel
        add(Box.createVerticalGlue());
        add(editCustomersButton);
        add(Box.createVerticalStrut(100)); // Adds vertical space between buttons
        add(editInventoryButton);
        add(Box.createVerticalStrut(100)); // Adds vertical space between buttons
        add(editInvoicesButton);
        add(Box.createVerticalGlue());
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(300, 500)); // Sets max width and height
        button.setAlignmentX(CENTER_ALIGNMENT); // Center alignment
        return button;
    }
}