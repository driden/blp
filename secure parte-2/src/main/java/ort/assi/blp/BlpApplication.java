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
        var secureSys = new SecureSystem();

        loadData(secureSys);

    }

    private void loadData(SecureSystem secureSystem) {

        secureSystem.createSubject("lyle", SecurityLevel.LOW);
        secureSystem.createSubject("moe", SecurityLevel.MEDIUM);
        secureSystem.createSubject("hal", SecurityLevel.HIGH);

    }
}
