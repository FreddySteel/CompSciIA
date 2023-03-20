import java.util.ArrayList;

public class database {
    //Displays the product and number of them
    public void Inventory() {

        //String line = br.readLine();
        while (line != null) {
            String[] parts = line.split(",");
            int number = Integer.parseInt(parts[0]);
            String name = parts[1];
            System.out.println("There is " + name + " of " + number);
        }
    }
}
