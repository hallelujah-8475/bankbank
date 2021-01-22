package com.example.demo.systemUser;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SystemUserService {

	@Autowired
	SystemUserRepository userRepositry;

	public SystemUser save(SystemUser entity) {

		return this.userRepositry.save(entity);
	}

	public List<SystemUser> findAll() {
		return userRepositry.findAll(Sort.by("id"));
	}

	public Optional<SystemUser> findById(Long id) {

		return this.userRepositry.findById(id);
	}


}
