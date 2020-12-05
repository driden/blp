package ort.assi.blp.secure;

import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;
import ort.assi.blp.io.Instruction;

import java.util.HashMap;
import java.util.List;

public class ReferenceMonitor {

    private final ObjectManager objectManager = new ObjectManager();
    private final HashMap<String, SysSubject> subjects = new HashMap<>();

    public void executeInstructions(List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            SysObject object = instruction.getObject();
            SysSubject subject = instruction.getSubject();
            switch (instruction.getType()) {
                case WRITE:
                    if (canWriteObject(subject, object)) executeWrite(subject, object, instruction.getObjectValue());
                case READ:
                    if (canReadObject(subject, object)) executeRead(subject, object);
                case BAD:
                default:
            }
        }
    }

    private Boolean canWriteObject(SysSubject subject, SysObject object) {
        return object.getSecurityTag().dominates(subject.getClearance());
    }

    private Boolean canReadObject(SysSubject subject, SysObject object) {
        return subject.getClearance().dominates(object.getSecurityTag());
    }

    public Integer executeRead(SysSubject subject, SysObject object) {
        return subject.readObject(object);
    }

    public Integer executeWrite(SysSubject subject, SysObject object, Integer objectValue) {
        return subject.writeObject(object, objectValue);
    }

    public Boolean existsObject(String name){
        return this.objectManager.existsObject(name);
    }

    public SysObject getObject(String name){
        return this.objectManager.getObject(name);
    }

    public Boolean existsSubject(String subjectName) {
        return subjects.containsKey(subjectName);
    }

    public SysSubject getSubject(String name){
        return this.subjects.get(name);
    }

    public void addSubject(SysSubject subject){
        this.subjects.put(subject.getName(), subject);
    }

    public void createNewObject(String name, SecurityLevel level){
        this.objectManager.createObject(name,level);
    }
}
