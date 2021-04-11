package com.example.demo.koinMaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.haizokuMaster.HaizokuMasterListForm;

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

	public Optional<KoinMaster> findById(Long id) {

		return this.koinMasterRepositry.findById(id);
	}

	public int findByMaxKoinId() {

		return (Integer)entityManager
	            .createQuery("select COALESCE(MAX(koinid), 0) from KoinMaster")
	            .getSingleResult();

	}

    public List<KoinMaster> findUsers(HaizokuMasterListForm haizokuMasterListForm) {

    	List<KoinMaster> list = new ArrayList<KoinMaster>();

    	list = koinMasterRepositry.findAll(Specification
    										.where(koinMasterSpecifications.shitenIdContains(haizokuMasterListForm.getShitenid()))
    										.and(koinMasterSpecifications.koinNameContains(haizokuMasterListForm.getKoinname())));
    	return list;
    }


}
