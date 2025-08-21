package csv.gui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public record HeaderUI(Node rootNode, SimpleBooleanProperty showStudyScene, SimpleBooleanProperty showSettingsScene) {

    private static final int TITLE_SIZE = 40;
    private static final int PARAGRAPH_SIZE = 20;

    public static HeaderUI create(Stage currentStage) {

        SimpleBooleanProperty showStudyScene = new SimpleBooleanProperty();
        SimpleBooleanProperty showSettingsScene = new SimpleBooleanProperty();

        // Create the title's label
        Label labelTitle = new Label("Quizvoc");
        labelTitle.setFont(new Font(TITLE_SIZE));

        // Create the button to Exit the window
        Button buttonExit = new Button("Exit");
        buttonExit.setFont(new Font(PARAGRAPH_SIZE));
        buttonExit.setOnAction(event -> {
            // close the window
            currentStage.close();
        });

        // Buttons to changes the center of the GUI
        Button buttonStudyScene = new Button("Study");
        buttonStudyScene.setFont(new Font(PARAGRAPH_SIZE));
        Button buttonSettingsScene = new Button("Settings");
        buttonSettingsScene.setFont(new Font(PARAGRAPH_SIZE));

        // Create the HBox that will contain all elements of the Header layout
        HBox hBox = new HBox(labelTitle, buttonExit, buttonStudyScene, buttonSettingsScene);

        // Add the functions to change the center of the GUI
        buttonStudyScene.setOnAction(event -> {
            showStudyScene.setValue(true);
        });

        buttonSettingsScene.setOnAction(event -> {
            showSettingsScene.setValue(true);
        });

        return new HeaderUI(hBox, showStudyScene, showSettingsScene);
    }
}
