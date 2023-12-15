import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SettingsGUI extends JPanel implements ActionListener {
    private JButton editCustomersButton;
    private JButton editInventoryButton;
    private JButton editInvoicesButton;

    public SettingsGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create buttons with reduced size
        editCustomersButton = createButton("Edit Customers.txt");
        editInventoryButton = createButton("Edit Inventory.txt");
        editInvoicesButton = createButton("Edit Invoices.txt");

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
        if (e.getActionCommand().equals("Edit Customers.txt")) {
            File file = new File("Customers.txt");
            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            new EditCustomersGUI().setVisible(true);
        } else if (e.getActionCommand().equals("Edit Inventory.txt")) {
            File file = new File("Inventory.txt");
            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            new EditInventoryGUI().setVisible(true);
        } else if (e.getActionCommand().equals("Edit Invoices.txt")) {
            File file = new File("Invoices.txt");
            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            new EditInvoicesGUI().setVisible(true);
        }// Make this a method !!!
    }
}
