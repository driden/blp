package ort.assi.blp.secure;

import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;
import ort.assi.blp.io.InstructionObject;

import java.util.List;

public class ReferenceMonitor {
    private final ObjectManager objectManager = new ObjectManager();

    public void executeInstructions(List<InstructionObject> instructions) {
        for (InstructionObject instruction : instructions) {
            SysObject object = objectManager.getObject(instruction.getObjectName());
            SysSubject subject = objectManager.getSubject(instruction.getSubjectName());
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

    public void executeRead(SysSubject subject, SysObject object) {
        subject.readObject(object);
    }

    public void executeWrite(SysSubject subject, SysObject object, Integer objectValue) {
        subject.writeObject(object, objectValue);
    }
}
