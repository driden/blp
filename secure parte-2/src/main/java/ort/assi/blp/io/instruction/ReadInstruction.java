package ort.assi.blp.io.instruction;

import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;

public class ReadInstruction extends Instruction {
    public ReadInstruction(SysSubject subject, SysObject object) {
        super.type = InstructionType.READ;
        super.object = object;
        super.subject = subject;
    }


    @Override
    public String getMessage() {
        return super.subject.getName() + " reads " + super.object.getName();
    }
}
