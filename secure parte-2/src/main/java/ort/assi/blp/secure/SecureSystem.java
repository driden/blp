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

    private InstructionObject parser;
    private final HashMap<String, SysSubject> subjects = new HashMap<>();
    private final SequenceHandler sequenceHandler;
    private final BitSet bits;
    public static final String SYNC_OBJ = "objSync";

    public SecureSystem(SequenceHandler sequenceHandler, BitSet bitsToTransfer) {
        referenceMonitor = new ReferenceMonitor();
        parser = new InstructionObject(referenceMonitor);
        this.sequenceHandler = sequenceHandler;
        this.bits = bitsToTransfer;
    }

    public void createSubject(String name, SecurityLevel clearance, Supplier<Integer> function) {
        SysSubject subj = new SysSubject(name, clearance, function);
        this.subjects.put(name, subj);
        this.referenceMonitor.addSubject(subj);
    }

    public SysSubject getSubject(String subjectName) {
        return subjects.get(subjectName);
    }

    public void printState(Instruction instruction) {
        System.out.println(instruction.getMessage());
        System.out.println("The current state is:");
        referenceMonitor.getAllObjects().forEach(object -> System.out.println("\t" + object.getStatusMessage()));
        referenceMonitor.getAllSubjects().forEach(subject -> System.out.println("\t" + subject.getStatusMessage()));
        System.out.println();
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
                    hal.run();
                    break;
                case 'L':
                    break;
                default:
                    ;
            }
        }
    }

    public Instruction[] sendBit0() {
        var hal = getSubject("hal");

        return new Instruction[]{
                new RunInstruction(hal),
                new CreateInstruction(hal, new SysObject("obj"))
        };
    }

    public Instruction[] sendBit1() {
        var hal = getSubject("hal");

        return new Instruction[]{
                new RunInstruction(hal),
        };
    }

    public Instruction[] readBit() {
        var lyle = getSubject("lyle");
        SysObject obj = new SysObject("obj");
        return new Instruction[]{
                new CreateInstruction(lyle, obj),
                new WriteInstruction(lyle, obj, 1),
                new DestroyInstruction(lyle, obj),
                new RunInstruction(lyle),
                new CreateInstruction(lyle, obj),
        };
    }
}
