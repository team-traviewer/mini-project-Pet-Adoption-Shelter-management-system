package org.example.miniprojpetadoptionshelter.entity.dashboard;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shelterId;

    private LocalDate fromDate;
    private LocalDate toDate;

    private Long totalCount;

    private Long protectedCount;    // 보호중
    private Long adoptedCount;      // 입양
    private Long naturalDeathCount; // 자연사
    private Long returnedCount;     // 반환
    private Long euthanasiaCount;   // 안락사
    private Long donationCount;     // 기증
    private Long releaseCount;      // 방사
}
