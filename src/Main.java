import javax.swing.*;
public class Main {
    public static void main(String[] args) {

        new invoiceManagers("Invoices");

        // demo GUI
        System.out.println("SEQUENCE: Program started");
        JFrame frame = new JFrame("Demo frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI myGUI = new GUI(600, 400);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }
}
   // Scanner scanner = new Scanner(System.in);
    //invoiceManagers manager = new invoiceManagers(); // Create the invoiceManagers object outside the while loop