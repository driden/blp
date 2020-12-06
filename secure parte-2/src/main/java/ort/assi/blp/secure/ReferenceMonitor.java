package ort.assi.blp.secure;

import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;
import ort.assi.blp.io.instruction.Instruction;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ReferenceMonitor {

    private final ObjectManager objectManager = new ObjectManager();
    private final HashMap<String, SysSubject> subjects = new HashMap<>();

    public Integer executeInstruction(Instruction instruction) {
        SysObject object = instruction.getObject();
        SysSubject subject = instruction.getSubject();
        switch (instruction.getType()) {
            case WRITE:
                if (canWriteObject(subject, object))
                    return executeWrite(subject, object, instruction.getObjectValue());
                else
                    return 0;
            case READ:
                if (canReadObject(subject, object))
                    return executeRead(subject, object);
                else
                    return 0;
            case CREATE:
                return executeCreateObject(subject, object);
            case DESTROY:
                return executeDestroyObject(subject, object);
            case RUN:
                return runSubject(subject);
            default:
                return 0;
        }
    }

    private Boolean canWriteObject(SysSubject subject, SysObject object) {
        return object.getSecurityTag().dominates(subject.getClearance());
    }

    private Boolean canReadObject(SysSubject subject, SysObject object) {
        return subject.getClearance().dominates(object.getSecurityTag());
    }

    private Integer executeRead(SysSubject subject, SysObject object) {
        return subject.readObject(object);
    }

    private Integer executeWrite(SysSubject subject, SysObject object, Integer objectValue) {
        return subject.writeObject(object, objectValue);
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

    public void createNewObject(String name, SecurityLevel level) {
        this.objectManager.createObject(name, level);
    }

    public List<SysSubject> getAllSubjects() {
        return subjects.values().stream().collect(Collectors.toUnmodifiableList());
    }

    public List<SysObject> getAllObjects() {
        return this.objectManager.getAllObjects();
    }

    private Integer executeCreateObject(SysSubject subject, SysObject object) {
        if (!existsObject(object.getName())) {
            this.objectManager.createObject(object.getName(), subject.getClearance());
            return 1;
        }
        return 0;
    }

    private Integer executeDestroyObject(SysSubject subject, SysObject object) {
        if (!object.getSecurityTag().dominates(subject.getClearance()) || !existsObject(object.getName()))
            return 0;

        this.objectManager.destroyObject(object);
        return 1;
    }

    private Integer runSubject(SysSubject subject) {
        subject.run();
        return -1;
    }
}
