package com.mehulsuthar.JPA_Project.repositories;

import com.mehulsuthar.JPA_Project.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Integer> {

    // Find sections by course id
    List<Section> findByCourseId(Integer courseId);

    // Find sections by course id ordered by section order
    List<Section> findByCourseIdOrderBySectionOrder(Integer courseId);

    // Find sections by name (case insensitive)
    List<Section> findByNameContainingIgnoreCase(String name);

    // Custom query to find sections with their lectures
    @Query("SELECT s FROM Section s LEFT JOIN FETCH s.lecture WHERE s.course.id = :courseId")
    List<Section> findSectionsWithLecturesByCourseId(@Param("courseId") Integer courseId);

    // Find sections by section order range
    List<Section> findBySectionOrderBetween(int startOrder, int endOrder);

    // Count sections by course id
    long countByCourseId(Integer courseId);
}
