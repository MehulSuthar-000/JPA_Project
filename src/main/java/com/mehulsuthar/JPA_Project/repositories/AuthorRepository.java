package com.mehulsuthar.JPA_Project.repositories;

import com.mehulsuthar.JPA_Project.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
