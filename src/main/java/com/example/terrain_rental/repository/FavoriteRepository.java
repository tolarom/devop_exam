package com.example.terrain_rental.repository;

import com.example.terrain_rental.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    // All favorites for a given user (e.g. "My Favorites" page)
    List<Favorite> findByUserId(Long userId);

    // Check/find a specific favorite (to toggle favorite/unfavorite)
    Optional<Favorite> findByUserIdAndTerrainId(Long userId, Long terrainId);

    boolean existsByUserIdAndTerrainId(Long userId, Long terrainId);

    // Remove a favorite (for "unfavorite" action)
    void deleteByUserIdAndTerrainId(Long userId, Long terrainId);
}