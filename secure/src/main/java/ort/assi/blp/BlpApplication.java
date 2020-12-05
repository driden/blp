package ort.assi.blp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ort.assi.blp.handlers.FileHandler;
import ort.assi.blp.io.InstructionObject;
import ort.assi.blp.secure.SecureSystem;
import ort.assi.blp.secure.SecurityLevel;

import java.util.stream.Collectors;

@SpringBootApplication
public class BlpApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlpApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var filePath = args[0];
        var secureSys = new SecureSystem();

        loadData(secureSys);
        InstructionObject instructionObject = secureSys.getParser();

        var instructionObjects = FileHandler.readFile(filePath)
                .stream()
                .map(instructionObject::parse)
                .collect(Collectors.toList());
    }

    private void loadData(SecureSystem secureSystem) {

        secureSystem.createNewObject("lobj", SecurityLevel.LOW);
        secureSystem.createNewObject("mobj", SecurityLevel.MEDIUM);
        secureSystem.createNewObject("hobj", SecurityLevel.HIGH);

        secureSystem.createSubject("lyle", SecurityLevel.LOW);
        secureSystem.createSubject("moe", SecurityLevel.MEDIUM);
        secureSystem.createSubject("hal", SecurityLevel.HIGH);

    }
}
