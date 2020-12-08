package ort.assi.blp.entities;

import ort.assi.blp.secure.SecurityLevel;

import java.util.function.Supplier;

public class SysSubject {
    private final SecurityLevel clearance;
    private Integer temp = 0;
    private final String name;

    public Boolean getCanAct() {
        return canAct;
    }

    private Boolean canAct = false;

    public void setFunction(Supplier<Integer> function) {
        this.function = function;
    }

    private Supplier<Integer> function = () -> -1;

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

    public SysSubject(String name, SecurityLevel clearance, Supplier<Integer> function) {
        this(name,clearance);
        this.function = function;
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

    public Integer execute(){
        return this.function.get();
    }

    public void setCanAct(boolean b) {
        this.canAct = b;
    }
}
