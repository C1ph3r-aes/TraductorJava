package csv;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ReaderCSV csvReader = new ReaderCSV();
        csvReader.extractWords();
        csvReader.extractDefs();

        // System.out.println(csvReader.getNbrWords());
        AtomicInteger index = new AtomicInteger();

        Label labelTitle = new Label("Quizvoc");
        Label labelTerme = new Label("Terme: " + csvReader.getWordAt(index.get()));
        Label labelDefinition = new Label("Definition: ");
        Button buttonNext = new Button("Suivant");
        Button buttonShowDef = new Button("Montrer Définition");

        buttonNext.setOnAction(event -> {
            index.getAndIncrement();

            if (index.get() < csvReader.getNbrWords()) {
                labelTerme.setText("Terme: " + csvReader.getWordAt(index.get()));
                labelDefinition.setText("Definition: ");
            } else {
                labelTitle.setText("Quizvoc - finish");
                // close the window
            }
        });

        buttonShowDef.setOnAction(event -> {
            labelDefinition.setText("Definition: " + csvReader.getDefAt(index.get()));
        });

        GridPane grid = new GridPane();
        grid.addRow(0, labelTitle);
        grid.addRow(1, labelTerme);
        grid.addRow(2, labelDefinition);
        grid.addRow(3, buttonNext, buttonShowDef);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setTitle("Quizvoc Java edition");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
