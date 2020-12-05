package ort.assi.blp.io;

public class WriteInstruction extends InstructionObject{

    @Override
    public boolean equals(Object o) {
        InstructionObject that = (InstructionObject) o;
        return super.equals(o) &&
                getObjectValue().equals(that.getObjectValue());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public WriteInstruction(String objectName, String subjectName, Integer objectValue) {
        super.type = InstructionType.WRITE;
        super.objectName = objectName;
        super.subjectName = subjectName;
        super.objectValue = objectValue;
    }
}
