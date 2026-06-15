package com.example.terrain_rental.repository;

import com.example.terrain_rental.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // All reviews for a given terrain (e.g. terrain detail page)
    List<Review> findByTerrainId(Long terrainId);

    // All reviews written by a specific user (e.g. user profile page)
    List<Review> findByUserId(Long userId);

    // Check if a user has already reviewed a terrain (prevent duplicate reviews)
    boolean existsByTerrainIdAndUserId(Long terrainId, Long userId);

    // Calculate average rating for a terrain
    @org.springframework.data.jpa.repository.Query(
        "SELECT AVG(r.rating) FROM Review r WHERE r.terrainId = :terrainId"
    )
    Double findAverageRatingByTerrainId(Long terrainId);
}