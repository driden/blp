package ort.assi.blp.io;

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
    public String getMessage() {
        return subject.getName() + " writes value " + object.getName() + " to " + object.getName();
    }

}
