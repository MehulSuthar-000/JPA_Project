package com.mehulsuthar.JPA_Project.repositories;

import com.mehulsuthar.JPA_Project.models.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ResourcesRepository extends JpaRepository<Resources, Integer> {

    // Find resources by name (case insensitive)
    List<Resources> findByNameContainingIgnoreCase(String name);

    // Find resources by size range
    List<Resources> findBySizeBetween(int minSize, int maxSize);

    // Find resources larger than specified size
    List<Resources> findBySizeGreaterThan(int size);

    // Find resources by URL containing specific text
    List<Resources> findByUrlContainingIgnoreCase(String urlPart);

    // Find resource by lecture id
    @Query("SELECT r FROM Resources r WHERE r.lecture.id = :lectureId")
    Optional<Resources> findByLectureId(@Param("lectureId") Integer lectureId);

    // Find all resources with their associated lectures
    @Query("SELECT r FROM Resources r LEFT JOIN FETCH r.lecture")
    List<Resources> findAllWithLectures();

    // Find resources by type (using instanceOf for inheritance)
    @Query("SELECT r FROM Resources r WHERE TYPE(r) = :resourceType")
    List<Resources> findByResourceType(@Param("resourceType") Class<? extends Resources> resourceType);

    // Find orphaned resources (without lectures)
    @Query("SELECT r FROM Resources r WHERE r.lecture IS NULL")
    List<Resources> findOrphanedResources();

    // Count resources by size range
    @Query("SELECT COUNT(r) FROM Resources r WHERE r.size BETWEEN :minSize AND :maxSize")
    long countByFileSize(@Param("minSize") int minSize, @Param("maxSize") int maxSize);
}
