package ort.assi.blp;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ort.assi.blp.io.BadInstruction;
import ort.assi.blp.io.InstructionParser;
import ort.assi.blp.io.ReadInstruction;
import ort.assi.blp.io.WriteInstruction;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class InstructionParserTests {
    InstructionParser parser = new InstructionParser();

    @Test
    public void parsesReadInstruction() throws Exception {
        String instruction = "READ       hal   object";

       assertThat(parser.parse(instruction)).isEqualTo(new ReadInstruction("object", "hal"));
    }

    @Test
    public void parsesWriteInstruction() throws Exception {
        String instruction =  "WRITE       hal   object  3";

        assertThat(parser.parse(instruction)).isEqualTo(new WriteInstruction("object", "hal", 3));

    }

    @Test
    public void parsesABadInstructionIfWrongAmountOfArgumentsForReadInstruction(){
        String instruction = "READ       hal   object object";
        assertThat(parser.parse(instruction)).isEqualTo(new BadInstruction());
    }

    @Test
    public void parsesABadInstructionIfWrongAmountOfArgumentsForWriteInstruction(){
        String instruction = "Write       hal   object object";
        assertThat(parser.parse(instruction)).isEqualTo(new BadInstruction());
    }
}
