package ort.assi.blp.entities;

import ort.assi.blp.io.InstructionObject;

import java.util.HashMap;
import java.util.List;

public class ObjectManager {
    private final HashMap<String, SysSubject> subjects = new HashMap<>();
    private final HashMap<String, SysObject> objects = new HashMap<>();


    public SysSubject getSubject(String subjectName){
        if(!subjects.containsKey(subjectName)){
            SysSubject subj = new SysSubject(subjectName);
            this.subjects.put(subjectName, subj);
        }
        return subjects.get(subjectName);
    }

    public SysObject getObject(String objectName){
        if(!objects.containsKey(objectName)){
            SysObject obj = new SysObject(objectName);
            this.objects.put(objectName, obj);
        }
        return objects.get(objectName);
    }
}
