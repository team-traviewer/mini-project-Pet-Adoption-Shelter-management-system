package org.example.miniprojpetadoptionshelter.repository.fromAnimal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;
import org.example.miniprojpetadoptionshelter.entity.fromAnimal.Foster;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FosterRepositoryImpl implements FosterRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Foster> searchFosters(Long fosterUserId, FosterStatus status, LocalDate startDate, LocalDate endDate) {

        StringBuilder jpql = new StringBuilder(
                "SELECT f FROM Foster f WHERE 1 = 1"
        );

        Map<String, Object> params = new HashMap<>();

        if (fosterUserId != null) {
            jpql.append(" AND f.fosterUser.id = :fosterUserId ");
            params.put("fosterUserId", fosterUserId);
        }

        if (status != null) {
            jpql.append(" AND f.status = :status ");
            params.put("status", status);
        }

        if (startDate != null) {
            jpql.append(" AND f.startDate >= :startDate ");
            params.put("startDate", startDate);
        }

        if (endDate != null) {
            jpql.append(" AND f.endDate <= :endDate ");
            params.put("endDate", endDate);
        }

        TypedQuery<Foster> query = em.createQuery(jpql.toString(), Foster.class);

        for (Map.Entry<String, Object> entry: params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<Foster> results = query.getResultList();
        return results;
    }
}
