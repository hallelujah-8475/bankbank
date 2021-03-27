package com.example.demo.shitenMaster;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ShitenMasterSpecifications {

    public Specification<ShitenMaster> nameContains(int shitenid) {

        return shitenid == 0 ? null : new Specification<ShitenMaster>() {

        	@Override
            public Predicate toPredicate(Root<ShitenMaster> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        		return criteriaBuilder.equal(root.join("koinlist", JoinType.LEFT).get("shitenid"), shitenid);
            }
        };
    }

}
