package ort.assi.blp.io.instruction;

import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;

public class CreateInstruction extends Instruction {
    public CreateInstruction(SysSubject subject, SysObject object) {
        super.object = object;
        super.subject = subject;
        this.type = InstructionType.CREATE;
    }

    @Override
    public Integer execute(SysSubject subject, SysObject object, ObjectManager manager) {
        if (!canDo(subject, object, manager)) return 0;
        manager.createObject(object.getName(), subject.getClearance());
        return 1;

    }

    private Boolean canDo(SysSubject subject, SysObject object, ObjectManager manager) {
        return !manager.existsObject((object.getName()));
    }
}
