package ort.assi.blp.io;

import java.util.Objects;

public abstract class InstructionObject {
    InstructionType type;
    String objectName;
    String subjectName;
    Integer objectValue;


    public InstructionType getType() {
        return type;
    }

    public void setType(InstructionType type) {
        this.type = type;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(Integer objectValue) {
        this.objectValue = objectValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructionObject that = (InstructionObject) o;
        return getType() == that.getType() &&
                getObjectName().equals(that.getObjectName()) &&
                getSubjectName().equals(that.getSubjectName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getObjectName(), getSubjectName(), getObjectValue());
    }
}
