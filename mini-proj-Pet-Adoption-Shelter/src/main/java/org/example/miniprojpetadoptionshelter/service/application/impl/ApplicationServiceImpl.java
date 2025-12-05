package org.example.miniprojpetadoptionshelter.service.application.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.common.enums.ByAnimalStatus;
import org.example.miniprojpetadoptionshelter.common.utils.DateUtils;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationCancelReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationCreateReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationRejectReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.application.response.ApplicationDetailRes;
import org.example.miniprojpetadoptionshelter.dto.application.response.ApplicationListRes;
import org.example.miniprojpetadoptionshelter.entity.animal.Animal;
import org.example.miniprojpetadoptionshelter.entity.application.Application;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.example.miniprojpetadoptionshelter.repository.animal.AnimalRepository;
import org.example.miniprojpetadoptionshelter.repository.application.ApplicationRepository;
import org.example.miniprojpetadoptionshelter.repository.user.UserRepository;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.application.ApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly= true)
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    @Override
    @Transactional
    public ResponseDto<Void> createApplication(
            UserPrincipal principal,
            ApplicationCreateReq req
    ) {
        if (!isUser(principal)) {
            throw new AccessDeniedException("USER만 입양 신청서를 작성할 수 있습니다.");
        }

        User user = userRepository.findById(principal.getId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않은 회원입니다."));

        Animal animal = animalRepository.findById(req.animalId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않은 동물입니다."));

        ByAnimalStatus animalStatus = animal.getStatus();
        if (animalStatus != ByAnimalStatus.AVAILABLE ) {
            throw new IllegalStateException("해당 동물은 입양 신청 가능상태가 아닙니다.");
        }

        Application application = Application.builder()
                .applicant(user)
                .animal(animal)
                .message(req.message())
                .build();

        applicationRepository.save(application);
        return ResponseDto.success("입양 신청서 작성이 완료되었습니다.", null);
    }

    @Override
    public ResponseDto<ApplicationListRes.PageResponse> getApplications(
            UserPrincipal principal,
            int page,
            int size,
            String[] sort,
            Long animalId,
            Long applicantId,
            ApplicationStatus status,
            LocalDateTime from,
            LocalDateTime to
    ) {
        LocalDateTime fromUtc = DateUtils.kstToUtc(from);
        LocalDateTime toUtc = DateUtils.kstToUtc(to);

        boolean isUser = isUser(principal);
        boolean isStaff = isStaff(principal);
        boolean isAdmin = isAdmin(principal);

        if (!(isUser || isStaff || isAdmin)) {
            throw new AccessDeniedException("USER, STAFF, ADMIN 만 접근 가능합니다.");
        }

        Long searchApplicantId = applicantId;

        // 신청한 본인 것만 조회
        if (isUser) {
            searchApplicantId = principal.getId();
        }

        Pageable pageable = buildPageable(page, size, sort);

        // STAFF/ADMIN 이면 applicantId 그대로 사용 (null 이면 전체, 값 있으면 특정 유저 것만)
        Page<Application> result = applicationRepository.searchApplications(
                animalId,
                searchApplicantId,
                status,
                fromUtc,
                toUtc,
                pageable
        );

        List<ApplicationListRes> content = result.getContent().stream()
                .map(ApplicationListRes::from)
                .toList();

        ApplicationListRes.PageMeta meta= ApplicationListRes.PageMeta.from(result);

        ApplicationListRes.PageResponse pageResponse = ApplicationListRes.PageResponse.builder()
                .content(content)
                .meta(meta)
                .build();

        return ResponseDto.success("조회 성공", pageResponse);
    }

    @Override
    public ResponseDto<ApplicationDetailRes> getApplicationById(
            UserPrincipal principal,
            Long applicationId
    ) {
        ApplicationDetailRes data = null;

        boolean isUser = isUser(principal);
        boolean isStaff = isStaff(principal);
        boolean isAdmin = isAdmin(principal);

        if (!(isUser || isStaff || isAdmin)) {
            throw new AccessDeniedException("USER, STAFF, ADMIN 만 접근 가능합니다.");
        }

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("해당 입양 신청 내역을 찾을 수 없습니다."));

        if (isUser) {
            if (!application.getApplicant().getId().equals(principal.getId())){
                throw new AccessDeniedException("본인의 입양 신청만 조회할 수 있습니다.");
            }
        }

        data = ApplicationDetailRes.from(application);
        return ResponseDto.success("조회 성공", data);
    }

    @Override
    @Transactional
    public ResponseDto<Void> reviewApplicationById(
            UserPrincipal principal,
            Long applicationId,
            ApplicationUpdateReq req
    ) {
        if (!isStaff(principal)) {
            throw new AccessDeniedException("STAFF 외에 접근 불가합니다.");
        }

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("해당 입양 신청 내역을 찾을 수 없습니다."));

        ApplicationStatus status = application.getStatus();

        if (status == ApplicationStatus.APPLIED) {
            application.startReview();

            return ResponseDto.success("SUCCESS", null);
        }

        if (status == ApplicationStatus.REVIEW) {
            if (req.interviewAt() != null || req.homeCheck() != null) {
                application.updateApplicationInfo(req.interviewAt(), req.homeCheck());
            }
            return ResponseDto.success("SUCCESS", null);
        }

        throw new IllegalStateException("입양 신청서 상태가 유효하지 않습니다.");
    }

    @Override
    @Transactional
    public ResponseDto<Void> approveApplicationById(
            UserPrincipal principal,
            Long applicationId
    ) {
        if (!isStaff(principal)){
            throw new AccessDeniedException("STAFF 외에 접근 불가합니다.");
        }

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("해당 입양 신청 내역을 찾을 수 없습니다."));

        if (application.getStatus() != ApplicationStatus.REVIEW) {
            throw new IllegalStateException("REVIEW 상태에서만 입양 신청서를 승인할 수 있습니다.");
        }

        application.approve();
        application.
        return ResponseDto.success("SUCCESS", null);
    }

    @Override
    @Transactional
    public ResponseDto<Void> rejectApplicationById(
            UserPrincipal principal,
            Long applicationId,
            ApplicationRejectReq req
    ) {
        if (!isStaff(principal)){
            throw new AccessDeniedException("STAFF 외에 접근 불가합니다.");
        }

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("해당 입양 신청 내역을 찾을 수 없습니다."));

        if (application.getStatus() != ApplicationStatus.REVIEW) {
            throw new IllegalStateException("REVIEW 상태에서만 입양신청서를 거절할 수 있습니다.");
        }

        application.reject(req.reason());

        return ResponseDto.success("SUCCESS", null);
    }

    @Override
    @Transactional
    public ResponseDto<Void> cancelApplicationById(
            UserPrincipal principal,
            Long applicationId,
            ApplicationCancelReq req
    ) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("해당 입양 신청 내역을 찾을 수 없습니다."));

        boolean isUser = isUser(principal);
        boolean isStaff = isStaff(principal);
        boolean isAdmin = isAdmin(principal);

        if (!(isUser || isStaff || isAdmin)) {
            throw new AccessDeniedException("USER, STAFF, ADMIN 만 접근 가능합니다.");
        }

        if (isUser) {
            if (!application.getApplicant().getId().equals(principal.getId())){
                throw new AccessDeniedException("본인의 입양 신청만 취소할 수 있습니다.");
            }
        }

        if (application.getStatus() != ApplicationStatus.REVIEW) {
            throw new IllegalStateException("REVIEW 상태에서만 입양 신청서를 취소할 수 있습니다.");
        }

        application.cancel(req.reason());
        return ResponseDto.success("SUCCESS", null);
    }

    /** 권한 체크 메서드 */
    private boolean isUser(UserPrincipal principal) {
        return principal.getAuthorities().stream()
                .anyMatch(auth ->
                        auth.getAuthority().equals("ROLE_USER"));
    }
    private boolean isStaff(UserPrincipal principal) {
        return principal.getAuthorities().stream()
                .anyMatch(auth ->
                        auth.getAuthority().equals("ROLE_STAFF"));
    }

    private boolean isAdmin(UserPrincipal principal) {
        return principal.getAuthorities().stream()
                .anyMatch(auth ->
                        auth.getAuthority().equals("ROLE_ADMIN"));
    }

    private static final Set<String> ALLOWED_SORTS = Set.of("id", "status", "createdAt");

    private Pageable buildPageable(int page, int size, String[] sortParams) {
        Sort sort = Sort.by("createdAt").descending();

        if (sortParams != null && sortParams.length > 0) {
            List<Sort.Order> orders = new ArrayList<>();

            for(int i = 0; i < sortParams.length; i++) {
                String value = sortParams[i];

                String property;
                String direction;

                if (value.contains(",")) {
                    String[] parts = value.split(",", 2);
                    property = parts[0].trim();
                    direction = parts.length > 1 ? parts[1].trim() : "desc";
                } else {
                    property = value.trim();
                    String next = (i + 1 < sortParams.length) ? sortParams[i + 1].trim() : "";
                    if ("desc".equalsIgnoreCase(next) || "asc".equalsIgnoreCase(next)) {
                        direction = next;
                        i++;
                    } else {
                        direction = "desc";
                    }
                }

                if (ALLOWED_SORTS.contains(property)) {
                    Sort.Direction dir = "desc".equalsIgnoreCase(direction)
                            ? Sort.Direction.DESC
                            : Sort.Direction.ASC;

                    orders.add(new Sort.Order(dir, property));
                }
            }
            if (!orders.isEmpty()) sort = Sort.by(orders);
        }
        return PageRequest.of(page, size, sort);
    }
}