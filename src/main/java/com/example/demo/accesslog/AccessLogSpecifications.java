package com.example.demo.accesslog;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AccessLogSpecifications {

    public Specification<AccessLog> actdatetimeFromContains(String name) {

    	return StringUtils.isBlank(name) ? null : new Specification<AccessLog>() {

            @Override
            public Predicate toPredicate(Root<AccessLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("actdatetime"), "%" + name + "%");
            }
    	};
    }

    public Specification<AccessLog> actdatetimeToContains(String name) {

    	return StringUtils.isBlank(name) ? null : new Specification<AccessLog>() {

    		@Override
    		public Predicate toPredicate(Root<AccessLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    			return cb.like(root.get("actdatetime"), "%" + name + "%");
    		}
    	};
    }

}
