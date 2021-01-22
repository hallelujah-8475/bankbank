package com.example.demo.koinMaster;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KoinMasterService {

	@Autowired
	KoinMasterRepository userRepositry;

	public KoinMaster save(KoinMaster entity) {

		return this.userRepositry.save(entity);
	}

	public List<KoinMaster> findAll() {
		return userRepositry.findAll(Sort.by("id"));
	}

	public Optional<KoinMaster> findById(Long id) {

		return this.userRepositry.findById(id);
	}


}
