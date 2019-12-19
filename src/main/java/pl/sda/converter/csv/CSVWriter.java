package pl.sda.converter.csv;

import pl.sda.converter.SDAFileWriter;
import pl.sda.converter.exceptions.FileConverterException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CSVWriter implements SDAFileWriter {

    @Override
    public void write(List<Map<String, Object>> records, String outputFilePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath))) {
            Set<String> headers = getHeaders(records);
            String headerRow = buildHeaderRow(headers);
            bufferedWriter.write(headerRow);
            bufferedWriter.newLine();

            for (Map<String, Object> record : records) {
                String row = buildRow(record, headers);
                bufferedWriter.write(row);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new FileConverterException("Błąd w trakcie zapisu pliku", e);
        }
    }

    private String buildRow(Map<String, Object> record, Set<String> headers) {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        for (String header : headers) {
            String currentCell = record.containsKey(header) ? record.get(header).toString() : "";
            stringBuilder.append(currentCell);
            if (index < headers.size()) {
                stringBuilder.append(";");
            }
            index++;
        }
        return stringBuilder.toString();
    }

    private String buildHeaderRow(Set<String> headers) {
        StringBuilder headerRow = new StringBuilder();
        int index = 1;
        for (String header : headers) {
            headerRow.append(header);
            if (index < headers.size()) {
                headerRow.append(";");
            }
            index++;
        }
        return headerRow.toString();
    }

    private Set<String> getHeaders(List<Map<String, Object>> records) {
        Set<String> headers = new LinkedHashSet<>();
        for (Map<String, Object> record : records) {
            headers.addAll(record.keySet());
        }
        return headers;
    }
}