package com.mehulsuthar.JPA_Project.repositories;

import com.mehulsuthar.JPA_Project.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Integer> {

    // Find files by type
    List<File> findByType(String type);

    // Find files by type (case insensitive)
    List<File> findByTypeIgnoreCase(String type);

    // Find files by type containing specific text
    List<File> findByTypeContainingIgnoreCase(String typePart);

    // Find files by name
    List<File> findByNameContainingIgnoreCase(String name);

    // Find files by size range
    List<File> findBySizeBetween(int minSize, int maxSize);

    // Find files larger than specified size
    List<File> findBySizeGreaterThan(int size);

    // Find files by multiple types
    List<File> findByTypeIn(List<String> types);

    // Find files by lecture id
    @Query("SELECT f FROM File f WHERE f.lecture.id = :lectureId")
    List<File> findByLectureId(@Param("lectureId") Integer lectureId);

    // Find files by course id (through lecture and section)
    @Query("SELECT f FROM File f WHERE f.lecture.section.course.id = :courseId")
    List<File> findByCourseId(@Param("courseId") Integer courseId);

    // Count files by type
    long countByType(String type);

    // Find files with specific file extensions
    @Query("SELECT f FROM File f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%.', :extension))")
    List<File> findByFileExtension(@Param("extension") String extension);

    // Find large files (above certain size threshold)
    @Query("SELECT f FROM File f WHERE f.size > :sizeThreshold ORDER BY f.size DESC")
    List<File> findLargeFiles(@Param("sizeThreshold") int sizeThreshold);

    // Group files by type and count
    @Query("SELECT f.type, COUNT(f) FROM File f GROUP BY f.type")
    List<Object[]> countFilesByType();
}
