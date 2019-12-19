package pl.sda.pdf;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import pl.sda.converter.SDAFileReader;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PdfReaderClass implements SDAFileReader {

    @Override
    public List<Map<String, Object>> read(String filePath) {

        List<Map<String, Object>> result = new ArrayList<>();
        com.itextpdf.text.pdf.PdfReader pdfReader;

        try {

            pdfReader = new com.itextpdf.text.pdf.PdfReader(filePath);

            String textFromPage = PdfTextExtractor.getTextFromPage(pdfReader, 1);
            System.out.println(textFromPage);

            int firstLineEndIndex = textFromPage.indexOf("\n");
            String firstLine = textFromPage.substring(0, firstLineEndIndex);
            String[] headers = firstLine.split(" ");
            String restOfText = textFromPage.substring(firstLineEndIndex).trim();
            String[] splitedLines = restOfText.split("\n");

            for (int i=0; i<splitedLines.length; i++) {
                Object[] splitedTokens = splitedLines[i].split(" ");
                Map<String, Object> record = new LinkedHashMap<>();
                for (int j=0; j< headers.length;j++) {
                    record.put(headers[j], splitedTokens[j] );
                }
                result.add(record);
            }
            pdfReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
