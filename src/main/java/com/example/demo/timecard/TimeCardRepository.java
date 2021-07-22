package com.example.demo.timecard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TimeCardRepository extends JpaRepository<TimeCard, Integer> {

	List<TimeCard> findByWorkdateEqualsOrderById(String workdate);
	
	TimeCard findById(int id);
}
