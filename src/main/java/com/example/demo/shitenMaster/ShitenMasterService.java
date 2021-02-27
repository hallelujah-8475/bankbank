package com.example.demo.shitenMaster;

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
public class ShitenMasterService {

	@Autowired
	ShitenMasterRepository shitenMasterRepositry;

	@PersistenceContext
	EntityManager entityManager;

	public ShitenMaster save(ShitenMaster entity) {

		return this.shitenMasterRepositry.save(entity);
	}

	public List<ShitenMaster> findAll() {
		return shitenMasterRepositry.findAll(Sort.by("id"));
	}

	public Optional<ShitenMaster> findById(Long id) {

		return this.shitenMasterRepositry.findById(id);
	}

	public Object findByShitenId(int shitenid) {

		Object results = entityManager
                .createQuery("from ShitenMaster where shitenid = :shitenid", ShitenMaster.class)
                .setParameter("shitenid", shitenid)
                .getSingleResult();

        return results;
	}

	public int findByMaxShitenId() {

		return (Integer)entityManager
	            .createQuery("select MAX(shitenid) from ShitenMaster")
	            .getSingleResult();

	}



}
