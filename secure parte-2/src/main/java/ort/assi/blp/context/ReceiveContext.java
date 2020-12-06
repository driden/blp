package ort.assi.blp.context;

import java.io.File;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.BitSet;

public class ReceiveContext {
    private BitSet bits;
    private Integer currentBit;
    private String filePath;

    public ReceiveContext(String filePath){
        bits = new BitSet();
        currentBit = 0;
        this.filePath = filePath;
    }

    public void receive(boolean value){
        bits.set(currentBit, value);
        currentBit++;
    }

    public void writeFile(){
        try{
            FileUtils.writeByteArrayToFile(new File(filePath), bits.toByteArray());
        } catch (IOException ignored){

        }
    }

}
