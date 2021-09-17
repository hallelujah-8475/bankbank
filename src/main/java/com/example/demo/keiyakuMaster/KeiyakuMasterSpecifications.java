package com.example.demo.keiyakuMaster;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class KeiyakuMasterSpecifications {

    public Specification<KeiyakuMaster> shoninFlgContains(int shoninFlg) {

        return shoninFlg == 99 || shoninFlg == 0 ? null : new Specification<KeiyakuMaster>() {

        	@Override
            public Predicate toPredicate(Root<KeiyakuMaster> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        		return criteriaBuilder.equal(root.get("shoninflg"), shoninFlg);
            }
        };
    }
}
