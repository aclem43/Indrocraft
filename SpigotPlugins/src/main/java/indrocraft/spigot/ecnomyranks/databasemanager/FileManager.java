package indrocraft.spigot.ecnomyranks.databasemanager;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    public static void filewrite(String loc2, String msg) {
        try {
            FileWriter myWriter = new FileWriter(loc2);
            myWriter.write(msg);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}