package com.example.demo.clientMaster;

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
public class ClientMasterService {

	@Autowired
	ClientMasterRepository clientMasterRepositry;

	@PersistenceContext
	EntityManager entityManager;

	public ClientMaster save(ClientMaster entity) {

		return this.clientMasterRepositry.save(entity);
	}

	public List<ClientMaster> findAll() {
		return clientMasterRepositry.findAll(Sort.by("id"));
	}

	public Optional<ClientMaster> findById(Long id) {

		return this.clientMasterRepositry.findById(id);
	}


	public int findByMaxClientId() {

		return (Integer)entityManager
	            .createQuery("select COALESCE(MAX(clientid), 0) from ClientMaster")
	            .getSingleResult();

	}



}
