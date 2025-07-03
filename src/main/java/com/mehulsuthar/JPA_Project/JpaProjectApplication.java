package com.mehulsuthar.JPA_Project;

import com.mehulsuthar.JPA_Project.models.Author;
import com.mehulsuthar.JPA_Project.repositories.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class JpaProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpaProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
		AuthorRepository repository
	){
		return (args) -> {
			var author = Author.builder()
					.firstName("Mehul")
					.lastName("	Suthar")
					.age(22)
					.email("mehulsuthar@gmail.com")
					.build();
		repository.save(author);
		};
	}
}
