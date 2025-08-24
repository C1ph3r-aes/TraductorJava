package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ReaderCSV {


    // Constructor of the class
    public ReaderCSV() {
    }

    // Extract words and definitions from one CSV file
    public HashMap<String, ArrayList<String>> extractWordsAndDefs(String csvFile) {
        String line;

        // Create a HashMap to hold the dictionary
        HashMap<String, ArrayList<String>> dictionary = new HashMap<>();

        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> definitions = new ArrayList<>();

        // addCsvPath(csvFile);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // Split the line by comma
                String[] values = line.split(",");

                // Check if the line has both a word and a definition
                if (values.length == 2) {
                    String word = values[0].trim(); // Get the word and trim whitespace
                    String definition = values[1].trim(); // Get the definition and trim whitespace

                    // Add to respective lists
                    words.add(word);
                    definitions.add(definition);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Put the ArrayLists into the HashMap
        dictionary.put("words", words);
        dictionary.put("definitions", definitions);

        return dictionary;
    }
}
