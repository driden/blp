package ort.assi.blp.entities;

import ort.assi.blp.secure.SecurityLevel;

import java.util.Objects;

public class SysObject {
    private Integer value = 0;
    private String name;
    private final SecurityLevel securityTag;

    public SysObject(String name){
        this(name, SecurityLevel.LOW);
    }

    public SysObject(String name, SecurityLevel level){
        this.name = name;
        this.securityTag = level;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name, SecurityLevel tag) {
        this.name = name;
    }

    public SecurityLevel getSecurityTag() {
        return securityTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysObject sysObject = (SysObject) o;
        return getName().equals(sysObject.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
