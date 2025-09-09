package com.example.oms.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    public static List<String[]> read(MultipartFile file) throws Exception {
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVReader csv = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
            List<String[]> rows = new ArrayList<>();
            String[] line;
            while ((line = csv.readNext()) != null) {
                rows.add(line);
            }
            return rows;
        }
    }

    public static String required(String[] row, int idx, String field){
        if (idx >= row.length || row[idx] == null || row[idx].trim().isEmpty()){
            throw new IllegalArgumentException("Missing required field: " + field);
        }
        return row[idx].trim();
    }

    public static String optional(String[] row, int idx){
        if (idx >= row.length || row[idx] == null) return null;
        return row[idx].trim().isEmpty() ? null : row[idx].trim();
    }
}
