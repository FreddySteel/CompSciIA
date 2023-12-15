import java.util.ArrayList;
import java.util.Scanner;
public class Order {

    public static void takeOrder(invoiceManagers manager) {
        Scanner scanner = new Scanner(System.in);
        String[] customers = FileHandling.fileToArray("Customers.txt", FileHandling.numOfLines("Customers.txt"));
        String[] products = stockList.productsInStock();

        for (String customer : customers) {
            ArrayList<String> order = new ArrayList<>();
            String[] customerInfo = customer.split(",");
            String name = customerInfo[0];
            String phone = customerInfo[1];

            order.add(name);
            order.add(phone);

            System.out.println("Customer: " + name + " Phone: " + phone);
            for (int i = 0; i < products.length; i++) {
                System.out.print("How many " + products[i] + " do you want? ");
                int quantity = scanner.nextInt();
                order.add(products[i] + " " + quantity);
            }

            System.out.println(name + " ordered:");
            System.out.println(order);

            Invoice invoice = Invoice.invoiceGenerator(order);
            manager.addInvoice(invoice);
        }
    }
}
