import java.util.ArrayList;
import java.util.Scanner;
public class Order {

    public static ArrayList<String> takeOrder() {
        Scanner scanner = new Scanner(System.in);
        String[] customers = FileHandling.fileToArray("Customers", FileHandling.numOfLines("Customers"));
        String[] products = stockList.productsInStock();
        ArrayList<String> lastOrder = null;

        for (String customer : customers) {
            // Reset order for new customer
            ArrayList<String> order = new ArrayList<>();// ArrayList for current customer's order

            String[] customerInfo = customer.split(",");
            String name = customerInfo[0];
            String phone = customerInfo[1];

            order.add(name);
            order.add(phone);
            // Add customer name to order list

            System.out.println("Customer: " + name + " Phone: " + phone);
            for (int i = 0; i < products.length; i++) {
                System.out.print("How many " + products[i] + " do you want? ");
                int quantity = scanner.nextInt();
                order.add(products[i] + " " + quantity); // Add product and quantity to order list
            }

            System.out.println(name + " ordered:");
            System.out.println(order);
            lastOrder = order; // Keep track of the last order

//            Invoice invoice = Invoice.invoiceGenerator(order);
//            invoiceManager.addInvoice(invoice);

            System.out.println("Do you want to take another order? (Y/N)");
            String input = scanner.next();
            if (input.equalsIgnoreCase("N")) {
                break; // Exit the for-loop
            }
        }
        return lastOrder;
    }
}