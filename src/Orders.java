
import java.util.Scanner;

public class Orders{
    //go through all the customers
    //display phone number and a user input
    //the user inputs product and how many
    //then goes on to the next customer
    //repeats until all customers done

    public static void takeOrder(){
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

        System.out.println("");
    }

        scanner.close();
    }
}
