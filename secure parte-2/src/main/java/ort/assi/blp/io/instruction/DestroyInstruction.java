package ort.assi.blp.io.instruction;

import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;

public class DestroyInstruction extends Instruction {
    public DestroyInstruction(SysSubject subject, SysObject object) {
        this.object = object;
        this.subject = subject;
        this.type = InstructionType.DESTROY;
    }

    @Override
    public Integer execute(SysSubject subject, SysObject object, ObjectManager manager) {
        var storedObj = manager.getObject(object.getName());
        if (!canDo(subject,object,manager)) return 0;

        manager.destroyObject(storedObj);
        return 1;
    }

    private Boolean canDo(SysSubject subject, SysObject object, ObjectManager manager) {
        var storedObj = manager.getObject(object.getName());
        return (storedObj != null && storedObj.getSecurityTag().dominates(subject.getClearance()));
    }
}
