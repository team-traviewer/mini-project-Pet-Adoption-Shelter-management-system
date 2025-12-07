package org.example.miniprojpetadoptionshelter.repository.dashboard;

import org.example.miniprojpetadoptionshelter.entity.dashboard.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {

    Optional<Dashboard> findByShelterId(String shelterId);

    @Query("""
        SELECT d FROM Dashboard d
        WHERE d.shelterId = :shelterId
          AND d.fromDate = :from
          AND d.toDate = :to
    """)
    Optional<Dashboard> findDashboard(String shelterId, LocalDate from, LocalDate to);
}