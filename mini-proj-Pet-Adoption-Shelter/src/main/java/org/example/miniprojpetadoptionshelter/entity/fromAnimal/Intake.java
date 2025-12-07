package org.example.miniprojpetadoptionshelter.entity.fromAnimal;

import jakarta.persistence.*;
import lombok.*;
import org.example.miniprojpetadoptionshelter.common.enums.IntakeReason;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeDetailRes;
import org.example.miniprojpetadoptionshelter.entity.animal.Animal;
import org.example.miniprojpetadoptionshelter.entity.base.BaseTimeEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "intake_records",
        indexes = {
            @Index(name = "idx_intake_animal", columnList = "animal_id, intake_date")
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
            foreignKey = @ForeignKey(name = "fk_intake_records_animal"))
    private Animal animal;

    @Column(name = "intake_date", nullable = false)
    private LocalDateTime intakeDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "intake_reason", nullable = false, length = 100)
    private IntakeReason intakeReason = IntakeReason.STRAY;
    // STRAY/SURRENDER/TRANSFER

    @Column(name = "found_location", length = 255)
    private String foundLocation;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    // intakeReason SURRENDER 경우
    public void intakeSurrender(){
        this.intakeReason = IntakeReason.SURRENDER;
    }

    // intakeReason Transfer 경우
    public void intakeTransfer() {
        this.intakeReason = IntakeReason.TRANSFER;
    }

    public void intakeUpdate(LocalDateTime intakeDate, IntakeReason intakeReason, String foundLocation, String note) {
        this.intakeDate = intakeDate;
        this.intakeReason = intakeReason;
        this.foundLocation = foundLocation;
        this.note = note;
    }

    @Builder
    public Intake(
            Animal animal,
            LocalDateTime intakeDate,
            IntakeReason intakeReason,
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
