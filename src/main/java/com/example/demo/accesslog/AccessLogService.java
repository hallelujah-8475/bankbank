package com.example.demo.accesslog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessLogService {

	@Autowired
	AccessLogRepository accessLogRepository;

	@Autowired
	AccessLogSpecifications accessLogSpecifications;

	@PersistenceContext
	EntityManager entityManager;

	public AccessLog save(long id, String content, String result) {

		AccessLog accesslog = new AccessLog();

		LocalDateTime date1 = LocalDateTime.now();
		DateTimeFormatter dtformat1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String fdate1 = dtformat1.format(date1);

//		 accesslog.setActsystemuserid(1);
		 accesslog.setActdatetime(fdate1);
		 accesslog.setActcontent(content);
		 accesslog.setActresult(result);

		return this.accessLogRepository.save(accesslog);
	}

	public List<AccessLog> findAll() {
		return accessLogRepository.findAll(Sort.by("id"));
	}

    public Page<AccessLog> findUsers(AccessLogListForm accessLogListForm, Pageable pageable) {

    	return accessLogRepository.findAll(Specification
    										.where(accessLogSpecifications.actdatetimeContains(accessLogListForm.getActdatetimeFrom(), accessLogListForm.getActdatetimeTo()))
    										,pageable);
    }
}
