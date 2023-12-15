import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        invoiceManagers manager = new invoiceManagers("Invoices.txt");
        System.out.println("SEQUENCE: Program started");
        JFrame frame = new JFrame("Demo frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI myGUI = new GUI(600, 400, manager); // passing the manager to GUI
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }
}
