package org.example.miniprojpetadoptionshelter.repository;

import org.example.miniprojpetadoptionshelter.entity.adoptions.Adoption;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    @EntityGraph(attributePaths = {"animal", "application", "adopter"})
    Optional<Adoption> findById(Long id);
}