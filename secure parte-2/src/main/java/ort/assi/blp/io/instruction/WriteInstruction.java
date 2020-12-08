package ort.assi.blp.io.instruction;

import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;

public class WriteInstruction extends Instruction {
    public WriteInstruction(SysSubject subject, SysObject object, Integer objectValue) {
        super.type = InstructionType.WRITE;
        super.object = object;
        super.subject = subject;
        super.objectValue = objectValue;
    }

    @Override
    public boolean equals(Object o) {
        Instruction that = (Instruction) o;
        return super.equals(o) &&
                getObjectValue().equals(that.getObjectValue());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Integer execute(SysSubject subject, SysObject object, ObjectManager manager) {
        if (!canDo(subject, object, manager)) return 0;
        logMessage();
        return subject.writeObject(object, objectValue);
    }

    @Override
    public String getMessage() {
        return  String.format("WRITE %s %s %s\n", subject.getName(), object.getName(), objectValue);
    }

    private Boolean canDo(SysSubject subject, SysObject object, ObjectManager manager) {
        return object.getSecurityTag().dominates(subject.getClearance());
    }
}
