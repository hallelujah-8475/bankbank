package com.example.demo.shohinMaster;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ShohinMasterSpecifications {

    public Specification<ShohinMaster> nameContains(String name) {

    	return StringUtils.isBlank(name) ? null : new Specification<ShohinMaster>() {

            @Override
            public Predicate toPredicate(Root<ShohinMaster> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("name"), "%" + name + "%");
            }
    	};
    }

}
