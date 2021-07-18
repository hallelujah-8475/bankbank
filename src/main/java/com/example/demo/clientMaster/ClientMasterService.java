package com.example.demo.clientMaster;

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
public class ClientMasterService {

	@Autowired
	ClientMasterRepository clientMasterRepositry;

	@Autowired
	ClientMasterSpecifications clientMasterSpecifications;

	@PersistenceContext
	EntityManager entityManager;

	public ClientMaster save(ClientMaster entity) {

		return this.clientMasterRepositry.save(entity);
	}

	public List<ClientMaster> findAll() {
		return clientMasterRepositry.findAll(Sort.by("id"));
	}

	public int findByMaxClientId() {

		return (Integer)entityManager
	            .createQuery("select COALESCE(MAX(clientid), 0) from ClientMaster")
	            .getSingleResult();

	}

    public Page<ClientMaster> findUsers(ClientMasterListForm clientMasterListForm, Pageable pageable) {

		return clientMasterRepositry.findAll(Specification
				.where(clientMasterSpecifications.clientnameContains(clientMasterListForm.getName()))
				.and(clientMasterSpecifications.prefectureContains(clientMasterListForm.getPrefecture()))
				,pageable);

    }
}
