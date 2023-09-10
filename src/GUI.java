import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;



public class GUI extends JPanel implements ActionListener {
    JButton button1;
    JButton button2;
    JButton button3;
    JLabel title, subTitle;
    JButton SettingButton;
    invoiceManagers manager;
    Queue<String> customersQueue = new LinkedList<>();
    public GUI(int width, int height, invoiceManagers manager) {
        this.manager = manager;
        System.out.println("SEQUENCE: GUI constructor");
        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);

        title = new JLabel("Main Menu");
        title.setBounds(250, 20, 200, 30);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        add(title);

        subTitle = new JLabel("Welcome, please select from the options below");
        subTitle.setBounds(140, 70, 350, 20);
        add(subTitle);

        button1 = new JButton("Take Order");
        button1.setBounds(220,130, 160, 55);
        button2 = new JButton("Stock List");
        button2.setBounds(220,200, 160, 55);
        button3 = new JButton("Invoices");
        button3.setBounds(220,270, 160, 55);
        SettingButton = new JButton("Settings");
        SettingButton.setBounds(0,0, 100, 35);
        add(button1);
        button1.addActionListener(this);
        add(button2);
        button2.addActionListener(this);
        add(button3);
        button3.addActionListener(this);
        add(SettingButton);
        SettingButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Stock List")) {
            new StockListGUI().setVisible(true);
        }
        if (e.getActionCommand().equals("Invoices")) {
            JFrame invoiceFrame = new JFrame("Invoices");
            invoiceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose this frame when closed, not exit the entire program
            InvoiceGUI invoiceGUI = new InvoiceGUI();
            invoiceFrame.add(invoiceGUI);
            invoiceFrame.pack();
            invoiceFrame.setVisible(true);

        }
        if (e.getActionCommand().equals("Take Order")) {
            String[] products = stockList.productsInStock();

            String[] customers = FileHandling.fileToArray("Customers", FileHandling.numOfLines("Customers"));

            // Populate the queue
            for (String customer : customers) {
                customersQueue.offer(customer);
            }

            showNextCustomerOrder(products);  // Kick off the sequence
        }
        if (e.getActionCommand().equals("Settings")) {
            JFrame settingsFrame = new JFrame("Settings");
            settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Set the frame size
            settingsFrame.setSize(500, 500); // Width and height in pixels

            // Center the frame
            settingsFrame.setLocationRelativeTo(null);

            SettingsGUI settingsGUI = new SettingsGUI();
            settingsFrame.add(settingsGUI);

            settingsFrame.setVisible(true);
        }
    }
    private void showNextCustomerOrder(String[] products) {
        if (!customersQueue.isEmpty()) {
            String customer = customersQueue.poll();  // Take the next customer from the queue
            String[][] customerInfo = new String[][]{{customer.split(",")[0], customer.split(",")[1]}};

            // Open the OrderGUI for the next customer.
            SwingUtilities.invokeLater(() -> new OrderGUI(customerInfo, products, manager, GUI.this::showNextCustomerOrder));
        }
        // If the queue is empty, you might want to show a message or take another action.
        else {
            JOptionPane.showMessageDialog(this, "All customer orders processed.");
        }
    }

}
