package ort.assi.blp.io.instruction;

import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;

public class RunInstruction extends Instruction {
    public RunInstruction(SysSubject subject){
        this.subject = subject;
        this.type = InstructionType.RUN;
    }

    @Override
    public Integer execute(SysSubject subject, SysObject object, ObjectManager manager) {
        logMessage();
        return -1;
    }

    @Override
    public String getMessage() {
        return String.format("RUN %s\n", subject.getName());
    }


}
