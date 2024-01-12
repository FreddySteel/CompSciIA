public class stockList {
    public static String[] productsInStock() {
        int k = 0;
        String[] products = new String[FileHandling.numOfLines("Inventory.txt")];
        //String[] list = FileHandling.fileToArray("Inventory.txt", FileHandling.numOfLines("Inventory.txt"));
        String[] list = FileHandling.fileToArray("Inventory.txt", FileHandling.numOfLines("Inventory.txt"));
        for (String product : list) {
            String[] line = product.split(",");
            String prod = line[1];
            products[k] = prod;
            k++;
        }

        return products;
    }

    public static double getProductPrice(String productName) {
        String[] inventory = FileHandling.fileToArray("Inventory.txt", FileHandling.numOfLines("Inventory.txt"));
        for (String productInfo : inventory) {
            String[] info = productInfo.split(",");
            // Check if the array has at least 3 elements (stock, product, price)
            if (info.length >= 3 && info[1].equals(productName)) {
                try {
                    return Double.parseDouble(info[2]);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing price for product " + productName);
                    return -1; // or handle the error as appropriate
                }
            }
        }
        return -1;  // Return -1 or throw an exception if product not found
    }
}

