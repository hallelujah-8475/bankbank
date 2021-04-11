package com.example.demo.accesslog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessLogService {

	@Autowired
	AccessLogRepository accessLogRepository;

	public AccessLog save(AccessLog entity) {

		return this.accessLogRepository.save(entity);
	}

	public List<AccessLog> findAll() {
		return accessLogRepository.findAll(Sort.by("id"));
	}


}
