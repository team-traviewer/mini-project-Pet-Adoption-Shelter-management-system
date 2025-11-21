package org.example.miniprojpetadoptionshelter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
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
    @JoinColumn(name = "animal_id", nullable = false, foreignKey = @ForeignKey(name = "fk_applications_animal"))
    private Animal animal;

    /** FK */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_applications_user"))
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
    @Column(name = "reason", nullable = false)
    private String reason;

    /** 입양 신청 시 생성자*/
    @Builder
    public Application(Animal animal, User user, String message) {
        this.animal = animal;
        this.user = user;
        this.message = message;
    }

    /** APPLIED -> CANCEL 상태 변경 */
    public void cancel(String reason) {
        if(status != ApplicationStatus.APPLIED) {
            throw new IllegalStateException(
                    "APPLIED 상태에서만 CANCELED 로 변경 가능!" + this.status
            );
        }
        this.reason = reason;
        this.status = ApplicationStatus.CANCELED;
    }

    /** APPLIED -> REVIEW 상태 변경 */
    // 서비스에서 검증하면 규칙이 샐수도있음. 도메인에 예외처리
    public void startReview(){
        if(status != ApplicationStatus.APPLIED) {
            throw new IllegalStateException(
                    "APPLIED 상태에서만 REVIEW 로 변경 가능!" + this.status
            );
        }
        this.status = ApplicationStatus.REVIEW;
    }

    /** REVIEW 일 때만 정보 수정 변경 */
    public void updateApplicationInfo(LocalDate interviewAt, boolean homeCheck) {
        if (this.status != ApplicationStatus.REVIEW) {
            throw new IllegalStateException(
                    "REVIEW 상태에서만 면담일정과 가정방문 여부 수정 가능!" + this.status
            );
        }
        this.interviewAt = interviewAt;
        this.homeCheck = homeCheck;
    }

    /** REVIEW -> APPROVED */
    public void approve() {
        if(this.status != ApplicationStatus.REVIEW) {
            throw new IllegalStateException(
                    "REVIEW 상태에서만 (승인) APPROVE 가능!" + this.status
            );
        }
        this.status = ApplicationStatus.APPROVED;
    }

    /** REVIEW -> REJECTED */
    public void reject(String reason) {
        if(this.status != ApplicationStatus.REVIEW) {
            throw new IllegalStateException(
                    "REVIEW 상태에서만 (거절) REJECT 가능!" + this.status
            );
        }
        this.status = ApplicationStatus.REJECTED;
        this.reason = reason;
    }
}
