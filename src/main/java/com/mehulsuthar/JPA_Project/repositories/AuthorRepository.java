package com.mehulsuthar.JPA_Project.repositories;

import com.mehulsuthar.JPA_Project.models.Author;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    // FindAllBy derived methods;

    // Select * From Author where first_name = 'fname'; <- case Sensitive
    List<Author> findAllByFirstName(String fname);

    // Select * From Author where first_name = 'fn'; <- case Sensitive
    List<Author> findAllByFirstNameIgnoreCase(String fname);

    // Select * From Author where first_name = '%fna%'; <- case Sensitive
    List<Author> findAllByFirstNameContainingIgnoreCase(String fname);

    List<Author> findAllByFirstNameStartsWithIgnoreCase(String fname);

    List<Author> findAllByFirstNameEndsWithIgnoreCase(String fname);

    List<Author> findAllByFirstNameInIgnoreCase(List<String> fname);

    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.courses")
    List<Author> findAllWithCourses();
}
