package com.example.demo.news;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.accesslog.AccessLogService;
import com.example.demo.shitenMaster.ShitenMaster;
import com.example.demo.shitenMaster.ShitenMasterService;
import com.example.demo.systemUser.PagenationHelper;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private AccessLogService accessLogService;

	@Autowired
	private ShitenMasterService shitenMasterService;

	@Autowired
    HttpSession session;

    private void setSelectTag(Model model) {

        var list = shitenMasterService.findAll();

        Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

        for(ShitenMaster entity : list) {

        	optionMap.put(entity.getShitenid(), entity.getShitenname());
        }

        model.addAttribute("optionMapList", optionMap);
    }

	@RequestMapping(value = "/news/detail")
	private String detail(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("newsForm") NewsForm newsForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var news = newsRepository.findById(id).get();

			BeanUtils.copyProperties(news, newsForm);
			newsForm.setId(id);
			newsForm.setShitenname(shitenMasterService.findByShitenid(newsForm.getShitenid()).getShitenname());
		}

		session.setAttribute("newsForm", newsForm);

		return "/news/detail";
	}

	@RequestMapping(value = "/news/edit")
	private String edit(Model model, @RequestParam(name = "id", required = false) Long id, @ModelAttribute("newsForm") NewsForm newsForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var news = newsRepository.findById(id).get();

			BeanUtils.copyProperties(news, newsForm);
			newsForm.setId(id);
			newsForm.setShitenname(shitenMasterService.findByShitenid(newsForm.getShitenid()).getShitenname());
		}

		this.setSelectTag(model);

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

		return "/news/finish";
	}

	@RequestMapping(value = "/news/pagenate")
	public String pagenate(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable) {

		NewsListForm newsListForm = (NewsListForm)session.getAttribute("newsListForm");

		return this.list(model, newsListForm, pageable);
	}

	@RequestMapping(value = "/news/list")
	public String list(Model model, @ModelAttribute("newsListForm") NewsListForm newsListForm, @PageableDefault(page = 0, size = 5) Pageable pageable) {

		session.setAttribute("newsListForm", newsListForm);

		Page<News> list = newsService.findUsers(newsListForm, pageable);

		model.addAttribute("list", list.getContent());
        model.addAttribute("newsListForm",newsListForm);
        model.addAttribute("page",PagenationHelper.createPagenation(list));

        this.setSelectTag(model);

        return "/news/list";
	}

	@RequestMapping("/news/delete")
	public String delete(@RequestParam(name = "id", required = false) Long id, Model model, @ModelAttribute("newsListForm") NewsListForm newsListForm, @PageableDefault(page = 0, size = 5) Pageable pageable) {

		this.newsRepository.deleteById(id);

		return this.list(model, newsListForm, pageable);
	}

	@RequestMapping("/news/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (NewsForm) session.getAttribute("newsForm");

		if(sessionEditForm == null) {

			return "redirect:/news/edit";
		}

		model.addAttribute("newsForm", sessionEditForm);

		this.setSelectTag(model);

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
