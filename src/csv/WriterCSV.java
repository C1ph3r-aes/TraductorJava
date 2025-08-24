package csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterCSV {

    public WriterCSV() {
    }

    public void writeToCSV(String filename, String filePath, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            int lastDotIndex = fileName.lastIndexOf('.');
            String realFileName = fileName.substring(0, lastDotIndex);

            writer.write(filePath + "," + realFileName);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
