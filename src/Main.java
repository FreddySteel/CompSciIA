import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        invoiceManagers manager = new invoiceManagers(); // Create the invoiceManagers object outside the while loop

        while (true) {
            System.out.println("Hello! Please select an option:");
            System.out.println("1. Take orders");
            System.out.println("2. Print invoices");
            System.out.println("3. Display inventory or customer information");
            System.out.print("Your choice: ");
            int input = scanner.nextInt();

            if (input == 1) {
                System.out.println("Taking orders...");
                Order.takeOrder(manager);
            } else if (input == 2) {
                System.out.println("Printing invoices...");
                manager.getAllInvoices();
                manager.writeInvoicesToFile();
            } else if (input == 3) {
                System.out.println("Displaying information...");
                System.out.println("1. Display inventory");
                System.out.println("2. Display customers");
                System.out.print("Your choice: ");
                int input3 = scanner.nextInt();
                if (input3 == 1) {
                    FileHandling.displayStock("Inventory");
                } else if (input3 == 2) {
                    FileHandling.displayStock("Customers");
                } else {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter 1, 2, or 3.");
            }
        }
    }
}
