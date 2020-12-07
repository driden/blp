package ort.assi.blp.io.instruction;

import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;

public class BadInstruction extends Instruction {
    public BadInstruction() {
        type = InstructionType.BAD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return getType() == that.getType();
    }

    @Override
    public String getMessage() {
        return "Bad Instruction";
    }

    @Override
    public Integer execute(SysSubject subject, SysObject object, ObjectManager manager) {
        return -1;
    }

    @Override
    public Boolean canDo(SysSubject subject, SysObject object, ObjectManager manager) {
        return false;
    }

}
