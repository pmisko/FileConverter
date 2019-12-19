package pl.sda.converter.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.sda.converter.SDAFileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelReader implements SDAFileReader {
    @Override
    public List<Map<String, Object>> read(String filePath) {

        List<Map<String, Object>> result = new ArrayList<>();
        List<String> headers = new LinkedList<>();

        try {
            FileInputStream file = new FileInputStream(new File(filePath));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            Row firstRow = rowIterator.next();
            Iterator<Cell> cellIterator = firstRow.cellIterator();

            while (cellIterator.hasNext()) {
                headers.add(cellIterator.next().getStringCellValue());
            }

            while (rowIterator.hasNext()) {
                int i = 0;
                Iterator<Cell> cellInRow = rowIterator.next().cellIterator();

                Map<String, Object> singleRecord = new LinkedHashMap<>();
                while (cellInRow.hasNext()) {
                    if (headers.get(i).equals("name")) {
                        singleRecord.put("name", cellInRow.next().getStringCellValue());
                    }
                    if (headers.get(i).equals("lastName")) {
                        singleRecord.put("lastName", cellInRow.next().getStringCellValue());
                    }
                    if (headers.get(i).equals("age")) {
                        singleRecord.put("age", cellInRow.next().getNumericCellValue());
                    }
                    if (headers.get(i).equals("salary")) {
                        singleRecord.put("salary", cellInRow.next().getNumericCellValue());
                    }
                    i++;
                }
                result.add(singleRecord);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}