package ort.assi.blp.entities;

import java.util.Set;

public class Context {
    Set<SysObject> objects;
    Set<SysSubject> subjects;

    public Context (Set<SysObject> objects, Set<SysSubject> subjects){
        this.objects = objects;
        this.subjects = subjects;
    }

    public Set<SysObject> getObjects() {
        return objects;
    }

    public Set<SysSubject> getSubjects() {
        return subjects;
    }
}
