package com.example.demo.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewsService {

	@Autowired
	NewsRepository newsRepositry;

	public News save(News entity) {

		return this.newsRepositry.save(entity);
	}

	public List<News> findAll() {
		return newsRepositry.findAll(Sort.by("id"));
	}

}
