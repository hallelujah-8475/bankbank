package com.example.demo.taioMaster;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaioMasterService {

	@Autowired
	TaioMasterRepository taioMasterRepositry;

	@PersistenceContext
	EntityManager entityManager;

	public TaioMaster save(TaioMaster entity) {

		return this.taioMasterRepositry.save(entity);
	}

	public List<TaioMaster> findAll() {
		return taioMasterRepositry.findAll(Sort.by("id"));
	}

	public Optional<TaioMaster> findById(Long id) {

		return this.taioMasterRepositry.findById(id);
	}


	public int findByMaxTaioId() {

		return (Integer)entityManager
	            .createQuery("select COALESCE(MAX(taioid), 0) from TaioMaster")
	            .getSingleResult();

	}



}
