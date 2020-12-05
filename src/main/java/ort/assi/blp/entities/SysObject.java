package ort.assi.blp.entities;

import java.util.Objects;

public class SysObject {
    private Integer value = 0;
    private String name;

    public SysObject(String name){
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
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
