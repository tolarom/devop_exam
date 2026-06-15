package com.example.terrain_rental.repository;

import com.example.terrain_rental.model.TerrainImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerrainImageRepository extends JpaRepository<TerrainImage, Long> {

    // Get all images for a given terrain
    List<TerrainImage> findByTerrainId(Long terrainId);

    // Delete all images belonging to a terrain (e.g. when a terrain is deleted)
    void deleteByTerrainId(Long terrainId);
}