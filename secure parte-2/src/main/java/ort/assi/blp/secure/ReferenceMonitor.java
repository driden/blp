package ort.assi.blp.secure;

import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;
import ort.assi.blp.io.instruction.Instruction;

import java.io.IOException;
import java.util.HashMap;

public class ReferenceMonitor {

    private final ObjectManager objectManager = new ObjectManager();
    private final HashMap<String, SysSubject> subjects = new HashMap<>();

    public Integer executeInstruction(Instruction instruction) {
        SysObject object = instruction.getObject();
        SysSubject subject = instruction.getSubject();
        return instruction.execute(subject, object, this.objectManager);
    }

    public Boolean existsObject(String name) {
        return this.objectManager.existsObject(name);
    }

    public SysObject getObject(String name) {
        return this.objectManager.getObject(name);
    }

    public Boolean existsSubject(String subjectName) {
        return subjects.containsKey(subjectName);
    }

    public SysSubject getSubject(String name) {
        return this.subjects.get(name);
    }

    public void addSubject(SysSubject subject) {
        this.subjects.put(subject.getName(), subject);
    }
}
