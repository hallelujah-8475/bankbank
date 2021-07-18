package com.example.demo.koinMaster;

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
public class KoinMasterService {

	@Autowired
	KoinMasterRepository koinMasterRepositry;

	@Autowired
	KoinMasterSpecifications koinMasterSpecifications;

	@PersistenceContext
	EntityManager entityManager;

	public KoinMaster save(KoinMaster entity) {

		return this.koinMasterRepositry.save(entity);
	}

	public List<KoinMaster> findAll() {
		return koinMasterRepositry.findAll(Sort.by("id"));
	}

    public Page<KoinMaster> findUsers(KoinMasterListForm koinMasterListForm, Pageable pageable) {

    	return koinMasterRepositry.findAll(Specification
    										.where(koinMasterSpecifications.shitenIdContains(koinMasterListForm.getShitenid()))
    										.and(koinMasterSpecifications.koinNameContains(koinMasterListForm.getKoinname()))
    										,pageable);
    }


}
