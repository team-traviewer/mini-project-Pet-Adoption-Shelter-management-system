package org.example.miniprojpetadoptionshelter.entity.application;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.common.enums.ByAnimalStatus;
import org.example.miniprojpetadoptionshelter.entity.file.FileInfo;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.example.miniprojpetadoptionshelter.entity.animal.Animal;
import org.example.miniprojpetadoptionshelter.entity.base.BaseTimeEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "adoption_applications",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_application_once", columnNames = {"animal_id", "applicant_id", "status"}
                )
        },
        indexes = {
                @Index(name = "idx_app_animal", columnList = "animal_id, status"),
                @Index(name = "idx_app_user", columnList = "applicant_id, status")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_applications_users"))
    private User applicant;

    /** FK */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false, foreignKey = @ForeignKey(name = "fk_applications_animals"))
    private Animal animal;

    /** FK */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_file_id", foreignKey = @ForeignKey(name = "fk_applications_file_infos"))
    private FileInfo applicationFile;

    /** 심사 상태 */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    /** 인터뷰 일정 */
    @Column(name = "interview_at")
    private LocalDateTime interviewAt; // 기본값 null

    /** 가정방문 확인 */
    @Column(name = "home_check", nullable = false)
    private boolean homeCheck;

    /** 자기소개, 메모 */
    @Lob
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    @Column(name = "message")
    private String message;

    /** 거절 이유 */
    @Column(name = "reason", length = 100)
    private String reason;

    /** 파일 편의 메서드 */
    public void attachDocumentFile(FileInfo fileInfo) {
        this.applicationFile = fileInfo;
    }

    /** 입양 신청 시 생성자*/
    @Builder
    public Application(User applicant, Animal animal, String message) {
        this.applicant = applicant;
        this.animal = animal;
        this.message = message;
    }

    /** APPLIED -> REVIEW 변경 */
    public void startReview(){
        this.status = ApplicationStatus.REVIEW;
    }

    /** REVIEW 일 때 정보 수정 변경 */
    public void updateApplicationInfo(LocalDateTime interviewAt, boolean homeCheck) {
        this.interviewAt = interviewAt;
        this.homeCheck = homeCheck;
    }

    /** REVIEW -> APPROVED 변경 */
    public void approve() {
        this.status = ApplicationStatus.APPROVED;
        this.animal.setStatus(ByAnimalStatus.ADOPTION_PENDING);
    }

    /** REVIEW -> REJECTED 변경 */
    public void reject(String reason) {
        this.status = ApplicationStatus.REJECTED;
        this.reason = reason;
    }

    /** REVIEW -> CANCEL 변경 */
    public void cancel(String reason) {
        this.status = ApplicationStatus.CANCELED;
        this.reason = reason;
    }
}