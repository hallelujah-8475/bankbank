package com.example.demo.shitenMaster;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ShitenMasterSpecifications {

    public Specification<ShitenMaster> shitennameContains(String shitenname) {

    	return StringUtils.isBlank(shitenname) ? null : new Specification<ShitenMaster>() {

            @Override
            public Predicate toPredicate(Root<ShitenMaster> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("shitenname"), "%" + shitenname + "%");
            }
    	};
    }

    public Specification<ShitenMaster> prefectureContains(String prefecture) {

    	return StringUtils.isBlank(prefecture) ? null : new Specification<ShitenMaster>() {

    		@Override
    		public Predicate toPredicate(Root<ShitenMaster> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    			return cb.like(root.get("prefecture"), "%" + prefecture + "%");
    		}
    	};
    }

}
