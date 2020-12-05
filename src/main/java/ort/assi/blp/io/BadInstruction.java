package ort.assi.blp.io;

public class BadInstruction extends InstructionObject {
    public BadInstruction() {
        type = InstructionType.BAD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructionObject that = (InstructionObject) o;
        return getType() == that.getType();
    }
}
