package org.example.miniprojpetadoptionshelter.repository.adoption;

import org.example.miniprojpetadoptionshelter.entity.adoptions.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}