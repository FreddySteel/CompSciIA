import java.util.ArrayList;
import java.util.Scanner;

public class InvoiceGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] customers = FileHandling.fileToArray("Customers",FileHandling.numOfLines("Customers"));
        String[] products = stockList.productsInStock();
        int[] quantities = new int[FileHandling.numOfLines("Inventory")];

        for (String customer : customers) {
            String[] customerInfo = customer.split(",");
            String name = customerInfo[0];
            String phone = customerInfo[1];

            System.out.println("Customer: " + name + " Phone: " + phone);

            for (int i = 0; i < products.length; i++) {
                System.out.print("How many " + products[i] + " do you want? ");
                quantities[i] = scanner.nextInt();
            }

            System.out.println("You ordered:");
            for (int i = 0; i < products.length; i++) {
                System.out.println(quantities[i] + " " + products[i]);
            }

            double totalPrice = 0;
            System.out.println("\nInvoice:");
            System.out.println("Customer: " + name);
            System.out.println("Phone: " + phone);
            for (int i = 0; i < products.length; i++) {
                if (quantities[i] > 0) {
                    String[] line = stockList.getProductInfo(products[i]);
                    double price = Double.parseDouble(line[2]);
                    double subtotal = price * quantities[i];
                    totalPrice += subtotal;
                    System.out.println(quantities[i] + " " + products[i] + " @ $" + price + " each: $" + subtotal);
                }
            }
            System.out.println("Total: $" + totalPrice);
            System.out.println("");
        }

        scanner.close();
    }
}