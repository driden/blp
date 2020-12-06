package ort.assi.blp.io.instruction;

import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;

public class DestroyInstruction extends Instruction {
    public DestroyInstruction(SysSubject subject, SysObject object){
        this.object = object;
        this.subject = subject;
        this.type = InstructionType.DESTROY;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
