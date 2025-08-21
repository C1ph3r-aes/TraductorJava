package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ReaderCSV {

    private final ArrayList<String> wordsList;
    private final ArrayList<String> DefList;

    // Constructor of the class
    public ReaderCSV() {
        DefList = new ArrayList<>();
        wordsList = new ArrayList<>();
    }

    // Extract the words from "words.csv" to put them in the ArrayList "wordsList"
    public void extractWords() {
        String csvFile = "words.csv";
        String line;
        // Delimiter between words in the csv file
        String csvSplitBy = "\n";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                // Process the values
                for (String value : values) {
                    // System.out.print(value + " ");
                    wordsList.add(value);
                }
                // System.out.println();

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Extract the definitions from "def.csv" to put them in the ArrayList "DefList"
    public void extractDefs() {
        String csvFile = "def.csv";
        String line;
        // Delimiter between definitions in the csv file
        String csvSplitBy = "\n";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                // Process the values
                for (String value : values) {
                    // System.out.print(value + " ");
                    DefList.add(value);
                }
                // System.out.println();

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // If there is only a list of words, translate the words using an API
    // Note: this could be a future feature, but I don't have an API key yet
    public void translateWords() {
        for (int x = 0; x < this.getNbrWords(); x++) {
            System.out.println(this.getWordAt(x));

            // translate the word
            String translatedWord = this.wordsList.get(x);

            // add it to the Def list
            this.DefList.add(translatedWord);
        }
    }

    // shuffle the words and definitions
    public void shuffleWordsAndDefinitions(ArrayList<Integer> currentIntList) {
        // shuffle the intList
        Collections.shuffle(currentIntList);
    }

    // Getters
    public ArrayList<String> getWordsList() {
        return wordsList;
    }

    public ArrayList<String> getDefList() {
        return DefList;
    }

    public String getWordAt(int index) {
        return wordsList.get(index);
    }

    public String getDefAt(int index) {
        return DefList.get(index);
    }

    public int getNbrWords() {
        return wordsList.size();
    }
}
