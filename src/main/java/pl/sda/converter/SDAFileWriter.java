package pl.sda.converter;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface SDAFileWriter {
    void write(List<Map<String, Object>> records, String outputFilePath) throws FileNotFoundException, DocumentException;
}
