package org.example.miniprojpetadoptionshelter.repository.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.entity.application.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ApplicationRepositoryImpl implements ApplicationRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Application> searchApplications(
            Long animalId,
            Long searchApplicantId,
            ApplicationStatus status,
            LocalDateTime fromUtc,
            LocalDateTime toUtc,
            Pageable pageable
    ) {
        StringBuilder jpql = new StringBuilder(
                "SELECT a FROM Application a WHERE 1 = 1"
        );

        StringBuilder countJpql = new StringBuilder(
                "SELECT COUNT(a) FROM Application a WHERE 1 = 1"
        );

        Map<String, Object> params = new HashMap<>();

        if (animalId != null) {
            jpql.append(" AND a.animal.id = :animalId");
            countJpql.append(" AND a.animal.id = :animalId");
            params.put("animalId", animalId);
        }

        if (searchApplicantId != null) {
            jpql.append(" AND a.applicant.id = :applicantId");
            countJpql.append(" AND a.applicant.id = :applicantId");
            params.put("applicantId", searchApplicantId);
        }

        if (status != null) {
            jpql.append(" AND a.status.id = :status");
            countJpql.append(" AND a.status.id = :status");
            params.put("status", status);
        }

        if (fromUtc != null) {
            jpql.append(" AND a.createdAt >= :fromUtc");
            countJpql.append(" AND a.createdAt >= :fromUtc");
            params.put("fromUtc", fromUtc);
        }

        if (toUtc != null) {
            jpql.append(" AND a.createdAt >= :toUtc");
            countJpql.append(" AND a.createdAt >= :toUtc");
            params.put("toUtc", toUtc);
        }

        // 정렬
        Sort sort = pageable.getSort();
        if (sort.isSorted()){
            jpql.append(" ORDER BY");
            List<String> order = new ArrayList<>();

            for (Sort.Order o : sort) {
                String property = o.getProperty();
                String direction = o.isAscending() ? "ASC" : "DESC";

                order.add("a." + property + " " + direction);
            }

            jpql.append(String.join(", ", order));
        } else {
            jpql.append(" ORDER BY a.createdAt DESC");
        }

        // 데이터 조리 쿼리
        TypedQuery<Application> query = em.createQuery(jpql.toString(), Application.class);
        params.forEach(query::setParameter);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Application> content = query.getResultList();

        // 카운트 쿼리
        TypedQuery<Long> countQuery = em.createQuery(countJpql.toString(), Long.class);
        params.forEach(countQuery::setParameter);

        Long total = countQuery.getSingleResult();

        return new PageImpl<>(content, pageable, total);
    }
}