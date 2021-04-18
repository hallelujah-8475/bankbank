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

	public List<KeiyakuMaster> findByKeiyakuRanking() {

		return entityManager
				.createQuery("select koinmaster.koinname, keiyakumaster.koinid, count(keiyakumaster.id) as result from KeiyakuMaster keiyakumaster left outer join KoinMaster koinmaster ON keiyakumaster.koinid = koinmaster.koinid where keiyakumaster.shoninflg = 1 group by keiyakumaster.koinid, koinmaster.koinname order by result desc")
				.getResultList();
	}

}
