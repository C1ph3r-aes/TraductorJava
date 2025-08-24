package csv.gui;

import csv.ReaderCSV;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public record ListsUI(Node rootNode) {

    private static final int TITLE_SIZE = 40;
    private static final int PARAGRAPH_SIZE = 20;

    public static ListsUI create(ReaderCSV currentReaderCsv) {

        Button buttonImportLists = new Button("Import List");
        // button that lets you select a .csv file to import a list

        // labels that shows the different lists

        VBox vbox = new VBox(buttonImportLists);

        return new ListsUI(vbox);
    }
}
