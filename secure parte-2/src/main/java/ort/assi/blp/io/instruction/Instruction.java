package ort.assi.blp.io.instruction;

import org.apache.commons.io.FileUtils;
import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Objects;

public abstract class Instruction {
    InstructionType type;
    SysObject object;
    SysSubject subject;
    Integer objectValue;

    public InstructionType getType() {
        return type;
    }

    public SysObject getObject() {
        return object;
    }

    public SysSubject getSubject() {
        return subject;
    }

    public Integer getObjectValue() {
        return objectValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return getType() == that.getType() &&
                getObject().equals(that.getObject()) &&
                getSubject().equals(that.getSubject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getObject(), getSubject(), getObjectValue());
    }

    public abstract Integer execute(SysSubject subject, SysObject object, ObjectManager manager);
    public abstract String getMessage();
    public void logMessage() {
        try{
            Path logPath = Path.of("log.txt");
            File logFile = new File(logPath.toString());
            if (!Files.exists(logPath)) {
                logFile.createNewFile();
            }
            FileWriter fr = new FileWriter(logFile, true);
            fr.write(getMessage());
            fr.close();
        }catch (IOException ignored){
            System.out.println("could not write to log");
        }
    }
}
