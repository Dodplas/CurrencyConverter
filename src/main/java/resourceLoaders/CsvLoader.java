package resourceLoaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvLoader {

    public static List<List<String>> loadCsv(String filePath) throws IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                records.add(Arrays.stream(values)
                                    .map(String::trim)
                                    .collect(Collectors.toList()));
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(String.format("File: %s not found", filePath));
        } catch (IOException e) {
            throw new IOException(String.format("Error while reading file: %s", filePath));
        }

        return records;
    }
}
