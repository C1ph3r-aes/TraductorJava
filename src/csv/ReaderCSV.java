package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReaderCSV {


    // Constructor of the class
    public ReaderCSV() {
    }

    // Extract words and definitions from one CSV file
    public HashMap<String, ArrayList<String>> extractAllWithOneDelimiter(String csvFile, String delimiter) {
        String line;

        // Create a HashMap to hold the dictionary
        HashMap<String, ArrayList<String>> dictionary = new HashMap<>();

        ArrayList<String> firstItems = new ArrayList<>();
        ArrayList<String> secondItems = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // Split the line by comma
                String[] values = line.split(delimiter);

                // Check if the line has both a word and a definition
                if (values.length == 2) {
                    String firstItem = values[0].trim(); // Get the word and trim whitespace
                    String secondItem = values[1].trim(); // Get the definition and trim whitespace

                    // Add to respective lists
                    firstItems.add(firstItem);
                    secondItems.add(secondItem);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Put the ArrayLists into the HashMap
        dictionary.put("first", firstItems);
        dictionary.put("second", secondItems);

        return dictionary;
    }
}
