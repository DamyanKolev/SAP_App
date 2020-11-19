package Model;

import Controllers.mainViewController;
import sample.FileProcess;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SwapLine {

    private static List<String> list = new ArrayList<>();

    //swap two rows from any file
    public static void swapLines(File file, int first_line_index, int second_line_index) throws IOException {
        mainViewController controller = new mainViewController();
        readFileLineByLine(file);

        Collections.swap(list, first_line_index, second_line_index);

        writeFileFromFile(file);

        FileProcess.visualizeFile(file, controller.textArea);
    }

    //read from file row by row and save every row into list
    private static void readFileLineByLine(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(Pattern.compile("\\n"));
        //read file line by line
        while (scanner.hasNext()) {
            list.add(scanner.next());
        }
        scanner.close();
    }

    //writes to the file line by line, and the lines are contained in a list
    private static void writeFileFromFile(File file) throws IOException {
        FileWriter writer = new FileWriter(file);

        for(int i=0; i<list.size();i++) {
            if(i==list.size()-1) {
                writer.write(list.get(i));
            }
            else {
                writer.write(list.get(i)+"\n");
            }
        }

        writer.close();
    }
}
