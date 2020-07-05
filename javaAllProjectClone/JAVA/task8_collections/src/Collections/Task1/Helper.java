package Collections.Task1;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Helper {
    public static ArrayList<String> inputData(String addressFile) throws IOException {
        FileReader reader = new FileReader(addressFile);
        Scanner sc = new Scanner(reader);
        ArrayList<String> listOfWords = new ArrayList<>();

        String allLines = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            allLines += line + " ";
        }

        String[] lines = allLines.replaceAll("[\\s,.;:%\\[\\]\\-\\—+«»\"(0-9)]", " ").split("\\s+");

        for (String s : lines) {
            listOfWords.add(s);
        }

        reader.close();
        return listOfWords;
    }
}
