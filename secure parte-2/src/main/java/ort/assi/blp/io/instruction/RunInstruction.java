package ort.assi.blp.io.instruction;

import ort.assi.blp.entities.SysSubject;

public class RunInstruction extends Instruction {
    public RunInstruction(SysSubject subject){
        this.subject = subject;
        this.type = InstructionType.RUN;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
