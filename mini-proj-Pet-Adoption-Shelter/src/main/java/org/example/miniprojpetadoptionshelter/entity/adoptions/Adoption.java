package org.example.miniprojpetadoptionshelter.entity.adoptions;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.miniprojpetadoptionshelter.entity.Animal;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adopter_id") // DB 컬럼 이름 지정
    private User adopter; // 기존 user -> adopter로 이름 변경

    private LocalDate adoptionDate;
    private Integer fee;
    private String contractPDF;

    // 수정 메서드
    public void update(LocalDate adoptionDate, Integer fee, String contractPDF) {
        this.adoptionDate = adoptionDate;
        this.fee = fee;
        this.contractPDF = contractPDF;
    }
}