package ort.assi.blp.io.instruction;

import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;

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

    public void setObject(SysObject object) {
        this.object = object;
    }

    public SysSubject getSubject() {
        return subject;
    }

    public void setSubject(SysSubject subject) {
        this.subject = subject;
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

    public abstract String getMessage();
    public abstract Integer execute(SysSubject subject, SysObject object, ObjectManager manager);
    public abstract Boolean canDo(SysSubject subject, SysObject object, ObjectManager manager);
}
