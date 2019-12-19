package pl.sda.converter.json;

import org.json.JSONArray;
import pl.sda.converter.SDAFileWriter;
import pl.sda.converter.exceptions.FileConverterException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonWriter implements SDAFileWriter {

    @Override
    public void write(List<Map<String, Object>> records, String outputFilePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath))) {
            JSONArray recordsToSave = new JSONArray();
            for (Map<String, Object> record : records) {
                recordsToSave.put(record);
            }
            bufferedWriter.write(recordsToSave.toString(2));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new FileConverterException("Error has occurred while saving the file", e);
        }
    }
}