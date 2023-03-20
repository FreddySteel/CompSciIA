import com.sun.jdi.event.StepEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandling {
    public static ArrayList<String> WholeFileRead (String Filename) {
        try {
            FileReader fr = new FileReader(Filename);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> data = new ArrayList<>();
            String line =br.readLine();
            while (line != null) {
                data.add(line);
                line = br.readLine();
            }
            return data;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
