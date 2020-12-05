package ort.assi.blp.handlers;

import ort.assi.blp.io.InstructionObject;
import ort.assi.blp.io.InstructionParser;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public static ArrayList<InstructionObject> readFile(String path) throws IOException {
        var result = new ArrayList<InstructionObject>();
        var file = new File(path);
        var bufferReader = new BufferedReader(new FileReader(file));
        String line;
        var instructionParser = new InstructionParser();
        while ((line = bufferReader.readLine()) != null) {
            var instructionObject = instructionParser.parse(line);
            result.add(instructionObject);
        }
        return result;
    }
}
