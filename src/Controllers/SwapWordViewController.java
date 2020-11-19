package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DecimalTextFormatter;
import Model.SwapWord;
import sample.FileProcess;

import java.io.File;
import java.io.IOException;


public class SwapWordViewController {
    @FXML private TextField first_line_index = new TextField();
    @FXML private TextField first_line_word_index = new TextField();
    @FXML private TextField second_line_word_index = new TextField();
    @FXML private TextField second_line_index = new TextField();
    @FXML private TextField fileView = new TextField();
    @FXML private TextArea textArea;
    @FXML private Button close = new Button();
    @FXML private Button swap = new Button();
    private final int MAX_NUMBER = 10000;
    private File chooseFile;


    public SwapWordViewController(){
        first_line_index.setTextFormatter(new DecimalTextFormatter(0, MAX_NUMBER));
        first_line_word_index.setTextFormatter(new DecimalTextFormatter(0, MAX_NUMBER));
        second_line_word_index.setTextFormatter(new DecimalTextFormatter(0, MAX_NUMBER));
        second_line_index.setTextFormatter(new DecimalTextFormatter(0, MAX_NUMBER));
    }

    public void close(){
        // get a handle to the stage
        Stage stage = (Stage) close.getScene().getWindow();
        // do what you have to do
        stage.close();

        textArea.requestFocus();
    }

    public void swap() throws IOException {
        Alert a = new Alert(Alert.AlertType.NONE);

        // set alert type
        a.setAlertType(Alert.AlertType.ERROR);

        //check for blank fields
        if (first_line_index.getText() == null || first_line_word_index.getText() == null ||
                second_line_word_index.getText() == null || second_line_index.getText() == null ||chooseFile == null) {

            // set content text
            a.setContentText("Fields are not filled");

            // show the dialog
            a.show();
        }
        else if(!isNumericTextFields()) {
            boolean firstInput = SwapWord.checkInputIndexes(
                    Integer.parseInt(first_line_index.getText()), Integer.parseInt(first_line_word_index.getText()));

            boolean secondInput = SwapWord.checkInputIndexes(
                    Integer.parseInt(second_line_index.getText()), Integer.parseInt(second_line_word_index.getText()));

            //check for if row1 is bigger number than row1
            if (Integer.parseInt(first_line_index.getText()) > Integer.parseInt(second_line_index.getText())) {
                // set content text
                a.setContentText("The number for first line have to be smaller than second line");

                // show the dialog
                a.show();

                //Clear the areas
                first_line_index.clear();
                second_line_index.clear();
            }
            else if(Integer.parseInt(first_line_index.getText()) == Integer.parseInt(second_line_index.getText())){
                // set content text
                a.setContentText("The indices cannot be equal");

                // show the dialog
                a.show();

                //clear the all areas
                first_line_index.clear();
                first_line_word_index.clear();
                second_line_word_index.clear();
                second_line_index.clear();
            }
            //checks if the indices exist
            else if (firstInput && secondInput) {
                // set content text
                a.setContentText("Wrong input, try again");

                // show the dialog
                a.show();

                //clear the all areas
                first_line_index.clear();
                first_line_word_index.clear();
                second_line_word_index.clear();
                second_line_index.clear();
            }
            //swap the words
            else {
                int i1 = Integer.parseInt(first_line_index.getText());//first line index
                int j1 = Integer.parseInt(first_line_word_index.getText());//first line word index
                int i2 = Integer.parseInt(second_line_index.getText());//second line index
                int j2 = Integer.parseInt(second_line_word_index.getText());//second line word index

                //swap the words
                SwapWord.swapWords(chooseFile, i1, j1, i2, j2);
                //visualize the file into textArea
                FileProcess.visualizeFile(chooseFile, textArea);
                // get a handle to the stage
                Stage stage = (Stage) swap.getScene().getWindow();
                // do what you have to do
                stage.close();

                textArea.requestFocus();
            }
        }
        else{
            // set content text
            a.setContentText("Invalid input");
            // show the dialog
            a.show();

            first_line_index.clear();
            first_line_word_index.clear();
            second_line_word_index.clear();
            second_line_index.clear();
        }
    }

    @FXML
    private void openFile(){
        chooseFile = FileProcess.openFile();

        fileView.clear();
        fileView.appendText(chooseFile.getAbsolutePath());
    }

    private boolean isNumericTextFields() {
        String matcher = "-?\\d+(\\\\.\\d+)?";
        if (!first_line_index.getText().matches(matcher)) {
            return true;
        }
        if (!first_line_word_index.getText().matches(matcher)) {
            return true;
        }
        if (!second_line_word_index.getText().matches(matcher)) {
            return true;
        }
        if (!second_line_index.getText().matches(matcher)) {
            return true;
        }
        return false;
    }

    public void setTextArea(TextArea textArea){
        this.textArea = textArea;
    }
}
