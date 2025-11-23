package org.example.miniprojpetadoptionshelter.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.miniprojpetadoptionshelter.entity.base.BaseTimeEntity;

import java.time.LocalDate;

@Entity
@Table(name = "foster_care",
        indexes = {
            @Index(name = "idx_foster_animal", columnList = "animal_id"),
            @Index(name = "idx_foster_user", columnList = "foster_user_id"),
            @Index(name = "idx_foster_status", columnList = "status")
        })
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Foster extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_foster_animal"))
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foster_user_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_foster_user"))
    private User fosterUser;

    @Column(name = "start_date",nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(length = 20, nullable = false)
    private String status = "ACTIVE";

    @Column(columnDefinition = "TEXT")
    private String note;

    // 임보 종료
    public void completeFoster(LocalDate endDate){
        this.endDate = endDate;
        this.status = "CLOSED";
    }
    // 임보 취소됨
    public void cancelFoster() {
        this.status = "CANCELED";
    }
    @Builder
    public Foster(Animal animal, User fosterUser, LocalDate startDate, LocalDate endDate, String status){
        this.animal = animal;
        this.fosterUser = fosterUser;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status != null ? status : "ACTIVE";
    }

}
