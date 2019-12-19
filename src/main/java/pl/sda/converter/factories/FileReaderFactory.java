package pl.sda.converter.factories;

import pl.sda.converter.SDAFileReader;
import pl.sda.converter.csv.CSVReader;
import pl.sda.converter.excel.ExcelReader;
import pl.sda.converter.json.JsonReader;
import pl.sda.pdf.PdfReaderClass;

public class FileReaderFactory {
    public SDAFileReader produce(String filePath) {
        if (filePath.endsWith(".csv")) {
            return new CSVReader();
        }
        if (filePath.endsWith(".json")) {
            return new JsonReader();
        }
        if (filePath.endsWith(".xlsx")) {
            return new ExcelReader();
        }
        if (filePath.endsWith(".pdf")) {
            return new PdfReaderClass();
        }
        return null;
    }
}