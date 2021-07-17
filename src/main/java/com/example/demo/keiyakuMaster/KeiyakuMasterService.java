package com.example.demo.keiyakuMaster;

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
public class KeiyakuMasterService {

	@Autowired
	KeiyakuMasterRepository keiyakuMasterRepositry;

	@Autowired
	KeiyakuMasterSpecifications keiyakuMasterSpecifications;

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

	public int findByMaxId() {

		return (Integer)entityManager
				.createQuery("select COALESCE(MAX(id), 0) from KeiyakuMaster")
				.getSingleResult();
	}

	public List<KeiyakuMaster> findByKeiyakuRanking() {

		return entityManager
				.createQuery("select koinmaster.koinname, keiyakumaster.koinid, count(keiyakumaster.id) as result from KeiyakuMaster keiyakumaster left outer join KoinMaster koinmaster ON keiyakumaster.koinid = koinmaster.koinid where keiyakumaster.shoninflg = 1 group by keiyakumaster.koinid, koinmaster.koinname order by result desc")
				.getResultList();
	}

    public Page<KeiyakuMaster> findUsers(KeiyakuMasterListForm keiyakuMasterListForm, Pageable pageable) {

    	return keiyakuMasterRepositry.findAll(Specification
    										.where(keiyakuMasterSpecifications.shoninFlgContains(keiyakuMasterListForm.getShoninflg()))
    										,pageable);
    }

}
