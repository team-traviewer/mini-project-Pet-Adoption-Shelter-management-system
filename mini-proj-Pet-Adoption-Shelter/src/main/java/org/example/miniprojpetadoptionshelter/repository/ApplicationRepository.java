package org.example.miniprojpetadoptionshelter.repository;

import org.example.miniprojpetadoptionshelter.entity.adoptions.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {}
