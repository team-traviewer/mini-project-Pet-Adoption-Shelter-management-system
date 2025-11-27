package org.example.miniprojpetadoptionshelter.entity.fromAnimal;


import jakarta.persistence.*;
import lombok.*;
import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;
import org.example.miniprojpetadoptionshelter.entity.animal.Animal;
import org.example.miniprojpetadoptionshelter.entity.base.BaseTimeEntity;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Entity
@Table(name = "foster_care",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_foster_active ", columnNames = {"animal_id", "status"}
                )
        },
        indexes = {
            @Index(name = "idx_foster_user", columnList = "foster_user_id, status")
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
                foreignKey = @ForeignKey(name = "fk_foster_care_animal"))
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foster_user_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_foster_care_user"))
    private User fosterUser;

    @Column(name = "start_date",nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    /* 임시보호 상태 : 초기값 ACTIVE */
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private FosterStatus status = FosterStatus.ACTIVE;

    @Lob
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    @Column(name = "note")
    private String note;

    // 임보 종료
    public void completeFoster(LocalDate endDate){
        this.endDate = endDate;
        this.status = FosterStatus.CLOSED;
    }
    // 임보 취소됨
    public void cancelFoster() {
        this.status = FosterStatus.CANCELED;
    }
    @Builder
    public Foster(Animal animal, User fosterUser, LocalDate startDate, LocalDate endDate, String status){
        this.animal = animal;
        this.fosterUser = fosterUser;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status =  FosterStatus.ACTIVE;
    }

}
