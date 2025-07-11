package csv;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public class Main extends Application {

    private final int TITLE_SIZE = 40;
    private final int PARAGRAPH_SIZE = 20;

    @Override
    public void start(Stage primaryStage) {
        // Create the ReaderCSV object to extract the words and definitions from the CSV files
        ReaderCSV csvReader = new ReaderCSV();
        csvReader.extractWords();
        csvReader.extractDefs();

        // Create an index to count up how many words we have studied to know when to finish the program
        AtomicInteger index = new AtomicInteger();

        // Elements of the GUI
        Label labelTitle = new Label("Quizvoc");
        labelTitle.setFont(new Font(TITLE_SIZE));

        Label labelWord = new Label("Word: " + csvReader.getWordAt(index.get()));
        labelWord.setFont(new Font(PARAGRAPH_SIZE));

        Label labelDefinition = new Label("Definition: ");
        labelDefinition.setFont(new Font(PARAGRAPH_SIZE));

        Button buttonNext = new Button("Next");
        buttonNext.setFont(new Font(PARAGRAPH_SIZE));

        Button buttonShowDef = new Button("Show definition");
        buttonShowDef.setFont(new Font(PARAGRAPH_SIZE));

        Button buttonExit = new Button("Exit");
        buttonExit.setFont(new Font(PARAGRAPH_SIZE));

        buttonNext.setOnAction(event -> {
            index.getAndIncrement();

            if (index.get() < csvReader.getNbrWords() && Objects.equals(buttonNext.getText(), "Next")) {
                labelWord.setText("Word: " + csvReader.getWordAt(index.get()));
                labelDefinition.setText("Definition: ");
            } else if (Objects.equals(buttonNext.getText(), "Redo")) {
                index.set(0);
                labelWord.setText("Word: " + csvReader.getWordAt(index.get()));
                buttonNext.setText("Next");
                labelTitle.setText("Quizvoc");
            } else {
                labelTitle.setText("Quizvoc - finish");
                buttonNext.setText("Redo");
            }
        });

        buttonShowDef.setOnAction(event -> {
            // "Definition: "
            // labelDefinition.getText()
            if(Objects.equals(labelDefinition.getText(), "Definition: ")) {
                labelDefinition.setText("Definition: " + csvReader.getDefAt(index.get()));
                buttonShowDef.setText("Hide definition");
            } else {
                labelDefinition.setText("Definition: ");
                buttonShowDef.setText("Show definition");
            }

        });

        buttonExit.setOnAction(event -> {
            // close the window
            primaryStage.close();
        });

        // Settings of the GUI
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(labelTitle, 0, 0);
        grid.add(labelWord, 0, 1);
        grid.add(labelDefinition, 0, 2);
        grid.add(buttonShowDef, 0, 3);
        grid.add(buttonNext, 0, 4);
        grid.add(buttonExit, 0, 5);

        Scene scene = new Scene(grid, 600, 300);
        primaryStage.setTitle("Quizvoc Java edition");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
