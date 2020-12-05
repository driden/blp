package ort.assi.blp.io;

public class ReadInstruction extends InstructionObject {
    public ReadInstruction(String objectName, String subjectName) {
        super.type = InstructionType.READ;
        super.objectName = objectName;
        super.subjectName = subjectName;
    }
}
