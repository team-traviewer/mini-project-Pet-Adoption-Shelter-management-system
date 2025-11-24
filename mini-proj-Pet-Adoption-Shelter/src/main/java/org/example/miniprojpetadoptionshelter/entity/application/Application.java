package org.example.miniprojpetadoptionshelter.entity.application;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.example.miniprojpetadoptionshelter.entity.animal.Animal;
import org.example.miniprojpetadoptionshelter.entity.base.BaseTimeEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Entity
@Table(name = "applications",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_application_once ", columnNames = {"animal_id", "applicant_id", "status"}
                )
        },
        indexes = {
                @Index(name = "idx_app_animal ", columnList = "animal_id, status"),
                @Index(name = "idx_app_user ", columnList = "applicant_id, status")
        })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application extends BaseTimeEntity {

    /** PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", nullable = false)
    private Long id;

    /** FK */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false, foreignKey = @ForeignKey(name = "fk_applications_animals"))
    private Animal animal;

    /** FK */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_applications_users"))
    private User user;

    /** 심사 상태 */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    /** 인터뷰 일정 */
    @Column(name = "interview_at")
    private LocalDate interviewAt; // 기본값 null

    /** 가정방문 확인 */
    @Column(name = "home_check", nullable = false)
    private boolean homeCheck; // 기본값 false

    /** 자기소개, 메모 */
    @Lob
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    @Column(name = "message")
    private String message;

    /** 거절 이유 */
    @Column(name = "reason", length = 100)
    private String reason; // 기본값 null

    /** 입양 신청 시 생성자*/
    @Builder
    public Application(Animal animal, User user, String message) {
        this.animal = animal;
        this.user = user;
        this.message = message;
    }

    /** APPLIED -> CANCEL 변경 */
    public void cancel(String reason) {
        this.status = ApplicationStatus.CANCELED;
        this.reason = reason;
    }

    /** APPLIED -> REVIEW 변경 */
    public void startReview(){
        this.status = ApplicationStatus.REVIEW;
    }

    /** REVIEW 일 때만 정보 수정 변경 */
    public void updateApplicationInfo(LocalDate interviewAt, boolean homeCheck) {
        this.interviewAt = interviewAt;
        this.homeCheck = homeCheck;
    }

    /** REVIEW -> APPROVED 변경*/
    public void approve() {
        this.status = ApplicationStatus.APPROVED;
    }

    /** REVIEW -> REJECTED 변경*/
    public void reject(String reason) {
        this.status = ApplicationStatus.REJECTED;
        this.reason = reason;
    }
}
