package com.example.demo.timecard;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TimeCardService {

	@Autowired
	TimeCardRepository timeCardRepositry;

	@PersistenceContext
	EntityManager entityManager;

	public TimeCard save(TimeCard entity) {

		return this.timeCardRepositry.save(entity);
	}

	public List<TimeCard> findAll() {


		return timeCardRepositry.findAll(Sort.by("id"));
	}

	public TimeCard findByKoinIdAndWorkDate(int id, String date) {

		TimeCard target = null;

		try {
			Query query = entityManager
					.createQuery("from TimeCard where id = :koinid AND workdate = :date", TimeCard.class)
					.setParameter("id", id)
					.setParameter("date", date);

			target = (TimeCard) query.getSingleResult();

		} catch (Exception e) {

			return null;
		}



        return target;
	}

	public List<String> findForKoinMasterAndTimeCard(String workDate) {

		String sql = "SELECT km.id, km.koinname, T.workstarttime, T.workendtime FROM KoinMaster km LEFT OUTER JOIN TimeCard T ON km.id = T.koinid Where T.workdate = '" + workDate + "' order by km.id ASC";

		Query q = entityManager.createQuery(sql);

		List<String> results = q.getResultList();

        return results;
	}


}
