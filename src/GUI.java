import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class GUI extends JPanel implements ActionListener {
    private Queue<String> customersQueue = new LinkedList<>();
    private invoiceManagers manager;
    private JButton button1, button2, button3, SettingButton;
    private JLabel title, subTitle;

    public GUI(int width, int height, invoiceManagers manager) {
        this.manager = manager;
        setPreferredSize(new Dimension(width, height));
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
        button3 = new JButton("Invoices.txt");
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
            InvoiceGUI invoiceGUI = new InvoiceGUI();
            invoiceGUI.setVisible(true);
        }
        if (e.getActionCommand().equals("Take Order")) {
            ArrayList<String> customerLines = FileHandling.WholeFileRead("Customers.txt");
            String[][] initialCustomerData = convertToCustomerDataArray(customerLines);
            String[] products = stockList.productsInStock();
            for (String[] customer : initialCustomerData) {
                if (customer.length >= 2) {
                    customersQueue.add(customer[0] + "," + customer[1]);
                }
            }
            // Create and show the OrderGUI
            OrderGUI orderGUI = new OrderGUI(initialCustomerData, products, manager, customersQueue);
            orderGUI.setVisible(true);
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
    private String[][] convertToCustomerDataArray(ArrayList<String> customerLines) {
        String[][] customerData = new String[customerLines.size()][];
        for (int i = 0; i < customerLines.size(); i++) {
            customerData[i] = customerLines.get(i).split(",");
        }
        return customerData;
    }

}
