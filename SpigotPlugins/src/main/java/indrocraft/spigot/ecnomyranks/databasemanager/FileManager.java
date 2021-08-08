package indrocraft.spigot.ecnomyranks.databasemanager;

import java.io.*;
import java.util.Scanner;

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

    public static String fileread(String loc) {
        try {
            File myObj = new File(loc);
            Scanner myReader = new Scanner(myObj);
            String output = "";
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                output += " " + data;
            }
            myReader.close();
            return output;
        } catch (FileNotFoundException e) {
            return "An error occurred.";
        }
    }
}