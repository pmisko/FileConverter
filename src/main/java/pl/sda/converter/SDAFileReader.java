package pl.sda.converter;

import java.util.List;
import java.util.Map;

public interface SDAFileReader {
    List<Map<String, Object>> read(String filePath);
}
