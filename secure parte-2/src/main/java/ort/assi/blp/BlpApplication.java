package ort.assi.blp;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ort.assi.blp.context.ReceiveContext;
import ort.assi.blp.context.TransferContext;
import ort.assi.blp.covertchannel.SequenceHandler;
import ort.assi.blp.secure.SecureSystem;
import ort.assi.blp.secure.SecurityLevel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.BitSet;

@SpringBootApplication
public class BlpApplication implements CommandLineRunner {
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

        var sequenceFile = args.length > 1 ? args[1] : "secuencia.txt";
        String sequence = readSequence(sequenceFile);
        TransferContext transferContext = new TransferContext(readFileToTransfer(fileToTransfer));

        String RESULT_FILE = "result.txt";
        Files.deleteIfExists(Path.of(RESULT_FILE));
        ReceiveContext receiveContext = new ReceiveContext(RESULT_FILE);
        var secureSys = new SecureSystem(new SequenceHandler(sequence));
        loadSecureSystemData(secureSys);
        secureSys.run(receiveContext, transferContext);
        receiveContext.writeFile();
    }

    private void loadSecureSystemData(SecureSystem secureSystem) {
        secureSystem.createSubject("lyle", SecurityLevel.LOW);
        secureSystem.createSubject("moe", SecurityLevel.MEDIUM);
        secureSystem.createSubject("hal", SecurityLevel.HIGH);
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
