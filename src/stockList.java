import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class stockList {
    // display the stocklist

    public static void displayStock() {
        System.out.println("Amount Product");

        ArrayList list = FileHandling.WholeFileRead("Inventory");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            System.out.println("");

        }
    }

    public static String[] productsInStock() {
        int k= 0;
        String[] products = new String[FileHandling.numOfLines("Inventory")];
        //String[] list = FileHandling.fileToArray("Inventory", FileHandling.numOfLines("Inventory"));
        String[] list =FileHandling.fileToArray("Inventory",3);
        for (String product : list) {
            String[] line = product.split(",");
            String prod = line[1];
            products[k]=prod;
            k++;
        }
        return products;
    }
}
