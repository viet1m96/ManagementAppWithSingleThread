package file_processing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private BufferedWriter fw;
    private String fileName;

    public CSVWriter(String fileName) {
        this.fileName = fileName;
    }

    public void setPointer() {
        try {
            fw = new BufferedWriter(new FileWriter(fileName, false));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void writeToCSV(String[] org) {
        String text = "";
        for(int i = 0; i < org.length; i++) {
            text += org[i];
            if(i != org.length - 1) {
                text += ",";
            } else {
                text += "\n";
            }
        }
        try {
            fw.write(text);
            fw.flush();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void shutDown() {
        try {
            if(fw != null) fw.close();
        } catch(IOException e) {
            System.out.println(e.toString());
        }
    }
}
