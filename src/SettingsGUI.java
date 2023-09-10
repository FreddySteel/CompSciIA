import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsGUI extends JPanel implements ActionListener {
    private JButton editCustomersButton;
    private JButton editInventoryButton;
    private JButton editInvoicesButton;

    public SettingsGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create buttons with reduced size
        editCustomersButton = createButton("Edit Customers");
        editInventoryButton = createButton("Edit Inventory");
        editInvoicesButton = createButton("Edit Invoices");

        // Add action listeners
        editCustomersButton.addActionListener(this);
        editInventoryButton.addActionListener(this);
        editInvoicesButton.addActionListener(this);

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
        button.setMaximumSize(new Dimension(300, 50)); // Sets max width and height
        button.setAlignmentX(CENTER_ALIGNMENT); // Center alignment
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Edit Customers")) {
            new EditCustomersGUI().setVisible(true);
        } else if (e.getActionCommand().equals("Edit Inventory")) {
            new EditInventoryGUI().setVisible(true);
        } else if (e.getActionCommand().equals("Edit Invoices")) {
            new EditInvoicesGUI().setVisible(true);
        }
    }
}
