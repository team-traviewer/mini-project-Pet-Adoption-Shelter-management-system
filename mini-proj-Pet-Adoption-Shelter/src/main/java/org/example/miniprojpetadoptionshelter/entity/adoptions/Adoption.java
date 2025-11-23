package org.example.miniprojpetadoptionshelter.entity.adoptions;

import jakarta.persistence.*;
import lombok.*;
import org.example.miniprojpetadoptionshelter.entity.Animal;
import org.example.miniprojpetadoptionshelter.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "adoptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 동물
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    // 승인된 신청서
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    // 입양자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adopter_id")
    private User adopter;

    private LocalDate adoptionDate;

    private Integer fee;

    private String contractPDF;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}