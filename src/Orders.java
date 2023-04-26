import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Orders {
    public static ArrayList<ArrayList<String>> takeOrder() {
        ArrayList<ArrayList<String>> orders = new ArrayList<>(); // 2D ArrayList for all orders
        Scanner scanner = new Scanner(System.in);
        String[] customers = FileHandling.fileToArray("Customers", FileHandling.numOfLines("Customers"));
        String[] products = stockList.productsInStock();

        for (String customer : customers) {
            ArrayList<String> order = new ArrayList<>(); // ArrayList for current customer's order
            String[] customerInfo = customer.split(",");
            String name = customerInfo[0];
            String phone = customerInfo[1];

            order.add(name); // Add customer name to order list

            System.out.println("Customer: " + name + " Phone: " + phone);
            for (int i = 0; i < products.length; i++) {
                System.out.print("How many " + products[i] + " do you want? ");
                int quantity = scanner.nextInt();
                order.add(products[i] + " " + quantity); // Add product and quantity to order list
            }

            System.out.println(name + " ordered:");
            System.out.println(order);
            orders.add(order); // Add customer's order list to main order ArrayList
        }

        // Print out all orders
        for (ArrayList<String> order : orders) {
            System.out.println(order);
        }
        System.out.println(orders);
        return orders;
    }
}