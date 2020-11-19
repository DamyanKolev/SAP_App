package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DecimalTextFormatter;
import sample.FileProcess;
import Model.SwapLine;
import java.io.File;
import java.io.IOException;

public class SwapLineViewController {
    @FXML private TextField first_line_index = new TextField();
    @FXML private TextField second_line_index = new TextField();
    @FXML private TextField fileView = new TextField();
    @FXML private Button closeButton = new Button();
    @FXML private Button swap = new Button();
    @FXML private TextArea textArea;
    private final int MAX_NUMBER = 10000;
    public File chooseFile;

    public SwapLineViewController(){
        first_line_index.setTextFormatter(new DecimalTextFormatter(0, MAX_NUMBER));
        second_line_index.setTextFormatter(new DecimalTextFormatter(0, MAX_NUMBER));
    }

    public void close(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
        textArea.requestFocus();
    }

   public void swapByIndex() throws IOException {
       Alert a = new Alert(Alert.AlertType.NONE);
       // set alert type
       a.setAlertType(Alert.AlertType.ERROR);

       //check for blank fields
       if (first_line_index.getText() == null || second_line_index.getText() == null || chooseFile == null){
           // set content text
           a.setContentText("Fields are not filled");

           // show the dialog
           a.show();
       }
       //check if the text fields are numeric
       else if (!isNumericTextFields()) {
           //check for if row1 is bigger number than row1
           if(Integer.parseInt(first_line_index.getText()) > Integer.parseInt(second_line_index.getText())){
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

               //Clear the areas
               first_line_index.clear();
               second_line_index.clear();
           }
           //check for if the fields are bigger integer than count of file lines
           else if(Integer.parseInt(first_line_index.getText()) >= FileProcess.fileLineCounter(chooseFile) ||
                   Integer.parseInt(second_line_index.getText()) >= FileProcess.fileLineCounter(chooseFile)){

               if(Integer.parseInt(first_line_index.getText()) >= FileProcess.fileLineCounter(chooseFile) ||
                       Integer.parseInt(second_line_index.getText()) >= FileProcess.fileLineCounter(chooseFile)){
                   // set content text
                   a.setContentText("Non-existent line indexes in file");
               }
               else if(Integer.parseInt(first_line_index.getText()) >= FileProcess.fileLineCounter(chooseFile)) {
                   // set content text
                   a.setContentText("Non-existent first_line_index in file");
               }
               else if(Integer.parseInt(second_line_index.getText()) >= FileProcess.fileLineCounter(chooseFile)){
                   // set content text
                   a.setContentText("Non-existent second_line_index in file");
               }

               // show the dialog
               a.show();

               //Clear the areas
               first_line_index.clear();
               second_line_index.clear();
           }
           //swap the lines
           else{
               SwapLine.swapLines(chooseFile, Integer.parseInt(first_line_index.getText()), Integer.parseInt(second_line_index.getText()));
               //visualize the file into textArea
               FileProcess.visualizeFile(chooseFile, textArea);
               // get a handle to the stage
               Stage stage = (Stage) swap.getScene().getWindow();
               // do what you have to do
               stage.close();

               textArea.requestFocus();
           }
       }
       //check for if row1 is bigger number than row1
       else {
           // set content text
           a.setContentText("Invalid input");
           // show the dialog
           a.show();

           first_line_index.clear();
           second_line_index.clear();
       }

   }

   @FXML
   private void openFile() throws IOException {
       this.chooseFile = FileProcess.openFile();

       fileView.clear();
       fileView.appendText(chooseFile.getAbsolutePath());
   }

   private boolean isNumericTextFields() {
        String matcher = "-?\\d+(\\\\.\\d+)?";
        if (!first_line_index.getText().matches(matcher)) {
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
