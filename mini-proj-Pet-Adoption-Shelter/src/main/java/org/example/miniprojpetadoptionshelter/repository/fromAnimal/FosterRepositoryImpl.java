package org.example.miniprojpetadoptionshelter.repository.fromAnimal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;
import org.example.miniprojpetadoptionshelter.entity.application.Application;
import org.example.miniprojpetadoptionshelter.entity.fromAnimal.Foster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FosterRepositoryImpl implements FosterRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Foster> searchFosters(
            Long fosterUserId,
            FosterStatus status,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {

        StringBuilder jpql = new StringBuilder(
                "SELECT f FROM Foster f WHERE 1 = 1"
        );

        StringBuilder countJpql = new StringBuilder(
                "SELECT COUNT(f) FROM Foster f WHERE 1 = 1"
        );

        Map<String, Object> params = new HashMap<>();

        if (fosterUserId != null) {
            jpql.append(" AND f.fosterUser.id = :fosterUserId ");
            countJpql.append(" AND f.fosterUser.id = :fosterUserId");
            params.put("fosterUserId", fosterUserId);
        }

        if (status != null) {
            jpql.append(" AND f.status = :status ");
            countJpql.append(" AND f.status = :status");
            params.put("status", status);
        }

        if (startDate != null) {
            jpql.append(" AND f.startDate >= :startDate ");
            countJpql.append(" AND f.startDate >= :startDate");
            params.put("startDate", startDate);
        }

        if (endDate != null) {
            jpql.append(" AND f.endDate <= :endDate ");
            countJpql.append(" AND f.endDate <= :endDate");
            params.put("endDate", endDate);
        }

        // 정렬
        Sort sort = pageable.getSort();
        if (sort.isSorted()){
            jpql.append(" ORDER BY ");
            List<String> order = new ArrayList<>();

            for (Sort.Order o : sort) {
                String property = o.getProperty();
                String direction = o.isAscending() ? "ASC" : "DESC";

                order.add("f." + property + " " + direction);
            }

            jpql.append(String.join(", ", order));
        } else {
            jpql.append(" ORDER BY f.createdAt DESC");
        }

        // 데이터 조리 쿼리
        TypedQuery<Foster> query = em.createQuery(jpql.toString(), Foster.class);
        params.forEach(query::setParameter);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Foster> content = query.getResultList();

        // 카운트 쿼리
        TypedQuery<Long> countQuery = em.createQuery(countJpql.toString(), Long.class);
        params.forEach(countQuery::setParameter);

        Long total = countQuery.getSingleResult();

        return new PageImpl<>(content, pageable, total);
    }
}
