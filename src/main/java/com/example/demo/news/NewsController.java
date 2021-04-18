package com.example.demo.news;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.accesslog.AccessLog;
import com.example.demo.accesslog.AccessLogService;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private AccessLogService accessLogService;


	@RequestMapping(value = "/news/detail")
	private String detail(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("newsForm") NewsForm newsForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var news = newsRepository.findById(id).get();

			BeanUtils.copyProperties(news, newsForm);
			newsForm.setId(id);
		}

		session.setAttribute("newsForm", newsForm);

		return "/news/detail";
	}

	@RequestMapping(value = "/news/edit")
	private String edit(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("newsForm") NewsForm newsForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var news = newsRepository.findById(id).get();

			BeanUtils.copyProperties(news, newsForm);
			newsForm.setId(id);
		}

		session.setAttribute("newsForm", newsForm);

		return "/news/edit";
	}

	@RequestMapping("/news/editCheck")
	public String editCheck(@Validated @ModelAttribute NewsForm newsForm, BindingResult result, HttpSession session) {

		session.setAttribute("newsForm", newsForm);

//		if(result.hasErrors()) {
//			return "/news/edit";
//		}

		return "/news/editCheck";
	}

	@PostMapping("/news/finish")
	public String finish(HttpSession session, @ModelAttribute("newsForm") NewsForm newsForm) {

		var news = new News();

		if(newsForm.getId() == null) {

			newsForm.setId((long) 0);
		}

		BeanUtils.copyProperties(newsForm, news);

		this.newsService.save(news);

		AccessLog accesslog = new AccessLog();

		LocalDateTime date1 = LocalDateTime.now();
		DateTimeFormatter dtformat1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String fdate1 = dtformat1.format(date1);

		 accesslog.setActsystemuserid(1);
		 accesslog.setActdatetime(fdate1);
		 accesslog.setActcontent("【システムユーザー】更新");
		 accesslog.setActresult("成功");

		this.accessLogService.save(accesslog);

		return "/news/finish";
	}

	@RequestMapping(value = "/news/list")
	public String list(Model model) {
        var list = newsService.findAll();
        model.addAttribute("list", list);
        return "/news/list";
	}

	@RequestMapping("/news/delete")
	public String delete(@RequestParam(name = "id", required = false) Long id, Model model) {

		this.newsRepository.deleteById(id);

		return this.list(model);
	}

	@RequestMapping("/news/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (NewsForm) session.getAttribute("newsForm");

		if(sessionEditForm == null) {

			return "redirect:/news/edit";
		}

		model.addAttribute("newsForm", sessionEditForm);

		return "/news/edit";
	}

	@RequestMapping("/news/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (NewsForm) session.getAttribute("newsForm");

		if(sessionEditForm == null) {

			return "redirect:/news/detail";
		}

		model.addAttribute("newsForm", sessionEditForm);

		return "/news/detail";
	}

}
