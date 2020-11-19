package sample;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileProcess {

    private final static FileChooser FILE_CHOOSER = new FileChooser();
    private final static Stage PRIMARY_STAGE = new Stage();

    //Visualized text from file who opens into textArea
    public static File openFile(){
        // create an object of FileChooser class
        FILE_CHOOSER.setTitle("Open file");
        // invoke the showsOpenDialog function to show the save dialog
        File filePath = FILE_CHOOSER.showOpenDialog(PRIMARY_STAGE);
        // Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        FILE_CHOOSER.getExtensionFilters().add(extFilter);

        return filePath;
    }

    //Makes new file or rewrites the same file with the new content in textArea
    public static void saveAsFile(String textAreaContent) throws IOException {
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FILE_CHOOSER.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File filePath = FILE_CHOOSER.showSaveDialog(PRIMARY_STAGE);

        //checks if the file is open
        if (filePath != null) {
            writeIntoFileFromTextArea(textAreaContent,filePath);
        }
    }

    //Rewrites the same file with the new content in textArea
    public static void saveFile(String textAreaContent, File file) throws IOException {
        writeIntoFileFromTextArea(textAreaContent, file);
    }

    //Write into files
    private static void writeIntoFileFromTextArea(String textAreaContent, File filePath) throws IOException {
        FileWriter fileWriter;

        //write into file
        fileWriter = new FileWriter(filePath);
        fileWriter.write(textAreaContent);
        fileWriter.close();
    }

    //Read file byte by byte and return contents into string
    private static String readFileByteByByte(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }


    //display the text from file into Text Area
    public static void visualizeFile(File filePath, TextArea textArea) throws IOException {
        if (filePath != null) {
            int i = 0;
            textArea.clear();
            //if file is more than 1 MB
            if (filePath.length() > 1048576) {
                Scanner scanner = new Scanner(filePath);
                //read file line by line
                while (scanner.hasNext()) {
                    textArea.appendText(scanner.next());
                    if (i == fileLineCounter(filePath) - 1) {
                        textArea.appendText(scanner.next());
                    } else {
                        textArea.appendText(scanner.next() + "\n");
                    }
                    i++;
                }
                scanner.close();
            }
            //if file less than 1 MB
            else {
                String content = readFileByteByByte(String.valueOf(filePath));
                textArea.appendText(content);
            }
        }
    }

    //Counts the lines of the file
    public static int fileLineCounter(File file) throws FileNotFoundException {
        int counter = 0; //initialize the counter

        if(file != null) {
            Scanner scanner = new Scanner(file);

            //Counts the line of the given file
            while (scanner.hasNext()) {
                scanner.nextLine();
                counter++;
            }
            scanner.close();
        }
        return counter;
    }
}

