package ort.assi.blp;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ort.assi.blp.context.ReceiveContext;
import ort.assi.blp.context.TransferContext;
import ort.assi.blp.covertchannel.SequenceHandler;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.io.instruction.CreateInstruction;
import ort.assi.blp.io.instruction.DestroyInstruction;
import ort.assi.blp.secure.SecureSystem;
import ort.assi.blp.secure.SecurityLevel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.BitSet;

@SpringBootApplication
public class BlpApplication implements CommandLineRunner {
    private TransferContext transferContext;
    private ReceiveContext receiveContext;
    private final String RESULT_FILE = "result.txt";

    public static void main(String[] args) {
        SpringApplication.run(BlpApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var fileToTransfer = args[0];
        if (!Files.exists(Path.of(fileToTransfer))) {
            System.out.println("Can't find file " + fileToTransfer);
            return;
        }

        var sequenceFile = args.length > 1 ? args[1] : "test-files/secuencia.txt";
        String sequence = readSequence(sequenceFile);
        transferContext = new TransferContext(readFileToTransfer(fileToTransfer));

        Files.deleteIfExists(Path.of(RESULT_FILE));
        receiveContext = new ReceiveContext(RESULT_FILE);
        var secureSys = new SecureSystem(new SequenceHandler(sequence));
        loadSecureSystemData(secureSys);
        secureSys.run();
        receiveContext.writeFile();
    }

    private void loadSecureSystemData(SecureSystem secureSystem) {
        var lyle = secureSystem.createSubject("lyle", SecurityLevel.LOW);
        secureSystem.createSubject("moe", SecurityLevel.MEDIUM);
        var hal = secureSystem.createSubject("hal", SecurityLevel.HIGH);

        lyle.setFunction(() -> {
            if (!lyle.getCanAct()) return 0;
            var createResult = secureSystem.execute(
                    new CreateInstruction(lyle, new SysObject(secureSystem.TRANSFER_OBJ)));
            var bit = createResult == 1;
            receiveContext.receive(bit);
            secureSystem.execute(new DestroyInstruction(lyle, new SysObject(secureSystem.TRANSFER_OBJ)));
            return 1;
        });

        hal.setFunction(() -> {
            if (!hal.getCanAct()) return 0;
            if (!transferContext.hasNext()) return 1;
            if (!transferContext.getNext()) {
                secureSystem.execute(
                        new CreateInstruction(hal, new SysObject(secureSystem.TRANSFER_OBJ)));
            }

            return 0;
        });

    }

    private String readSequence(String sequencePath) throws IOException {
        if (Files.exists(Path.of(sequencePath))) {
            return Files.readString(Path.of(sequencePath));
        }
        String cwd = System.getProperty("user.dir");
        System.out.println("Current Path " + cwd);
        return "";
    }

    private BitSet readFileToTransfer(String path) throws IOException {
        return BitSet.valueOf(FileUtils.readFileToByteArray(new File(path)));
    }

}
