package aoc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class utils {

    public static List<String> readFile(String file) {
        BufferedReader reader;
        List<String> result = new ArrayList<>();
        try
        {
            reader = new BufferedReader(new FileReader(new File("input/"+file)));
            String line;

            while ((line = reader.readLine()) != null)
            {
                result.add(line);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}
