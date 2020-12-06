package ort.assi.blp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ort.assi.blp.covertchannel.SequenceHandler;
import ort.assi.blp.secure.SecureSystem;
import ort.assi.blp.secure.SecurityLevel;

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

        var sequenceFile = args.length > 1 ? args[1] : "test-files/secuencia.txt";
        String sequence = readSequence(sequenceFile);
        BitSet bitsToTransfer = readFileToTransfer(fileToTransfer);
        var secureSys = new SecureSystem(new SequenceHandler(sequence), bitsToTransfer);
        loadSecureSystemData(secureSys);
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
        return BitSet.valueOf(Files.readAllBytes(Path.of(path)));
    }

}
