package com.example.demo.clientMaster;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ClientMasterSpecifications {

    public Specification<ClientMaster> clientnameContains(String clientname) {

    	return StringUtils.isBlank(clientname) ? null : new Specification<ClientMaster>() {

            @Override
            public Predicate toPredicate(Root<ClientMaster> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("name"), "%" + clientname + "%");
            }
    	};
    }

    public Specification<ClientMaster> prefectureContains(String prefecture) {

    	return StringUtils.isBlank(prefecture) ? null : new Specification<ClientMaster>() {

    		@Override
    		public Predicate toPredicate(Root<ClientMaster> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    			return cb.like(root.get("prefecture"), "%" + prefecture + "%");
    		}
    	};
    }

}
