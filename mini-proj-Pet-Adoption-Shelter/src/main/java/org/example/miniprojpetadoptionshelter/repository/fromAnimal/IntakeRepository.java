package org.example.miniprojpetadoptionshelter.repository.fromAnimal;

import org.example.miniprojpetadoptionshelter.entity.fromAnimal.Intake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntakeRepository extends JpaRepository<Intake, Long> {
    List<Intake> findByAnimalId(Long animalId);
}
