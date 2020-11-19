package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SwapWord {
    private static List<String[]> list = new ArrayList<>();

    public static void swapWords(File file, int first_line_index, int first_line_word_index, int second_line_index, int second_line_word_index)
            throws IOException
    {
        if(file != null) {
            read(file);

            swap(first_line_index, first_line_word_index, second_line_index, second_line_word_index);

            write(file);
        }
    }

    public static boolean checkInputIndexes(int lineIndex, int lineWordIndex){
        if(lineIndex <= list.size()-1 && lineWordIndex <= list.get(lineIndex).length-1){
            return false;
        }

        return true;
    }

    //swap words on according to a given index of the row and column where the words are located
    private static void swap(int i1, int j1, int i2, int j2) {
        String temp = list.get(i1)[j1];
        list.get(i1)[j1] = list.get(i2)[j2];
        list.get(i2)[j2] = temp;
    }

    //read file and fill in the list
    private static void read(File file) throws IOException {
        String[] words;  //Initialize the word Array
        FileReader fr = new FileReader(file);  //Creation of File Reader object
        BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
        String s;
        // Input word to be searched
        while ((s = br.readLine()) != null)   //Reading Content from the file
        {
            words = s.split(" ");
            list.add(words);//Split the word using space
        }
        fr.close();
    }

    //write into file
    private static void write(File file) throws IOException {
        FileWriter writer = new FileWriter(file);

        for(int j = 0;j<list.size();j++) {
            for(int i = 0;i<list.get(j).length;i++) {
                if(i==list.get(j).length-1 && j==list.size()-1) {
                    writer.write(list.get(j)[i]);
                }
                else if(i==list.get(j).length-1) {
                    writer.write(list.get(j)[i]+"\n");
                }
                else {
                    writer.write(list.get(j)[i]+" ");
                }
            }
        }
        writer.close();
    }

}
