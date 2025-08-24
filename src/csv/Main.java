package csv;

import csv.gui.HeaderUI;
import csv.gui.ListsUI;
import csv.gui.SettingsUI;
import csv.gui.StudyUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Main extends Application {

    private static final int PARAGRAPH_SIZE = 20;

    @Override
    public void start(Stage primaryStage) {

        // Create the ReaderCSV object to extract the words and definitions from the CSV files
        ReaderCSV csvReader = new ReaderCSV();
        WriterCSV csvWriter = new WriterCSV();

        List listClass = new List();
        listClass.getWordsAndDefinitions(csvReader);

        // Create an index to count up how many words we have studied to know when to finish the program
        AtomicInteger index = new AtomicInteger();
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for(int i = 0; i < listClass.getNbrWords(); i++) {
            intList.add(i);
        }

        // create to BorderPane that will contain the Header and a Center (study or settings)
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp, 600, 300);
        primaryStage.setTitle("Quizvoc Java edition");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create all the Layout of the different parts of the GUI
        SettingsUI set = SettingsUI.create(listClass, index, intList);
        StudyUI stu = StudyUI.create(listClass, index, intList, set.resetUI());
        HeaderUI h = HeaderUI.create(primaryStage);
        ListsUI l = ListsUI.create(csvReader, csvWriter, listClass);

        // Set the top and the default center of the BorderPane
        bp.setTop(h.rootNode());
        bp.setCenter(stu.rootNode());

        // handle the buttons to change the scenes
        h.showStudyScene().subscribe((Boolean bool) -> {
            if(bool) {
                bp.setCenter(stu.rootNode());

                h.showStudyScene().setValue(false);
            }
        });

        h.showSettingsScene().subscribe((Boolean bool) -> {
            if(bool) {
                bp.setCenter(set.rootNode());

                h.showSettingsScene().setValue(false);
            }
        });

        h.showListsScene().subscribe((Boolean bool) -> {
            if(bool) {
                bp.setCenter(l.rootNode());

                h.showListsScene().setValue(false);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
