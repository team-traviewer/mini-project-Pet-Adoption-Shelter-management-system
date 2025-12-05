package org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response;

import lombok.Builder;
import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;
import org.example.miniprojpetadoptionshelter.common.utils.DateUtils;
import org.example.miniprojpetadoptionshelter.dto.application.response.ApplicationListRes;
import org.example.miniprojpetadoptionshelter.entity.fromAnimal.Foster;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public record FosterListRes(
        Long id,
        String animalSpecies,
        LocalDate startDate,
        FosterStatus status,
        String createdAtKst,
        String createdUtcIso
) {
    public static FosterListRes from(Foster foster) {
        if (foster == null) return null;

        return new FosterListRes(
                foster.getId(),
                foster.getAnimal().getSpecies(),
                foster.getStartDate(),
                foster.getStatus(),
                DateUtils.toKstString(foster.getCreatedAt()),
                DateUtils.toUtcString(foster.getCreatedAt())
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
        public static FosterListRes.PageMeta from(Page<?> p) {
            String sort = p.getSort().toString(); // ex) createdAt: DESC
            return FosterListRes.PageMeta.builder()
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
            List<FosterListRes> content,
            FosterListRes.PageMeta meta
    ){}
}
