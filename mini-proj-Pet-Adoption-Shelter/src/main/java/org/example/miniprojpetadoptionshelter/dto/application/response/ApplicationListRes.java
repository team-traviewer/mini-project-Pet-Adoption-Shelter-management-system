package org.example.miniprojpetadoptionshelter.dto.application.response;

import lombok.Builder;
import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.common.enums.Species;
import org.example.miniprojpetadoptionshelter.common.utils.DateUtils;
import org.example.miniprojpetadoptionshelter.entity.application.Application;
import org.springframework.data.domain.Page;

import java.util.List;

public record ApplicationListRes(
        Long id,
        String animalSpecies,
        ApplicationStatus status,
        String message,
        String applicantName,
        String createdAtKst
) {
    public static ApplicationListRes from(Application application) {
        if (application == null) return null;

        return new ApplicationListRes(
                application.getId(),
                application.getAnimal().getSpecies(),
                application.getStatus(),
                application.getMessage(),
                application.getApplicant().getName(),
                DateUtils.toKstString(application.getCreatedAt())
        );
    }

    // === 페이지 정보 메타 === //
    @Builder
    public record PageMeta(
            int page,
            int size,
            long totalElements,
            int totalPages,
            boolean hasNext,
            boolean hasPrevious,
            String sort
    ) {
        public static PageMeta from(Page<?> p) {
            String sort = p.getSort().toString(); // ex) createdAt: DESC
            return PageMeta.builder()
                    .page(p.getNumber())
                    .size(p.getSize())
                    .totalElements(p.getTotalElements())
                    .totalPages(p.getTotalPages())
                    .hasNext(p.hasNext())
                    .hasPrevious(p.hasPrevious())
                    .sort(sort)
                    .build();
        }
    }

    // === OffSet 기반 응답 ===
    @Builder
    public record PageResponse(
            List<ApplicationListRes> content,
            PageMeta meta
    ){}
}
