import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandling {
    public static ArrayList<String> WholeFileRead(String Filename) {
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(Filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static int numOfLines(String filename) {
        int numLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.readLine() != null) {
                numLines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numLines;
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
            System.err.println("Failed to write to file: " + fileName);
        }
    }

    public static void overwriteFile(String filename, ArrayList<String> data) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);

            for (String line : data) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void removeEmptyLines(String filename) {
        ArrayList<String> lines = WholeFileRead(filename);
        if (lines == null) return;

        ArrayList<String> cleanedLines = new ArrayList<>();
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                cleanedLines.add(line);
            }
        }
        overwriteFile(filename, cleanedLines);
    }

    public static void createIfNotExists(String filename){
        File file = new File(filename);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public class FileReadException extends Exception {
        public FileReadException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public class FileWriteException extends Exception {
        public FileWriteException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
