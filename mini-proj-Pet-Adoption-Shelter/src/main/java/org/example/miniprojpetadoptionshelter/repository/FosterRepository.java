package org.example.miniprojpetadoptionshelter.repository;

import org.example.miniprojpetadoptionshelter.entity.Foster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FosterRepository extends JpaRepository<Foster, Long> {
    Optional<Foster> findByAnimalIdAndStatus(Long animalId, String status);

}
