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

    public Specification<AccessLog> actdatetimeContains(String actdatetimeFrom, String actdatetimeTo) {

    	final String actdatetimeFromConvert = StringUtils.isBlank(actdatetimeFrom) ? "" : actdatetimeFrom.replace("-", "/") + " 00:00:00";
    	final String actdatetimeToConvert = StringUtils.isBlank(actdatetimeTo) ? "" : actdatetimeTo.replace("-", "/") + " 23:59:59";

    	return StringUtils.isBlank(actdatetimeFrom) && StringUtils.isBlank(actdatetimeTo) ? null : new Specification<AccessLog>() {

            @Override
            public Predicate toPredicate(Root<AccessLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.between(root.get("actdatetime"), actdatetimeFromConvert, actdatetimeToConvert);
            }
    	};
    }

}
