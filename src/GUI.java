import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

public class GUI extends JPanel implements ActionListener {
    JButton button1;
    JButton button2;
    JButton button3;
    JLabel title, subTitle;
    JButton SettingButton;

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
            JTextArea textArea = new JTextArea(output.toString());
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 500)); // You can adjust these dimensions as needed

            JOptionPane.showMessageDialog(null, scrollPane, "Invoices", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}