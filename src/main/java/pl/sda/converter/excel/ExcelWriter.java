package pl.sda.converter.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.sda.converter.SDAFileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelWriter implements SDAFileWriter {
    private boolean isHeadersRow = true;

    @Override
    public void write(List<Map<String, Object>> records, String outputFilePath) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("excelWriter");
        Set<String> keys = records.get(0).keySet();
        List<Object> keyList = keys.stream().map(k -> (Object) k).collect(Collectors.toList());
        for (Object k : keyList) {
            System.out.println(k);
        }

        for (int i = 0; i < records.size() + 1; i++) {
            XSSFRow row = sheet.createRow(i);

            if (isHeadersRow) {
                for (int j = 0; j < keyList.size(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(keyList.get(j).toString());
                }
                isHeadersRow = false;
            } else {
                Collection<Object> values = records.get(i - 1).values();
                List<Object> collectedValues = new ArrayList<>(values);
                for (int k = 0; k <= collectedValues.size() - 1; k++) {
                    Cell cell = row.createCell(k);
                    cell.setCellValue(collectedValues.get(k).toString());
                }
            }
        }
        try (FileOutputStream fileToSave = new FileOutputStream(new File(outputFilePath))) {
            workbook.write(fileToSave);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}