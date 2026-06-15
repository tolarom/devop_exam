package com.example.terrain_rental.controller;

import com.example.terrain_rental.model.Terrain;
import com.example.terrain_rental.repository.TerrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terrains")
@RequiredArgsConstructor
public class TerrainController {

    private final TerrainRepository terrainRepository;

    @GetMapping
    public List<Terrain> getAll() {
        return terrainRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Terrain> getById(@PathVariable Long id) {
        return terrainRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Terrain> create(@RequestBody Terrain terrain) {
        Terrain saved = terrainRepository.save(terrain);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Terrain> update(@PathVariable Long id, @RequestBody Terrain updated) {
        return terrainRepository.findById(id)
                .map(existing -> {
                    existing.setOwnerId(updated.getOwnerId());
                    existing.setTitle(updated.getTitle());
                    existing.setDescription(updated.getDescription());
                    existing.setLocation(updated.getLocation());
                    existing.setAreaSize(updated.getAreaSize());
                    existing.setPricePerDay(updated.getPricePerDay());
                    existing.setAvailableFrom(updated.getAvailableFrom());
                    existing.setAvailableTo(updated.getAvailableTo());
                    existing.setAvailable(updated.isAvailable());
                    existing.setMainImageId(updated.getMainImageId());
                    return ResponseEntity.ok(terrainRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!terrainRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        terrainRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}