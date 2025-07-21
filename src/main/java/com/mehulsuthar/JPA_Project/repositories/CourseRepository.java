package com.mehulsuthar.JPA_Project.repositories;

import com.mehulsuthar.JPA_Project.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
