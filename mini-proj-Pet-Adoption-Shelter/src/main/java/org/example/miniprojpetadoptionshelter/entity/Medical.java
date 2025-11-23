package org.example.miniprojpetadoptionshelter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.miniprojpetadoptionshelter.entity.Animal;
import org.example.miniprojpetadoptionshelter.entity.base.BaseTimeEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_records",
        indexes = {
                @Index(name = "idx_medical_animal", columnList = "animal_id, record_date, type")
        })
@Getter
@Setter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class Medical extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    // ==== Animal FK (외래키 이름 명시) ====
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_medical_records_animal"))
    private Animal animal;

    // 기록 날짜
    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    // 타입: VACCINE / NEUTER / TREATMENT / EXAM
    @Column(name = "type", length = 30, nullable = false)
    private String type;

    // 상세 내용
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    // 비용
    @Column(name = "cost", precision = 10, scale = 2)
    private BigDecimal cost;

    // 생성 시간
    @Column(name = "created_at", nullable = false,
            columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)")
    private LocalDateTime createdAt;

    // === 수정 메서드 ===
    public void updateInfo(LocalDate recordDate,
                           String type,
                           String description,
                           BigDecimal cost)
    {
        this.recordDate = recordDate;
        this.type = type;
        this.description = description;
        this.cost = cost;
    }

    @Builder
    public Medical(Animal animal,
                   LocalDate recordDate,
                   String type,
                   String description,
                   BigDecimal cost)
    {
        this.animal = animal;
        this.recordDate = recordDate;
        this.type = type;
        this.description = description;
        this.cost = cost;
    }
}