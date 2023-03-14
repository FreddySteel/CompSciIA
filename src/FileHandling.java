import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandling {
    public static void inventoryWholeFileRead () {
        try {
            FileReader fr = new FileReader("Inventory");
            BufferedReader br = new BufferedReader(fr);
            String line =br.readLine();
            while (line != null) {
                String[] parts=line.split(",");

                System.out.println("There is "+parts[0]+" of " + parts[1]);
                line = br.readLine();

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
