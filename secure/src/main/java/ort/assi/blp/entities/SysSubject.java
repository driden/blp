package ort.assi.blp.entities;

import ort.assi.blp.secure.SecurityLevel;

public class SysSubject {
    private SecurityLevel clearance;
    private Integer temp = 0;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
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

    public void setClearance(SecurityLevel clearance) {
        this.clearance = clearance;
    }

    public void writeObject(SysObject object, Integer value) {
        object.setValue(value);
    }

    public void readObject(SysObject object) {
        this.temp = object.getValue();
    }
}
