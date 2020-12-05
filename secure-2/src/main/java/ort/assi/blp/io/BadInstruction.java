package ort.assi.blp.io;

public class BadInstruction extends Instruction {
    public BadInstruction() {
        type = InstructionType.BAD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return getType() == that.getType();
    }

    @Override
    public String getMessage() {
        return "Bad Instruction";
    }
}
