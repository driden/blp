package ort.assi.blp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ort.assi.blp.entities.Context;
import ort.assi.blp.entities.ObjectManager;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;
import ort.assi.blp.handlers.FileHandler;
import ort.assi.blp.secure.SecurityLevel;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BlpApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var filePath = args[0];
		var context = getStartUpContext();
		var instructionObjects = FileHandler.readFile(filePath);
	}

	private Context getStartUpContext(){
		var objects = new HashSet<SysObject>();
		var lobj = new SysObject("lobj", SecurityLevel.LOW);
		var mobj = new SysObject("mobj", SecurityLevel.MEDIUM);
		var hobj = new SysObject("hobj", SecurityLevel.HIGH);
		objects.add(lobj);
		objects.add(mobj);
		objects.add(hobj);

		var subjects = new HashSet<SysSubject>();
		var lyle = new SysSubject("lyle", SecurityLevel.LOW);
		var moe = new SysSubject("moe", SecurityLevel.MEDIUM);
		var hal = new SysSubject("hal", SecurityLevel.HIGH);
		subjects.add(lyle);
		subjects.add(moe);
		subjects.add(hal);

		return new Context(objects, subjects);
	}
}
