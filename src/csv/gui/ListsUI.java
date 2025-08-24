package csv.gui;

import csv.List;
import csv.ReaderCSV;
import csv.WriterCSV;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;

public record ListsUI(Node rootNode) {

    private static final int TITLE_SIZE = 40;
    private static final int PARAGRAPH_SIZE = 20;
    private static final String PathToCsvFileAllListsPaths = "csvFilesPaths.csv";

    public static ListsUI create(ReaderCSV currentCsvReader, WriterCSV currentCsvWriter, List listclass) {

        Button buttonImportLists = new Button("Import List");
        buttonImportLists.setFont(new Font(PARAGRAPH_SIZE));
        // button that lets you select a .csv file to import a list
        buttonImportLists.setOnAction(event -> {
            listclass.importList(currentCsvWriter);
        });

        HashMap<String, ArrayList<String>> dictionary = new HashMap<>();
        dictionary = currentCsvReader.extractAllWithOneDelimiter(PathToCsvFileAllListsPaths, ",");

        ArrayList<String> allPaths = new ArrayList<>();
        ArrayList<String> allFileNames = new ArrayList<>();

        allPaths = dictionary.get("first");
        allFileNames = dictionary.get("second");

        VBox vbox = new VBox(buttonImportLists);

        for(Integer x = 0; x < allPaths.size(); x++) {
            Label fileLabel = new Label(allFileNames.get(x));
            fileLabel.setFont(new Font(PARAGRAPH_SIZE));
            vbox.getChildren().add(fileLabel);
        }


        return new ListsUI(vbox);
    }
}
