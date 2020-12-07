package ort.assi.blp.io.instruction;

import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;

public class ReadInstruction extends Instruction {
    public ReadInstruction(SysSubject subject, SysObject object) {
        super.type = InstructionType.READ;
        super.object = object;
        super.subject = subject;
    }

    @Override
    public Integer execute(SysSubject subject, SysObject object, ObjectManager manager) {
        return subject.readObject(object);
    }

}
