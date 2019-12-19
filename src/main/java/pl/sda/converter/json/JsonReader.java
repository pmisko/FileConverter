package pl.sda.converter.json;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.sda.converter.SDAFileReader;
import pl.sda.converter.exceptions.FileConverterException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonReader implements SDAFileReader {

    @Override
    public List<Map<String, Object>> read(String filePath) {
        List<Map<String, Object>> result = new ArrayList<>();

        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            String content = new String(bytes, StandardCharsets.UTF_8);

            JSONArray records = new JSONArray(content);

            for (int i = 0; i < records.length(); i++) {
                Object recordRaw = records.get(i);
                JSONObject record = new JSONObject(recordRaw.toString());

                Map<String, Object> parsedRecord = new LinkedHashMap<>();
                for (String key : record.keySet()) {
                    parsedRecord.put(key, record.get(key));
                }
                result.add(parsedRecord);
            }
        } catch (IOException e) {
            throw new FileConverterException("Error has occurred while processing the file", e);
        }
        return result;
    }
}