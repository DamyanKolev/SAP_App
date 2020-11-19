package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.awt.Dimension;
import java.io.IOException;

public class Main extends Application {
    private final Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Views/mainView.fxml"));
            primaryStage.setTitle("File processing");
            primaryStage.setMaximized(true);
            primaryStage.setScene(new Scene(root, screenSize.getWidth() / 3, screenSize.getHeight() / 2));

            primaryStage.show();
        } catch (IOException e) {
            e.getMessage();
        }
    }


    public static void main(String[] args) {launch(args);}
}
