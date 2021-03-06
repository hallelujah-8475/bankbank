package com.example.demo.login;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {

	@Autowired
	LoginRepository userRepositry;

	public Login save(Login entity) {

		return this.userRepositry.save(entity);
	}

	public List<Login> findAll() {
		return userRepositry.findAll(Sort.by("id"));
	}

	public Optional<Login> findById(Long id) {

		return this.userRepositry.findById(id);
	}


}
