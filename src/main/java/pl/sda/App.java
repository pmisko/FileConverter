package pl.sda;

import pl.sda.converter.FileConverter;

public class App {
    public static void main(String[] args) {
        FileConverter converter = new FileConverter();

//Please enter input and output file path
        String sourceFilePath = "<INPUT_FILE_PATH>";
        String outputFilePath = "OUTPUT_FILE_PATH";
        converter.convert(sourceFilePath, outputFilePath);
        int i = 0;
    }
}
