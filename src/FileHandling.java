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

    //returns number of lines in array.
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

    // converts the file to an array,
    //returns as an String array
    public static String[] fileToArray(String filename, int numLines) {
        try {

            Scanner line;// = //new Scanner(new File(filename));
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

    //user can write to a file, and chose to append.
    public static void WriteToFile(String fileName, String input, Boolean append) {
        try {
            FileWriter fw = new FileWriter(fileName, append);
            PrintWriter pw = new PrintWriter(fw);

            pw.write("\n" + input);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

