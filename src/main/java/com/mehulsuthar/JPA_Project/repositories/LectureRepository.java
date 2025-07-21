package com.mehulsuthar.JPA_Project.repositories;

import com.mehulsuthar.JPA_Project.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    // Find lectures by section id
    List<Lecture> findBySectionId(Integer sectionId);

    // Find lectures by name (case insensitive)
    List<Lecture> findByNameContainingIgnoreCase(String name);

    // Find lecture by section id and name
    Optional<Lecture> findBySectionIdAndName(Integer sectionId, String name);

    // Custom query to find lectures with their resources
    @Query("SELECT l FROM Lecture l LEFT JOIN FETCH l.resources WHERE l.section.id = :sectionId")
    List<Lecture> findLecturesWithResourcesBySectionId(@Param("sectionId") Integer sectionId);

    // Find lectures by course id (through section)
    @Query("SELECT l FROM Lecture l WHERE l.section.course.id = :courseId")
    List<Lecture> findByCourseId(@Param("courseId") Integer courseId);

    // Count lectures by section id
    long countBySectionId(Integer sectionId);

    // Find lectures that have resources
    @Query("SELECT l FROM Lecture l WHERE l.resources IS NOT NULL")
    List<Lecture> findLecturesWithResources();

    // Find lectures that don't have resources
    @Query("SELECT l FROM Lecture l WHERE l.resources IS NULL")
    List<Lecture> findLecturesWithoutResources();
}
