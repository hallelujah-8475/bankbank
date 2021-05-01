package com.example.demo.shohinMaster;

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

@Service
@Transactional
public class ShohinMasterService {

	@Autowired
	ShohinMasterRepository shohinMasterRepositry;

	@Autowired
	ShohinMasterSpecifications shohinMasterSpecifications;

	@PersistenceContext
	EntityManager entityManager;

	public ShohinMaster save(ShohinMaster entity) {

		return this.shohinMasterRepositry.save(entity);
	}

	public List<ShohinMaster> findAll() {
		return shohinMasterRepositry.findAll(Sort.by("id"));
	}

	public Optional<ShohinMaster> findById(Long id) {

		return this.shohinMasterRepositry.findById(id);
	}

	public int findByMaxShohinId() {

		return (Integer)entityManager
	            .createQuery("select COALESCE(MAX(shohinid), 0) from ShohinMaster")
	            .getSingleResult();
	}

    public List<ShohinMaster> findUsers(ShohinMasterListForm shohinMasterListForm) {

    	List<ShohinMaster> list = new ArrayList<ShohinMaster>();

		list = shohinMasterRepositry.findAll(Specification
				.where(shohinMasterSpecifications.nameContains(shohinMasterListForm.getName())));

		return list;
    }

}
