package com.example.demo.keiyakuMaster;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KeiyakuMasterService {

	@Autowired
	KeiyakuMasterRepository keiyakuMasterRepositry;

	@PersistenceContext
	EntityManager entityManager;

	public KeiyakuMaster save(KeiyakuMaster entity) {

		return this.keiyakuMasterRepositry.save(entity);
	}

	public List<KeiyakuMaster> findAll() {
		return keiyakuMasterRepositry.findAll(Sort.by("id"));
	}

	public int findByMaxKeiyakuId() {

		return (Integer)entityManager
	            .createQuery("select COALESCE(MAX(keiyakuid), 0) from KeiyakuMaster")
	            .getSingleResult();

	}

}
