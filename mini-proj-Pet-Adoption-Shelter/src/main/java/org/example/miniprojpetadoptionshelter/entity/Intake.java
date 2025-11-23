package org.example.miniprojpetadoptionshelter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.miniprojpetadoptionshelter.entity.base.BaseTimeEntity;

import java.time.LocalDate;


@Entity
@Table(name = "medical_records",
        indexes = {
            @Index(name = "idx_medical_animal", columnList = "animal_id"),
            @Index(name = "idx_medical_visit", columnList = "visit_date")
        })
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Intake extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_medcial_animal"))
    private Animal animal;

    @Column(name = "intake_date", nullable = false)
    private LocalDate intakeDate;

    @Column(name = "intake_reason", nullable = false, length = 100)
    private String intakeReason;
    // STRAY/SURRENDER/TRANSFER

    @Column(name = "found_location", length = 255)
    private String foundLocation;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    // === 수정 내용 ===


    @Builder
    public Intake(
            Animal animal,
            LocalDate intakeDate,
            String intakeReason,
            String foundLocation,
            String note
    ) {
        this.animal = animal;
        this.intakeDate = intakeDate;
        this.intakeReason = intakeReason;
        this.foundLocation = foundLocation;
        this.note = note;
    }
}
