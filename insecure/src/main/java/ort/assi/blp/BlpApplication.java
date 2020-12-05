package ort.assi.blp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.handlers.FileHandler;

@SpringBootApplication
public class BlpApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var filePath = args[0];
		var instructionObjects = FileHandler.readFile(filePath);
		ObjectManager.getInstance().executeInstructions(instructionObjects);
	}
}
