package ort.assi.blp.handlers;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public static ArrayList<String> readFile(String path) throws IOException {
        var result = new ArrayList<String>();
        var file = new File(path);
        var bufferReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferReader.readLine()) != null) {
            result.add(line);
        }
        return result;
    }
}
