package ort.assi.blp.io;

public class InstructionParser {
    public InstructionObject parse(String instruction) throws Exception {
        String sanitized = sanitize(instruction);
        String[] parts = sanitized.split("\\s+");
        return parseParts(parts);
    }

    private String sanitize(String instruction) {
        return instruction.toLowerCase();
    }

    private InstructionObject parseParts(String[] parts) throws Exception {
        if (!(parts.length == 3 || parts.length == 4)) {
            return new BadInstruction();
        }

        if ("read".equals(parts[0])) {
            return parseReadInstruction(parts);
        } else if ("write".equals(parts[0])) {
            return parseWriteInstruction(parts);
        }

        throw new Exception("invalid input");
    }

    private ReadInstruction parseReadInstruction(String[] parts) {
        return new ReadInstruction("object", "hal");
    }

    private WriteInstruction parseWriteInstruction(String[] parts) {
        return new WriteInstruction("blah", "blah", 3);
    }
}
