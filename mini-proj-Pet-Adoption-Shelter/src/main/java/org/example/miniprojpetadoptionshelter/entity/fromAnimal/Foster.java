package org.example.miniprojpetadoptionshelter.entity.fromAnimal;


import jakarta.persistence.*;
import lombok.*;
import org.example.miniprojpetadoptionshelter.common.enums.ByAnimalStatus;
import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;
import org.example.miniprojpetadoptionshelter.entity.animal.Animal;
import org.example.miniprojpetadoptionshelter.entity.base.BaseTimeEntity;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "foster_cares",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_foster_cares_animal_id_status", columnNames = {"animal_id", "status"}
                )
        },
        indexes = {
            @Index(name = "idx_foster_cares_user", columnList = "foster_user_id, status")
        })
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Foster{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_foster_cares_animal"))
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foster_user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_foster_cares_user"))
    private User fosterUser;

    @Column(name = "start_date",nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    /* 임시보호 상태 : 초기값 ACTIVE */
    @Enumerated(EnumType.STRING)
    @Column(name ="status", length = 20, nullable = false)
    private FosterStatus status = FosterStatus.ACTIVE;

    @Lob
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    @Column(name = "note")
    private String note;

    @CreationTimestamp // 생성될 때 자동으로 현재시각 넣어줌. (생성자에 불필요)
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    private LocalDateTime createdAt;

    @Builder
    public Foster(Animal animal, User fosterUser, LocalDate startDate, String note){
        this.animal = animal;
        this.fosterUser = fosterUser;
        this.startDate = startDate;
        this.note = note;
    }

    // 임시보호 종료
    public void closeFoster(String note){
        this.note = note;
        this.endDate = LocalDate.now();
        this.status = FosterStatus.CLOSED;
    }
    // 임시보호 취소
    public void cancelFoster(String note) {
        this.note = note;
        this.status = FosterStatus.CANCELED;
        this.animal.setStatus(ByAnimalStatus.AVAILABLE);
    }
}
