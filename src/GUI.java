import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class GUI extends JPanel implements ActionListener {
    JButton button1;
    JButton button2;
    JButton button3;
    JLabel title, subTitle;
    JButton buttonEditInvoice;

    public GUI(int width, int height) {
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
        add(button1);
        button1.addActionListener(this);
        add(button2);
        button2.addActionListener(this);
        add(button3);
        button3.addActionListener(this);
        buttonEditInvoice = new JButton("Edit Invoice");
        buttonEditInvoice.setBounds(220,340, 160, 55);
        add(buttonEditInvoice);
        buttonEditInvoice.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Stock List")) {
            ArrayList<String> stockList = FileHandling.WholeFileRead("Inventory");
            StringBuilder output = new StringBuilder("Product, Quantity, Price\n");
            for (String item : stockList) {
                output.append(item).append("\n");
            }
            JTextArea textArea = new JTextArea(output.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(null, scrollPane, "Stock List", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getActionCommand().equals("Invoices")) {
            ArrayList<String> invoices = FileHandling.WholeFileRead("Invoices");
            StringBuilder output = new StringBuilder();
            for (String invoice : invoices) {
                output.append(invoice).append("\n");
            }

            JTextArea textArea = new JTextArea(output.toString());
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 500)); // You can adjust these dimensions as needed

            JOptionPane.showMessageDialog(null, scrollPane, "Invoices", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (e.getActionCommand().equals("Edit Invoice")) {
            String customerName = JOptionPane.showInputDialog("Enter the customer's name:");
            String oldProductName = JOptionPane.showInputDialog("Enter the name of the product to be replaced:");
            String newProductName = JOptionPane.showInputDialog("Enter the new product's name:");
            double newProductPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter the new product's price:"));

            Product oldProduct = new Product(oldProductName, -1);  // Price doesn't matter for old product
            Product newProduct = new Product(newProductName, newProductPrice);
            Invoice.updateInvoiceInFile(customerName, oldProduct, newProduct);
        }
    }
}