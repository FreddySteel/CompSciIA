import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InvoiceViewer {
    private JFrame frame;
    private JTextArea textArea;
    private JButton loadInvoicesButton;

    public InvoiceViewer() {
        frame = new JFrame("Invoice Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        loadInvoicesButton = new JButton("Load Invoices");
        loadInvoicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadInvoices();
            }
        });

        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.getContentPane().add(BorderLayout.SOUTH, loadInvoicesButton);

        frame.setVisible(true);
    }

    private void loadInvoices() {
        ArrayList<String> lines = FileHandling.WholeFileRead("Invoices.txt");
        String text = String.join("\n", lines);
        textArea.setText(text);
    }

    public static void main(String[] args) {
        new InvoiceViewer();
    }
}