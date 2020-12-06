package ort.assi.blp.secure;

import ort.assi.blp.covertchannel.SequenceHandler;
import ort.assi.blp.entities.SysSubject;
import ort.assi.blp.io.instruction.Instruction;
import ort.assi.blp.io.InstructionObject;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.stream.Collectors;

public class SecureSystem {
    private final ReferenceMonitor referenceMonitor;

    private InstructionObject parser;
    private final HashMap<String, SysSubject> subjects = new HashMap<>();
    private final SequenceHandler sequenceHandler;
    private final BitSet bits;

    public SecureSystem(SequenceHandler sequenceHandler, BitSet bitsToTransfer) {
        referenceMonitor = new ReferenceMonitor();
        parser = new InstructionObject(referenceMonitor);
        this.sequenceHandler = sequenceHandler;
        this.bits = bitsToTransfer;
    }

    public void createSubject(String name, SecurityLevel clearance) {
        SysSubject subj = new SysSubject(name, clearance);
        this.subjects.put(name, subj);
        this.referenceMonitor.addSubject(subj);
    }

    public void createNewObject(String name, SecurityLevel tag) {
        this.referenceMonitor.createNewObject(name, tag);
    }

    public SysSubject getSubject(String subjectName) {
        return subjects.get(subjectName);
    }

    public void printState(Instruction instruction) {
        System.out.println(instruction.getMessage());
        System.out.println("The current state is:");
        referenceMonitor.getAllObjects().forEach(object -> System.out.println("\t" + object.getStatusMessage()));
        referenceMonitor.getAllSubjects().forEach(subject -> System.out.println("\t" +subject.getStatusMessage()));
        System.out.println();
    }

    public void run(ArrayList<String> readFile, String filename) {
        var instructions = readFile
                .stream()
                .map(parser::parse)
                .collect(Collectors.toList());
    }
}
