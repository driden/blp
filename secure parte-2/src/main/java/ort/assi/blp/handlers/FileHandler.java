package ort.assi.blp.handlers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.BitSet;

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

    private byte[] fileBytes = null;

    public BitSet readBinaryFile(String path) throws IOException {
        // TODO: check que exista el file
        fileBytes = Files.readAllBytes(Path.of(path));
        return BitSet.valueOf(fileBytes);
    }
}
