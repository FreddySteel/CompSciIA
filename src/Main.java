import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(FileHandling.WholeFileRead("Inventory"));
        Orders.takeOrder();

    }
}
