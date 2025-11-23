package org.example.miniprojpetadoptionshelter.repository;

import org.example.miniprojpetadoptionshelter.entity.Medical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRepository extends JpaRepository<Medical, Long> {
    List<Medical> findByAnimalId(Long animalId);
}
