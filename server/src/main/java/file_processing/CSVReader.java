package file_processing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class CSVReader {
    private BufferedReader br;

    public CSVReader(String filename) {
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    public Queue<String[]> getDataFromFile() {
        String line = "";
        String splitBy = ",";
        Queue<String[]> data = new ArrayDeque<>();
        try {
            while((line = br.readLine()) != null) {
                String[] tmp = line.split(splitBy);
                data.add(tmp);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return data;
    }
}
