
import java.util.ArrayList;

public class stockList {
    // display the stocklist


    public static String[] productsInStock() {
        int k= 0;
        String[] products = new String[FileHandling.numOfLines("Inventory")];
        //String[] list = FileHandling.fileToArray("Inventory", FileHandling.numOfLines("Inventory"));
        String[] list =FileHandling.fileToArray("Inventory",FileHandling.numOfLines("Inventory"));
        for (String product : list) {
            String[] line = product.split(",");
            String prod = line[1];
            products[k]=prod;
            k++;
        }
        return products;
    }
}
