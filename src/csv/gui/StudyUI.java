package csv.gui;

import csv.ReaderCSV;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public record StudyUI(Node rootNode) {

    private static final int TITLE_SIZE = 40;
    private static final int PARAGRAPH_SIZE = 20;

    public static StudyUI create(ReaderCSV currentReaderCsv, AtomicInteger currentIndex, ArrayList<Integer> currentIntList, SimpleBooleanProperty resetUI) {

        // Create the elements of the Study Layout
        System.out.println("The word: " + currentReaderCsv.getWordAt(currentIntList.get(currentIndex.get())));
        Label labelWord = new Label("Word: " + currentReaderCsv.getWordAt(currentIntList.get(currentIndex.get())));
        labelWord.setFont(new Font(PARAGRAPH_SIZE));
        Label labelDefinition = new Label("Definition: ");
        labelDefinition.setFont(new Font(PARAGRAPH_SIZE));


        Button buttonNext = new Button("Next");
        buttonNext.setFont(new Font(PARAGRAPH_SIZE));
        Button buttonShowDef = new Button("Show definition");
        buttonShowDef.setFont(new Font(PARAGRAPH_SIZE));

        // Add the functions of the buttons
        buttonNext.setOnAction(event -> {
            currentIndex.getAndIncrement();
            // When there are still words to study we go to the next words
            if (currentIndex.get() < currentReaderCsv.getNbrWords() && Objects.equals(buttonNext.getText(), "Next")) {
                labelWord.setText("Word: " + currentReaderCsv.getWordAt(currentIntList.get(currentIndex.get())));
                labelDefinition.setText("Definition: ");
                buttonShowDef.setText("Show definition");
            } // When the button is in "Redo" mode, and we click on it, we reset the index and reset the labels
            else if (Objects.equals(buttonNext.getText(), "Redo")) {
                currentIndex.set(0);
                labelWord.setText("Word: " + currentReaderCsv.getWordAt(currentIntList.get(currentIndex.get())));
                labelDefinition.setText("Definition: ");
                buttonNext.setText("Next");
            } // When there are no more words we add the possibility to redo (restudy the list)
            else {
                labelWord.setText("Word: ");
                labelDefinition.setText("Definition: ");
                buttonShowDef.setText("Show definition");
                buttonNext.setText("Redo");
            }
        });

        buttonShowDef.setOnAction(event -> {
            // If the button's text is "Show definition" we show the definition and change its text
            if(!Objects.equals(buttonNext.getText(), "Redo")) {
                if (Objects.equals(buttonShowDef.getText(), "Show definition")) {
                    labelDefinition.setText("Definition: " + currentReaderCsv.getDefAt(currentIntList.get(currentIndex.get())));
                    buttonShowDef.setText("Hide definition");
                } // Otherwise, if the button's text is "hide definition" we hide the definition and change its text
                else if (Objects.equals(buttonShowDef.getText(), "Hide definition")) {
                    labelDefinition.setText("Definition: ");
                    buttonShowDef.setText("Show definition");
                }
            }
        });

        // Create the VBox that will contain all elements of the Study layout
        VBox vBox = new VBox(labelWord, labelDefinition, buttonNext, buttonShowDef);

        // reset UI when boolean is true
        resetUI.subscribe((Boolean bool) -> {
            if(bool) {
                // reset the text to default values
                labelWord.setText("Word: " + currentReaderCsv.getWordAt(currentIntList.get(currentIndex.get())));
                labelDefinition.setText("Definition: ");
                buttonNext.setText("Next");
                buttonShowDef.setText("Show definition");

                resetUI.setValue(false);
            }
        });

        return new StudyUI(vBox);
    }
}
