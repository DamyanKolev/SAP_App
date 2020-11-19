package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class HelpViewController {
    @FXML Button close = new Button();
    @FXML TextArea textArea = new TextArea();

    public HelpViewController(){

    }

    public void close(){
        // get a handle to the stage
        Stage stage = (Stage) close.getScene().getWindow();
        // do what you have to do
        stage.close();

        textArea.requestFocus();
    }

    public void setTextArea(TextArea textArea){
        this.textArea = textArea;
    }
}
