package ort.assi.blp;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;
import ort.assi.blp.io.instruction.BadInstruction;
import ort.assi.blp.io.InstructionObject;
import ort.assi.blp.io.instruction.ReadInstruction;
import ort.assi.blp.io.instruction.WriteInstruction;
import ort.assi.blp.secure.ReferenceMonitor;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class InstructionObjectTests {
    final InstructionObject parser = new InstructionObject(new ReferenceMonitor());

    @Test
    public void parsesReadInstruction() {
        String instruction = "READ       hal   object";

        SysObject object = new SysObject("object");
        SysSubject hal = new SysSubject("hal");
        assertThat(parser.parse(instruction)).isEqualTo(new ReadInstruction(hal, object));
    }

    @Test
    public void parsesWriteInstruction() {
        String instruction = "WRITE       hal   object  3";

        SysObject object = new SysObject("object");
        SysSubject hal = new SysSubject("hal");
        assertThat(parser.parse(instruction)).isEqualTo(new WriteInstruction(hal, object, 3));

    }

    @Test
    public void parsesABadInstructionIfWrongAmountOfArgumentsForReadInstruction() {
        String instruction = "READ       hal   object object";
        assertThat(parser.parse(instruction)).isEqualTo(new BadInstruction());
    }

    @Test
    public void parsesABadInstructionIfWrongAmountOfArgumentsForWriteInstruction() {
        String instruction = "Write       hal   object object";
        assertThat(parser.parse(instruction)).isEqualTo(new BadInstruction());
    }

    @Test
    public void parsesABadInstructionIfWrongInstruction() {
        String instruction = "FOO       hal   object object";
        assertThat(parser.parse(instruction)).isEqualTo(new BadInstruction());
    }
}
