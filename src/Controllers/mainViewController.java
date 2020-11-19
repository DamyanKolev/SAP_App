package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.FileProcess;

import java.io.File;
import java.io.IOException;

public class mainViewController {

    @FXML
    public TextArea textArea = new TextArea();
    private File openedFile;

    public void openFile() throws IOException {
        //open and visualize the file
        this.openedFile = FileProcess.openFile();
        //visualize the file into textArea
        FileProcess.visualizeFile(openedFile, textArea);

        textArea.requestFocus();
    }

    public void saveAsFile() throws IOException {
        //Save the information into new file or same file if the textArea id different of null
        if(textArea.getText() != null){
            FileProcess.saveAsFile(textArea.getText());
        }
        textArea.requestFocus();
    }

    public void saveFile() throws IOException {
        //Save the information into same file if the openedFile id different of null
        if(openedFile != null) {
            FileProcess.saveFile(textArea.getText(), openedFile);
        }
        else if(textArea.getText() != null){
            FileProcess.saveAsFile(textArea.getText());
        }

        textArea.requestFocus();
    }

    public void lineSwap() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/swapLineView.fxml"));
        Parent root = loader.load();

        //The following both lines are the only addition we need to pass the arguments
        SwapLineViewController swapLineController = loader.getController();
        swapLineController.setTextArea(textArea);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void wordSwap() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/swapWordView.fxml"));
        Parent root = loader.load();

        //The following both lines are the only addition we need to pass the arguments
        SwapWordViewController swapWordController = loader.getController();
        swapWordController.setTextArea(textArea);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void about() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/helpView.fxml"));
        Parent root = loader.load();

        //The following both lines are the only addition we need to pass the arguments
        HelpViewController helpViewController = loader.getController();
        helpViewController.setTextArea(textArea);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
