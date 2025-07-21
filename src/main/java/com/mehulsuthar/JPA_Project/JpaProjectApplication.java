package com.mehulsuthar.JPA_Project;

import com.github.javafaker.Faker;
import com.mehulsuthar.JPA_Project.models.Author;
import com.mehulsuthar.JPA_Project.models.Course;
import com.mehulsuthar.JPA_Project.models.Video;
import com.mehulsuthar.JPA_Project.repositories.AuthorRepository;
import com.mehulsuthar.JPA_Project.repositories.CourseRepository;
import com.mehulsuthar.JPA_Project.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JpaProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpaProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
		AuthorRepository repository,
		VideoRepository videoRepository,
		CourseRepository courseRepository
	){
		return (args) -> {
			for(int i = 0; i < 50; i++) {
				Faker faker = new Faker();
				var author = Author.builder()
						.firstName(faker.name().firstName())
						.lastName(faker.name().lastName())
						.age((int) faker.number().randomNumber())
						.email("mehulsuthar" + i + "@gmail.com")
						.build();
				repository.save(author);

				List<Author> authorList = new ArrayList<>();
				authorList.add(author);
				var course = Course.builder()
						.authors(authorList)
						.description(faker.cat().name())
						.name(faker.name().title())
						.build();
				courseRepository.save(course);
			}

			List<Author> authors = repository.findAllWithCourses();
			authors.forEach(author -> {
				System.out.println("Author: " + author.getFirstName() + " " + author.getLastName());
				author.getCourses().forEach(course -> System.out.println(" - Course: " + course.getName()));
			});

//			Video video = Video.builder()
//					.name("ABC")
//					.length(5)
//					.build();
//
//			videoRepository.save(video);
		};
	}
}
