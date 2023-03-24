import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandling {
    public static ArrayList<String> WholeFileRead(String Filename) {
        try {
            FileReader fr = new FileReader(Filename);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> data = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                data.add(line);
                line = br.readLine();
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int numOfLines(String filename) {
        //Open the file.
        //Read line by line, and increases count + 1 each line.
        //Close the file.
        //Read the count.
        try {
            Scanner line = new Scanner(new File(filename));
            int numLines = 0;
            while (line.hasNextLine()) {
                line.nextLine();
                numLines++;
            }
            line.close();
            return numLines;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String[] fileToArray(String filename, int numLines) {
        try {

            Scanner line ;// = //new Scanner(new File(filename));
            String[] data = new String[numLines];
            line = new Scanner(new File(filename));
            int i = 0;
            while (line.hasNextLine()) { // returns true if there is another line
                data[i] = line.nextLine();// adds to array
                i++;
            }
            line.close();
            return data;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


   // public static class EditInventory(String fileName) {

        // Read the current line and replace the number
        //String line = raf.readLine();
        //String[] parts = line.split(",");
        //parts[0] = Integer.toString(newNumber);
        //String newLine = String.join(",", parts);
 //   }

}

