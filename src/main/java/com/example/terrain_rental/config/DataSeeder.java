package com.example.terrain_rental.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.terrain_rental.model.Terrain;
import com.example.terrain_rental.repository.TerrainRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    private final TerrainRepository terrainRepository;

    @Bean
    CommandLineRunner seed() {
        return args -> {

            if (terrainRepository.count() == 0) {

                Terrain terrain = new Terrain();
                terrain.setOwnerId(1L);
                terrain.setTitle("Riverside Plot");
                terrain.setLocation("Kampong Cham");
                terrain.setAreaSize(new BigDecimal("500.00"));
                terrain.setPricePerDay(new BigDecimal("25.00"));
                terrain.setAvailable(true);
                terrainRepository.save(terrain);
            }
        };
    }
}