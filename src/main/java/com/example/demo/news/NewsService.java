package com.example.demo.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewsService {

	@Autowired
	NewsRepository newsRepositry;

	@Autowired
	NewsSpecifications newsSpecifications;

	public News save(News entity) {

		return this.newsRepositry.save(entity);
	}

    public Page<News> findUsers(NewsListForm newsListForm, Pageable pageable) {

    	return newsRepositry.findAll(Specification
    										.where(newsSpecifications.actdatetimeContains(newsListForm.getKokaistartdate(), newsListForm.getKokaienddate()))
    										,pageable);
    }

}
