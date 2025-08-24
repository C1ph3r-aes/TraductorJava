package gui;

import Lists;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public record SettingsUI(Node rootNode, SimpleBooleanProperty resetUI) {

    private static final int TITLE_SIZE = 40;
    private static final int PARAGRAPH_SIZE = 20;

    public static SettingsUI create(Lists classList, AtomicInteger currentIndex, ArrayList<Integer> currentIntList) {

        SimpleBooleanProperty resetUI = new SimpleBooleanProperty();

        // Create the elements of the Settings Layout
        CheckBox checkBoxShuffleList = new CheckBox("Shuffle list");

        Button buttonApply = new Button("Apply changes");

        // Add the functions of the "Apply Changes" button
        buttonApply.setOnAction(event -> {
            // Apply changes and reset everything
            if(checkBoxShuffleList.isSelected()) {
                // If checkBox selected, we shuffle the currentIntList
                classList.shuffleWordsAndDefinitions(currentIntList);
            } else {
                // reset currentIntList
                currentIntList.clear();
                for(int i = 0; i < classList.getNbrWords(); i++) {
                    currentIntList.add(i);
                }
            }
            // Apply other settings

            // Reset the index
            currentIndex.set(0);
            // Reset entirely the StudyUI
            System.out.println("Reset UI Boolean changed");
            resetUI.setValue(true);
        });

        // Create the VBox that will contain all the elements of the Settings layout
        VBox vBox = new VBox(checkBoxShuffleList, buttonApply);

        return new SettingsUI(vBox, resetUI);
    }
}
