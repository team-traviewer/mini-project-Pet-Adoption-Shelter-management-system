package org.example.miniprojpetadoptionshelter.repository.animal;

import org.example.miniprojpetadoptionshelter.entity.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnimalRepository extends JpaRepository<Animal, Long> {}


