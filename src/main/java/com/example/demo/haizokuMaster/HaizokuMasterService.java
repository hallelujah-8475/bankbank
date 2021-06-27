package com.example.demo.haizokuMaster;

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

import com.example.demo.koinMaster.KoinMaster;
import com.example.demo.koinMaster.KoinMasterRepository;
import com.example.demo.koinMaster.KoinMasterSpecifications;

@Service
@Transactional
public class HaizokuMasterService {

	@Autowired
	KoinMasterRepository koinMasterRepositry;

	@Autowired
	HaizokuMasterSpecifications haizokuMasterSpecifications;

	@Autowired
	KoinMasterSpecifications koinMasterSpecifications;

	@PersistenceContext
	EntityManager entityManager;

	public List<KoinMaster> findAll() {
		return koinMasterRepositry.findAll(Sort.by("id"));
	}

    public Page<KoinMaster> findUsers(HaizokuMasterListForm haizokuMasterListForm, Pageable pageable) {

    	return koinMasterRepositry.findAll(Specification
    										.where(koinMasterSpecifications.shitenIdContains(haizokuMasterListForm.getShitenid()))
    										.and(koinMasterSpecifications.koinNameContains(haizokuMasterListForm.getKoinname()))
    										,pageable);
    }

}
