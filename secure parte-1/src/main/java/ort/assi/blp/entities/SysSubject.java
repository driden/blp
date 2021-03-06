package ort.assi.blp.entities;

import ort.assi.blp.secure.SecurityLevel;

public class SysSubject {
    private final SecurityLevel clearance;
    private Integer temp = 0;
    private final String name;

    public String getName() {
        return name;
    }

    public Integer getTemp() {
        return temp;
    }

    public SysSubject(String name) {
        this(name, SecurityLevel.LOW);
    }

    public SysSubject(String name, SecurityLevel clearance) {
        this.name = name;
        this.clearance = clearance;
    }

    public SecurityLevel getClearance() {
        return clearance;
    }

    public Integer writeObject(SysObject object, Integer value) {
        object.setValue(value);
        return 0;
    }

    public Integer readObject(SysObject object) {
        this.temp = object.getValue();
        return this.temp;
    }

    public String getStatusMessage(){
        return name + " has recently read: " + temp.toString();
    }
}
