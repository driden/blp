package ort.assi.blp.secure;

import ort.assi.blp.entities.SysSubject;
import ort.assi.blp.io.InstructionObject;

import java.util.HashMap;

public class SecureSystem {
    private final ReferenceMonitor referenceMonitor;

    public InstructionObject getParser() {
        return parser;
    }

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
}
