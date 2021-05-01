package com.example.demo.systemUser;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SystemUserSpecifications {

    public Specification<SystemUser> nameContains(String name) {

    	return StringUtils.isBlank(name) ? null : new Specification<SystemUser>() {

            @Override
            public Predicate toPredicate(Root<SystemUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("name"), "%" + name + "%");
            }
    	};
    }

    public Specification<SystemUser> roleContains(String role) {

    	return StringUtils.isBlank(role) ? null : new Specification<SystemUser>() {

    		@Override
    		public Predicate toPredicate(Root<SystemUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    			return cb.like(root.get("role"), "%" + role + "%");
    		}
    	};
    }

}
