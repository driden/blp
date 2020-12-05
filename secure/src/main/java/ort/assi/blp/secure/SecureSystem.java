package ort.assi.blp.secure;

import ort.assi.blp.entities.SysSubject;
import ort.assi.blp.handlers.FileHandler;
import ort.assi.blp.io.Instruction;
import ort.assi.blp.io.InstructionObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class SecureSystem {
    private final ReferenceMonitor referenceMonitor;

    private InstructionObject parser;
    private final HashMap<String, SysSubject> subjects = new HashMap<>();

    public SecureSystem(){
        referenceMonitor = new ReferenceMonitor();
        parser = new InstructionObject(referenceMonitor);
    }

    public void createSubject(String name, SecurityLevel clearance){
        SysSubject subj = new SysSubject(name, clearance);
        this.subjects.put(name,subj );
        this.referenceMonitor.addSubject(subj);
    }

    public void createNewObject(String name, SecurityLevel tag){
        this.referenceMonitor.createNewObject(name, tag);
    }

    public SysSubject getSubject(String subjectName) {
        return subjects.get(subjectName);
    }

    public void printState(Instruction instruction){
        System.out.println(instruction.getMessage());
        System.out.println("The current state is:");
        //TODO acceder a los objetos e imprimir mensaje (método en el objeto getStatusMessage())
        //TODO acceder a los sujetos e imprimir mensaje (método en el sujeto getStatusMessage())
    }

    public void run(ArrayList<String> readFile) {
        var instructions = readFile
                .stream()
                .map(parser::parse)
                .collect(Collectors.toList());

        System.out.println("Processing instructions... ");
        for (var instruction : instructions){
            referenceMonitor.executeInstruction(instruction);
            printState(instruction);
        }
    }
}
