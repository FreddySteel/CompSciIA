import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceGUI extends JPanel implements ActionListener {
    private static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Whole Month"};

    public InvoiceGUI() {
        this.setPreferredSize(new Dimension(400, 300));
        setLayout(new GridLayout(2, 4)); // A grid layout for the buttons

        for (String day : DAYS) {
            JButton dayButton = new JButton(day);
            dayButton.addActionListener(this);
            add(dayButton);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String day = e.getActionCommand();
        // Here is where you handle the invoice display for each day or the whole month
        System.out.println("Display invoices for: " + day);
    }
}
