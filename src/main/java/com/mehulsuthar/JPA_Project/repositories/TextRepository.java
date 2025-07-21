package com.mehulsuthar.JPA_Project.repositories;

import com.mehulsuthar.JPA_Project.models.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TextRepository extends JpaRepository<Text, Integer> {

    // Find text resources by content containing specific text
    List<Text> findByContentContainingIgnoreCase(String searchText);

    // Find text resources by name
    List<Text> findByNameContainingIgnoreCase(String name);

    // Find text resources by size range
    List<Text> findBySizeBetween(int minSize, int maxSize);

    // Find text resources with content length greater than specified value
    @Query("SELECT t FROM Text t WHERE LENGTH(t.content) > :minLength")
    List<Text> findByContentLengthGreaterThan(@Param("minLength") int minLength);

    // Find text resources with empty or null content
    @Query("SELECT t FROM Text t WHERE t.content IS NULL OR t.content = ''")
    List<Text> findEmptyTextResources();

    // Find text resources by lecture id
    @Query("SELECT t FROM Text t WHERE t.lecture.id = :lectureId")
    List<Text> findByLectureId(@Param("lectureId") Integer lectureId);

    // Search text content using full-text search (database specific)
    @Query("SELECT t FROM Text t WHERE LOWER(t.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Text> searchByKeyword(@Param("keyword") String keyword);

    // Count text resources by content length range
    @Query("SELECT COUNT(t) FROM Text t WHERE LENGTH(t.content) BETWEEN :minLength AND :maxLength")
    long countByContentLength(@Param("minLength") int minLength, @Param("maxLength") int maxLength);
}
