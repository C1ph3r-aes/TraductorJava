package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReaderCSV {

    private final ArrayList<String> wordsList;
    private final ArrayList<String> DefList;


    public ReaderCSV() {
        DefList = new ArrayList<>();
        wordsList = new ArrayList<>();
    }

    public void extractWords() {
        String csvFile = "words.csv"; // Specify the path to your CSV file
        String line;
        String csvSplitBy = ","; // Change this if your CSV uses a different delimiter

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(csvSplitBy);
                // Process the values as needed

                for (String value : values) {
                    System.out.print(value + " ");
                    wordsList.add(value);
                }
                System.out.println();

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void extractDefs() {
        String csvFile = "def.csv"; // Specify the path to your CSV file
        String line;
        String csvSplitBy = "\n"; // Change this if your CSV uses a different delimiter

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(csvSplitBy);
                // Process the values as needed

                for (String value : values) {
                    System.out.print(value + " ");
                    DefList.add(value);
                }
                System.out.println();

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void translateWords() {
        for (int x = 0; x < this.getNbrWords(); x++) {
            System.out.println(this.getWordAt(x));

            // translate the word
            String translatedWord = this.wordsList.get(x);

            // add it to the Def list
            this.DefList.add(translatedWord);
        }
    }

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
