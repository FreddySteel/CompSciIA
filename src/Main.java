import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        boolean repeat =true;
        boolean repeat2 = true;
        boolean repeat3 =true;
        System.out.println("Hello, To take orders (1) To edit files (2) To display ... (3) ");
        Integer input = scanner.nextInt();
        Integer input2;
        Integer input3;
        while (repeat){
            if (input == 1) {
                ArrayList<ArrayList<String>> AllOrders = Orders.takeOrder();
                repeat= false;
            }
            if (input == 2){
                while (repeat2){
                    System.out.println("To edit Customers (1) To edit Inventory (2)");
                    input2 = scanner.nextInt();
                    if (input2 == 1){
                        // method to edit the file Customers
                    }
                    if(input2 == 2) {
                        // method to edit the file inventory

                    }
                }
            }
            if (input == 3){
                while(repeat3) {
                    System.out.println("To display Inventory (1) To display Customers (2)");
                    input3 = scanner.nextInt();
                    if (input3 == 1) {
                        FileHandling.displayStock("Inventory");
                        repeat3 = false;
                    }
                    if (input3 == 2) {
                        FileHandling.displayStock("Customers");
                        repeat3 = false;
                    }
                }
            }
        }
    }
}