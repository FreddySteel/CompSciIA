import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        invoiceManagers manager = new invoiceManagers();

        while (true){
            System.out.println("Hello, To take orders (1) To print invoices (2) To display ... (3) ");
            Integer input = scanner.nextInt();
            if(input == 1){
                ArrayList<String> order = Orders.takeOrder();
                Invoice invoice = Invoice.invoiceGenerator(order);
                manager.addInvoice(invoice);
            } else if(input == 2){
                manager.getAllInvoices();
            } else if(input == 3){
                System.out.println("To display Inventory (1) To display Customers (2)");
                Integer input3 = scanner.nextInt();
                if(input3 == 1){
                    FileHandling.displayStock("Inventory");
                } else if(input3 == 2){
                    FileHandling.displayStock("Customers");
                } else {
                    System.out.println("Invalid input, please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input, please enter 1, 2 or 3.");
            }
        }
    }
}