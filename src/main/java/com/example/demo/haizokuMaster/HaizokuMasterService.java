package com.example.demo.haizokuMaster;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HaizokuMasterService {

	@Autowired
	HaizokuMasterRepository shitenMasterRepositry;

	@PersistenceContext
	EntityManager entityManager;

	public List<HaizokuMaster> findForHaizokuMaster() {

		String sql = "select SM.name, KM.age, KM.name from KoinMaster KM LEFT OUTER JOIN ShitenMaster SM ON SM.shitenid = KM.shitenid order by SM.name ASC";

		Query q = entityManager.createQuery(sql);

		List<HaizokuMaster> results = q.getResultList();

        return results;
	}

}
