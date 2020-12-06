package ort.assi.blp.io;

import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;
import ort.assi.blp.io.instruction.*;
import ort.assi.blp.secure.ReferenceMonitor;

import java.util.Objects;

public class InstructionObject {
    private final ReferenceMonitor referenceMonitor;

    public InstructionObject(ReferenceMonitor referenceMonitor) {
        this.referenceMonitor = referenceMonitor;
    }


    public Instruction parse(String instruction) {
        String sanitized = sanitize(instruction);
        String[] parts = sanitized.split("\\s");
        return parseParts(parts);
    }

    private String sanitize(String instruction) {
        return instruction.toLowerCase();
    }

    private Instruction parseParts(String[] parts) {
        if ("read".equals(parts[0])) {
            return parseReadInstruction(parts);
        } else if ("write".equals(parts[0])) {
            return parseWriteInstruction(parts);
        } else if ("run".equals(parts[0])) {
            return parseRunInstruction(parts);
        } else if ("create".equals(parts[0])) {
            return parseCreateInstruction(parts);
        } else if ("destroy".equals(parts[0])) {
            return parseDestroyInstruction(parts);
        }

        return new BadInstruction();
    }

    private Instruction parseReadInstruction(String[] parts) {
        if (parts.length != 3) return new BadInstruction();
        try {
            String[] subjectAndObj = parseObjectAndSubjectNames(parts);
            String subjectName = subjectAndObj[0];
            String objectNamed = subjectAndObj[1];

            if (!this.referenceMonitor.existsObject(objectNamed))
                return new BadInstruction();

            if (!this.referenceMonitor.existsSubject(subjectName))
                return new BadInstruction();

            SysSubject subject = this.referenceMonitor.getSubject(subjectName);
            SysObject object = this.referenceMonitor.getObject(objectNamed);

            return new ReadInstruction(subject, object);
        } catch (Exception ignored) {
            return new BadInstruction();
        }
    }

    private Instruction parseWriteInstruction(String[] parts) {
        if (parts.length != 4) return new BadInstruction();
        try {
            Integer parseValue = parseObjectValue(parts);
            String[] subjectAndObj = parseObjectAndSubjectNames(parts);
            String subjectName = subjectAndObj[0];
            String objectNamed = subjectAndObj[1];

            if (!this.referenceMonitor.existsObject(objectNamed))
                return new BadInstruction();

            if (!this.referenceMonitor.existsSubject(subjectName))
                return new BadInstruction();

            SysSubject subject = this.referenceMonitor.getSubject(subjectName);
            SysObject object = this.referenceMonitor.getObject(objectNamed);
            return new WriteInstruction(subject, object, parseValue);
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

    private Instruction parseDestroyInstruction(String[] parts) {
        try {
            String[] subjectAndObj = parseObjectAndSubjectNames(parts);
            String subjectName = subjectAndObj[0];
            String objectNamed = subjectAndObj[1];
            SysSubject subject = this.referenceMonitor.getSubject(subjectName);
            SysObject object = this.referenceMonitor.getObject(objectNamed);
            return new DestroyInstruction(subject, object);
        } catch (Exception ignored) {
        }
        return new BadInstruction();
    }

    private Instruction parseCreateInstruction(String[] parts) {
        try {
            String[] subjectAndObj = parseObjectAndSubjectNames(parts);
            String subjectName = subjectAndObj[0];
            String objectNamed = subjectAndObj[1];
            SysSubject subject = this.referenceMonitor.getSubject(subjectName);
            SysObject object = this.referenceMonitor.getObject(objectNamed);
            return new CreateInstruction(subject, object);
        } catch (Exception ignored) {
        }
        return new BadInstruction();
    }

    private Instruction parseRunInstruction(String[] parts) {
        String subjectName = parts[1];
        SysSubject subject = this.referenceMonitor.getSubject(subjectName);
        return new RunInstruction(subject);
    }


}
