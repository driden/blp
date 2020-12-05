package ort.assi.blp.io;

import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;

public class ReadInstruction extends Instruction {
    public ReadInstruction(SysSubject subject, SysObject object) {
        super.type = InstructionType.READ;
        super.object = object;
        super.subject = subject;
    }
}
