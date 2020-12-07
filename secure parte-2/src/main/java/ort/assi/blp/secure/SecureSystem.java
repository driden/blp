package ort.assi.blp.secure;

import ort.assi.blp.covertchannel.SequenceHandler;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;
import ort.assi.blp.io.instruction.*;
import ort.assi.blp.io.InstructionObject;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SecureSystem {
    private final ReferenceMonitor referenceMonitor;

    private final HashMap<String, SysSubject> subjects = new HashMap<>();
    private final SequenceHandler sequenceHandler;
    public static final String SYNC_OBJ = "objSync";
    public static final String TRANSFER_OBJ = "obj";

    public SecureSystem(SequenceHandler sequenceHandler) {
        referenceMonitor = new ReferenceMonitor();
        this.sequenceHandler = sequenceHandler;
    }

    public SysSubject createSubject(String name, SecurityLevel clearance) {
        SysSubject subj = new SysSubject(name, clearance);
        this.subjects.put(name, subj);
        this.referenceMonitor.addSubject(subj);
        return subj;
    }

    public SysSubject getSubject(String subjectName) {
        return subjects.get(subjectName);
    }

    public void run() {
        var hal = getSubject("hal");
        var lyle = getSubject("lyle");

        char c;

        while ((c = sequenceHandler.getNextSubject()) != ' ') {
            switch (c) {
                case 'H':
                    var createResult = referenceMonitor.executeInstruction(
                            new CreateInstruction(hal, new SysObject(SYNC_OBJ)));
                    hal.setCanAct(createResult == 1);
                    if (hal.run() == 1) {
                        referenceMonitor.executeInstruction(
                                new DestroyInstruction(hal, new SysObject(SYNC_OBJ)));
                    }
                    break;
                case 'L':
                    var destroyResult = referenceMonitor.executeInstruction(
                            new DestroyInstruction(lyle, new SysObject(SYNC_OBJ)));
                    lyle.setCanAct(destroyResult == 1);
                    lyle.run();
                    break;
                default:
            }
        }
    }

    public Integer execute(Instruction instruction) {
        return this.referenceMonitor.executeInstruction(instruction);
    }
}
