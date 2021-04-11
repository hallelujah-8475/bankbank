package com.example.demo.koinMaster;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class KoinMasterSpecifications {

    public Specification<KoinMaster> shitenIdContains(int shitenid) {

        return shitenid == 0 ? null : new Specification<KoinMaster>() {

        	@Override
            public Predicate toPredicate(Root<KoinMaster> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        		return criteriaBuilder.equal(root.join("shitenmaster", JoinType.LEFT).get("id"), shitenid);
            }
        };
    }

    public Specification<KoinMaster> koinNameContains(String koinname) {

    	return StringUtils.isBlank(koinname) ? null : new Specification<KoinMaster>() {

            @Override
            public Predicate toPredicate(Root<KoinMaster> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("koinname"), "%" + koinname + "%");
            }
    	};
    }

}
