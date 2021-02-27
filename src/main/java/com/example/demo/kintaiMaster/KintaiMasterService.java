package com.example.demo.kintaiMaster;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.timecard.TimeCard;
import com.example.demo.timecard.TimeCardRepository;

@Service
@Transactional
public class KintaiMasterService {

	@Autowired
	KimtaiMasterRepository kintaiMasterRepositry;

	@Autowired
	TimeCardRepository timeCardRepositry;

	@PersistenceContext
	EntityManager entityManager;

	public KintaiMaster save(KintaiMaster entity) {

		return this.kintaiMasterRepositry.save(entity);
	}

	public List<TimeCard> findAll() {
		return timeCardRepositry.findAll(Sort.by("id"));
	}

	public Optional<KintaiMaster> findById(Long id) {

		return this.kintaiMasterRepositry.findById(id);
	}


	public int findByMaxTaioId() {

		return (Integer)entityManager
	            .createQuery("select COALESCE(MAX(taioid), 0) from TaioMaster")
	            .getSingleResult();

	}



}
