package org.example.miniprojpetadoptionshelter.service.fromAnimal.impl;

import com.mysql.cj.conf.StringProperty;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;
import org.example.miniprojpetadoptionshelter.common.utils.DateUtils;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.application.response.ApplicationListRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCancelReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCloseReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterListRes;
import org.example.miniprojpetadoptionshelter.entity.animal.Animal;
import org.example.miniprojpetadoptionshelter.entity.application.Application;
import org.example.miniprojpetadoptionshelter.entity.fromAnimal.Foster;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.example.miniprojpetadoptionshelter.repository.animal.AnimalRepository;
import org.example.miniprojpetadoptionshelter.repository.fromAnimal.FosterRepository;
import org.example.miniprojpetadoptionshelter.repository.user.UserRepository;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.fromAnimal.FosterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class FosterServiceImpl implements FosterService {

    private final FosterRepository fosterRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    @Override
    @Transactional
    public ResponseDto<Void> createFoster(UserPrincipal principal, @Valid FosterCreateReq req) {

        User user = userRepository.findById(req.fosterUserId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));

        Animal animal = animalRepository.findById(req.animalId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않은 동물입니다."));

        boolean existsActive = fosterRepository.existsByAnimalIdAndStatus(animal.getId(), FosterStatus.ACTIVE);

        if (existsActive) {
            throw new IllegalStateException("이미 진행중인 임시보호가 있어 새로운 임시보호를 등록할 수 없습니다.");
        }

        if (!isStaff(principal)) {
            throw new AccessDeniedException("STAFF만 임시보호를 생성할 수 있습니다.");
        }

        Foster foster = Foster.builder()
                .animal(animal)
                .fosterUser(user)
                .startDate(req.startDate())
                .note(req.note())
                .build();

        fosterRepository.save(foster);

        return ResponseDto.success("임시 보호등록이 완료되었습니다.");
    }

    @Override
    public ResponseDto<FosterListRes.PageResponse> getFosters(
            UserPrincipal principal,
            int page,
            int size,
            String[] sort,
            Long fosterUserId,
            FosterStatus status,
            LocalDate startDate,
            LocalDate endDate
    ) {
        if (!isStaffOrAdmin(principal)) {
            throw new AccessDeniedException("임시보호 목록을 조회할 권한이 없습니다.");
        }

        Pageable pageable = buildPageable(page, size, sort);

        Page<Foster> result = fosterRepository.searchFosters(
                fosterUserId,
                status,
                startDate,
                endDate,
                pageable
        );

        List<FosterListRes> content = result.getContent().stream()
                .map(FosterListRes::from)
                .toList();

        FosterListRes.PageMeta meta= FosterListRes.PageMeta.from(result);

        FosterListRes.PageResponse pageResponse = FosterListRes.PageResponse.builder()
                .content(content)
                .meta(meta)
                .build();


        return ResponseDto.success("조회 성공", pageResponse);
    }

    @Override
    public ResponseDto<FosterDetailRes> getFosterDetail(UserPrincipal principal, Long fosterId) {
        FosterDetailRes data = null;

        if (!isStaffOrAdmin(principal)) {
            throw new AccessDeniedException("해당 임시보호 상세 정보를 조회할 접근 권한이 없습니다.");
        }

        Foster foster = fosterRepository.findById((fosterId))
                .orElseThrow(() -> new EntityNotFoundException("해당 임시보호 id를 찾을 수 없습니다."));

        data = FosterDetailRes.from(foster);
        return ResponseDto.success("조회 성공", data);
    }

    @Override
    @Transactional
    public ResponseDto<Void> closeFoster(UserPrincipal principal, Long fosterId, FosterCloseReq req) {
        Foster foster = fosterRepository.findById((fosterId))
                .orElseThrow(() -> new EntityNotFoundException("해당 임시보호 id를 찾을 수 없습니다."));

        if (!isStaffOrAdmin(principal)) {
            throw new AccessDeniedException("접근 권한이 없습니다.");
        }

        if (foster.getStatus() != FosterStatus.ACTIVE) {
            throw new IllegalStateException("ACTIVE 상태의 임시보호만 종료할 수 있습니다.");
        }

        // record에 note변수 getter로 가져올때 () 붙임.
        foster.closeFoster(req.note());
        return ResponseDto.success("SUCCESS", null);
    }

    @Override
    @Transactional
    public ResponseDto<Void> cancelFoster(UserPrincipal principal, Long fosterId, FosterCancelReq req) {

        if (!isStaffOrAdmin(principal)) {
            throw new AccessDeniedException("접근 권한이 없습니다.");
        }

        Foster foster = fosterRepository.findById((fosterId))
                .orElseThrow(() -> new EntityNotFoundException("해당 임시보호 id를 찾을 수 없습니다."));

        if (foster.getStatus() != FosterStatus.ACTIVE) {
            throw new IllegalStateException("ACTIVE 상태의 임시보호만 취소할 수 있습니다.");
        }

        foster.cancelFoster(req.note());
        return ResponseDto.success("SUCCESS", null);
    }

    /** 권한 체크 메서드 */

    private boolean isStaff(UserPrincipal principal) {
        return principal.getAuthorities().stream()
                .anyMatch(auth ->
                        auth.getAuthority().equals("ROLE_STAFF"));

    }

    private boolean isStaffOrAdmin(UserPrincipal principal) {
        return principal.getAuthorities().stream()
                .anyMatch(
                        auth -> (
                                auth.getAuthority().equals("ROLE_STAFF"))
                                || auth.getAuthority().equals("ROLE_ADMIN"));
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
