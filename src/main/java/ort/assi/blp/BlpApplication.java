package ort.assi.blp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlpApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("HOLAAA");
	}
}
