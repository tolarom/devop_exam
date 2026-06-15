package com.example.terrain_rental.repository;

import com.example.terrain_rental.model.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerrainRepository extends JpaRepository<Terrain, Long> {}