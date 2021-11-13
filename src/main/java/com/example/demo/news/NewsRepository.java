package com.example.demo.news;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsRepository extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News> {

	
	public News findById(int id);
	
	public List<News> findAllByKokaiflgOrderByIdAsc(int kokaiflg);
}
