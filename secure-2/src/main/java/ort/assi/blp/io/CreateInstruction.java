package ort.assi.blp.io;

import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;

public class CreateInstruction extends Instruction {
   public CreateInstruction(SysSubject subject, SysObject object){
       super.object = object;
       super.subject = subject;
       this.type = InstructionType.CREATE;
   }
    @Override
    public String getMessage() {
        return null;
    }
}
