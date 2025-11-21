package org.example.miniprojpetadoptionshelter.repository;

import org.example.miniprojpetadoptionshelter.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnimalRepository extends JpaRepository<Animal, Long> {}


