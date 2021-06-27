package com.example.demo.news;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NewsSpecifications {

    public Specification<News> actdatetimeContains(String actdatetimeFrom, String actdatetimeTo) {

    	return StringUtils.isBlank(actdatetimeFrom) && StringUtils.isBlank(actdatetimeTo) ? null : new Specification<News>() {

            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.between(root.get("actdatetime"), actdatetimeFrom, actdatetimeTo);
            }
    	};
    }

}
