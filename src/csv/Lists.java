package csv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Lists {

    // attributes
    private ArrayList<String> wordsList;
    private ArrayList<String> DefList;

    // constructor
    public Lists() {
        wordsList = new ArrayList<String>();
        DefList = new ArrayList<String>();
    }

    public void importList() {
        // open a window to let the user select the file he wants to
        // verify some conditions:
            // it is a .csv file
        String csvFile = "file/path";

    }

    // shuffle the words and definitions
    public void shuffleWordsAndDefinitions(ArrayList<Integer> currentIntList) {
        // shuffle the intList
        Collections.shuffle(currentIntList);
    }

    public void getWordsAndDefinitions(ReaderCSV currentCsvReader) {
        HashMap<String, ArrayList<String>> dictionary = new HashMap<>();
        dictionary = currentCsvReader.extractWordsAndDefs("list.csv");

        this.wordsList = dictionary.get("words");
        this.DefList = dictionary.get("definitions");
    }

    // getters
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
