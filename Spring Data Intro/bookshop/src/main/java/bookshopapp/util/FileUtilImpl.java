package bookshopapp.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtilImpl implements FileUtil {


    @Override
    public String[] getFileContent(String filePath) throws IOException {
        File file = new File(filePath);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> allLines = new ArrayList<>();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            allLines.add(line);
        }
        return allLines.stream().filter(l -> !l.equals("")).toArray(String[]::new);
    }
}
