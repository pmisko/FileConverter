package pl.sda.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pl.sda.converter.SDAFileWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PdfWrite implements SDAFileWriter {
    @Override
    public void write(List<Map<String, Object>> records, String outputFilePath) {

        Document doc = new Document();
        PdfWriter docWriter = null;
        try {
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(outputFilePath));
            doc.open();

            int size = records.get(0).size();
            PdfPTable table = new PdfPTable(size);
            table.setWidthPercentage(90f);

            List<String> keyNames = new ArrayList<>(records.get(0).keySet());
            for (int i = 0; i < size; i++) {
                table.addCell(keyNames.get(i));
            }

            for (Map<String, Object> record : records) {
                Collection<Object> values = record.values();
                List<Object> collectedValues = new ArrayList<>(values);
                for (int i = 0; i < values.size(); i++) {
                    table.addCell(collectedValues.get(i).toString());
                }
            }
            doc.add(table);
            doc.close();
        } catch (Exception dex) {
            dex.printStackTrace();
        } finally {
            doc.close();
            if (docWriter != null) {
                docWriter.close();
            }
        }
    }
}