package ort.assi.blp.entities;

import ort.assi.blp.io.InstructionObject;

import java.util.HashMap;
import java.util.List;

public class ObjectManager {
    private final HashMap<String, SysSubject> subjects = new HashMap<>();
    private final HashMap<String, SysObject> objects = new HashMap<>();
    private static ObjectManager instance = null;

    private ObjectManager() {
    }

    public static ObjectManager getInstance() {
        if (instance == null) instance = new ObjectManager();
        return instance;
    }

    public void executeInstructions(List<InstructionObject> instructions){
        for (InstructionObject instruction: instructions ) {
           switch(instruction.getType()) {
               case WRITE:
                    writeObject(instruction.getSubjectName(), instruction.getObjectName(), instruction.getObjectValue());
               case READ:
                   readObject(instruction.getSubjectName(), instruction.getObjectName());
               case BAD:
               default:
           }
        }
    }

    private void readObject(String subjectName, String objectName) {
        SysSubject subject = getSubject(subjectName);
        SysObject obj = getObject(objectName);
        subject.readObject(obj);
    }

    private void writeObject(String subjectName, String objectName, Integer objectValue) {
        SysObject object = getObject(objectName);
        SysSubject subject = getSubject(subjectName);
        subject.writeObject(object, objectValue);
    }

    private SysSubject getSubject(String subjectName){
        if(!subjects.containsKey(subjectName)){
            SysSubject subj = new SysSubject(subjectName);
            this.subjects.put(subjectName, subj);
        }
        return subjects.get(subjectName);
    }

    private SysObject getObject(String objectName){
        if(!objects.containsKey(objectName)){
            SysObject obj = new SysObject(objectName);
            this.objects.put(objectName, obj);
        }
        return objects.get(objectName);
    }
}
