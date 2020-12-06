package ort.assi.blp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ort.assi.blp.secure.SecureSystem;
import ort.assi.blp.secure.SecurityLevel;

@SpringBootApplication
public class BlpApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlpApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var secureSys = new SecureSystem();
        loadSecureSystemData(secureSys);
    }

    private void loadSecureSystemData(SecureSystem secureSystem) {
        secureSystem.createSubject("lyle", SecurityLevel.LOW);
        secureSystem.createSubject("moe", SecurityLevel.MEDIUM);
        secureSystem.createSubject("hal", SecurityLevel.HIGH);
    }

}
