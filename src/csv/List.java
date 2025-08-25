package csv;

import javafx.beans.property.SimpleBooleanProperty;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class List {

    // attributes
    private ArrayList<String> wordsList;
    private ArrayList<String> DefList;
    private final String PathToCsvFileAllListsPaths;

    // constructor
    public List() {
        PathToCsvFileAllListsPaths = "csvFilesPaths.csv";
        wordsList = new ArrayList<String>();
        DefList = new ArrayList<String>();
    }

    private static void selectCsvFile(WriterCSV currentCsvWriter, String pathToCsvFileAllListsPaths, JFrame currentFrame) {
        // Create a JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a CSV File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));

        // Show the file chooser dialog
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            String filePath = fileToOpen.getAbsolutePath();
            String fileName = fileToOpen.getName();

            currentCsvWriter.writeToCSV(pathToCsvFileAllListsPaths, filePath, fileName);

        }

        currentFrame.dispose();
    }
    

    public void importList(WriterCSV currentCsvWriter, SimpleBooleanProperty reloadListUI) {
        // open a window to let the user select the file he wants to
        // Create a JFrame
        JFrame frame = new JFrame("Select CSV File");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        selectCsvFile(currentCsvWriter, this.PathToCsvFileAllListsPaths, frame);

        // refresh ListsUI
        reloadListUI.setValue(true);
    }

    // shuffle the words and definitions
    public void shuffleWordsAndDefinitions(ArrayList<Integer> currentIntList) {
        // shuffle the intList
        Collections.shuffle(currentIntList);
    }

    public void getWordsAndDefinitions(ReaderCSV currentCsvReader) {
        HashMap<String, ArrayList<String>> dictionary = new HashMap<>();
        dictionary = currentCsvReader.extractAllWithOneDelimiter("list.csv", ",");

        this.wordsList = dictionary.get("first");
        this.DefList = dictionary.get("second");
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
