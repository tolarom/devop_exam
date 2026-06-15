package com.example.terrain_rental.controller;

import com.example.terrain_rental.model.TerrainImage;
import com.example.terrain_rental.repository.TerrainImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terrain-images")
@RequiredArgsConstructor
public class TerrainImageController {

    private final TerrainImageRepository terrainImageRepository;

    @GetMapping
    public List<TerrainImage> getAll() {
        return terrainImageRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerrainImage> getById(@PathVariable Long id) {
        return terrainImageRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TerrainImage> create(@RequestBody TerrainImage image) {
        TerrainImage saved = terrainImageRepository.save(image);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TerrainImage> update(@PathVariable Long id, @RequestBody TerrainImage updated) {
        return terrainImageRepository.findById(id)
                .map(existing -> {
                    existing.setTerrainId(updated.getTerrainId());
                    existing.setImagePath(updated.getImagePath());
                    return ResponseEntity.ok(terrainImageRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!terrainImageRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        terrainImageRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}