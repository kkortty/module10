import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class SecondTask {
    public static void main(String[] args) {
        String fileInfo = "file.txt";
        String fileOutput = "user.json";

        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileInfo))) {
            boolean IsFirstLine = true;
            String line;
            while ((line = br.readLine()) != null) {
                if (IsFirstLine) {
                    IsFirstLine = false;
                    continue;
                }

                String [] colums = line.split("\\s+");
                String name = colums[0];
                int age = Integer.parseInt(colums[1]);
                users.add(new User(name, age));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(fileOutput))
        {
           gson.toJson(users, writer);
            System.out.println("Successfully completed. Data saved to: " + fileOutput);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

