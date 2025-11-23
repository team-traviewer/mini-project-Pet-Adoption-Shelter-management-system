package org.example.miniprojpetadoptionshelter.repository;

import org.example.miniprojpetadoptionshelter.entity.Intake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntakeRepository extends JpaRepository<Intake, Long> {
    List<Intake> findByAnimalId(Long animalId);
}
