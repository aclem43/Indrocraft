package indrocraft.spigot.ecnomyranks.databasemanager;

import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {
    public static void main(String[] args) {
        try {
            FileWriter myWriter = new FileWriter(args[0]);
            myWriter.write(args[1]);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
