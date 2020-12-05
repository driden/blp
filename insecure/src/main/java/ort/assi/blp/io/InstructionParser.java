package ort.assi.blp.io;

import java.util.Objects;

public class InstructionParser {
    public InstructionObject parse(String instruction) {
        String sanitized = sanitize(instruction);
        String[] parts = sanitized.split("\\s+");
        return parseParts(parts);
    }

    private String sanitize(String instruction) {
        return instruction.toLowerCase();
    }

    private InstructionObject parseParts(String[] parts) {
        if ("read".equals(parts[0])) {
            return parseReadInstruction(parts);
        } else if ("write".equals(parts[0])) {
            return parseWriteInstruction(parts);
        }

        return new BadInstruction();
    }

    private InstructionObject parseReadInstruction(String[] parts) {
        if (parts.length != 3) return new BadInstruction();
        try {
            String[] subjectAndObj = parseObjectAndSubjectNames(parts);
            return new ReadInstruction(subjectAndObj[1], subjectAndObj[0]);
        } catch (Exception ignored) {
            return new BadInstruction();
        }
    }

    private InstructionObject parseWriteInstruction(String[] parts) {
        if (parts.length != 4) return new BadInstruction();
        try {
            String[] subjectAndObj = parseObjectAndSubjectNames(parts);
            Integer parseValue = parseObjectValue(parts);
            return new WriteInstruction(subjectAndObj[1], subjectAndObj[0], parseValue);
        } catch (Exception ignored) {
            return new BadInstruction();
        }
    }

    private Integer parseObjectValue(String[] parts) throws NumberFormatException {
        String numberString = parts[3];
        return Integer.parseInt(numberString);
    }

    private String[] parseObjectAndSubjectNames(String[] parts) throws Exception {
        String subject = parts[1];
        String object = parts[2];

        validateString(subject);
        validateString(object);

        return new String[]{subject, object};
    }

    private void validateString(String subject) throws Exception {
        if (Objects.isNull(subject) || subject.isEmpty()) {
            throw new Exception("Subject is null or empty");
        }
    }
}
