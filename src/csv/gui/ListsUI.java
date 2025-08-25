package csv.gui;

import csv.List;
import csv.ReaderCSV;
import csv.WriterCSV;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public record ListsUI(Node rootNode) {

    private static final int TITLE_SIZE = 40;
    private static final int PARAGRAPH_SIZE = 20;
    private static final String PathToCsvFileAllListsPaths = "csvFilesPaths.csv";

    public static ListsUI create(ReaderCSV currentCsvReader, WriterCSV currentCsvWriter, List listclass, SimpleBooleanProperty realoadListUI) {

        Button buttonImportLists = new Button("Import List");
        buttonImportLists.setFont(new Font(PARAGRAPH_SIZE));
        // button that lets you select a .csv file to import a list
        buttonImportLists.setOnAction(event -> {
            listclass.importList(currentCsvWriter, realoadListUI);
        });

        AtomicReference<HashMap<String, ArrayList<String>>> dictionary = new AtomicReference<>(new HashMap<>());
        dictionary.set(currentCsvReader.extractAllWithOneDelimiter(PathToCsvFileAllListsPaths, ","));

        AtomicReference<ArrayList<String>> allPaths = new AtomicReference<>(new ArrayList<>());
        AtomicReference<ArrayList<String>> allFileNames = new AtomicReference<>(new ArrayList<>());

        allPaths.set(dictionary.get().get("first"));
        allFileNames.set(dictionary.get().get("second"));

        VBox vbox = new VBox(buttonImportLists);

        for(Integer x = 0; x < allPaths.get().size(); x++) {
            HBox listHBox = new HBox();

            Label fileLabel = new Label(allFileNames.get().get(x));
            fileLabel.setFont(new Font(PARAGRAPH_SIZE));

            Button buttonStudyList = new Button("Study List");
            buttonStudyList.setFont(new Font(PARAGRAPH_SIZE));

            Button buttonModifyList = new Button("Modify List");
            buttonModifyList.setFont(new Font(PARAGRAPH_SIZE));

            Button buttonDeleteList = new Button("Delete List");
            buttonDeleteList.setFont(new Font(PARAGRAPH_SIZE));

            listHBox.getChildren().add(fileLabel);
            listHBox.getChildren().add(buttonStudyList);
            listHBox.getChildren().add(buttonModifyList);
            listHBox.getChildren().add(buttonDeleteList);

            vbox.getChildren().add(listHBox);
        }

        // reaload the UI
        realoadListUI.subscribe((Boolean bool) -> {
            if (bool) {
                dictionary.set(currentCsvReader.extractAllWithOneDelimiter(PathToCsvFileAllListsPaths, ","));

                allPaths.set(dictionary.get().get("first"));
                allFileNames.set(dictionary.get().get("second"));

                vbox.getChildren().clear();
                vbox.getChildren().add(buttonImportLists);

                for(Integer x = 0; x < allPaths.get().size(); x++) {
                    HBox listHBox = new HBox();

                    Label fileLabel = new Label(allFileNames.get().get(x));
                    fileLabel.setFont(new Font(PARAGRAPH_SIZE));

                    Button buttonStudyList = new Button("Study List");
                    buttonStudyList.setFont(new Font(PARAGRAPH_SIZE));

                    Button buttonModifyList = new Button("Modify List");
                    buttonModifyList.setFont(new Font(PARAGRAPH_SIZE));

                    Button buttonDeleteList = new Button("Delete List");
                    buttonDeleteList.setFont(new Font(PARAGRAPH_SIZE));

                    listHBox.getChildren().add(fileLabel);
                    listHBox.getChildren().add(buttonStudyList);
                    listHBox.getChildren().add(buttonModifyList);
                    listHBox.getChildren().add(buttonDeleteList);

                    vbox.getChildren().add(listHBox);
                }

                realoadListUI.setValue(false);
            }
        });


        return new ListsUI(vbox);
    }
}
