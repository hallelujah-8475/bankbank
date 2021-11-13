package com.example.demo.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.keiyakuMaster.KeiyakuMasterRepository;
import com.example.demo.koinMaster.KoinMasterRepository;
import com.example.demo.news.NewsRepository;
import com.example.demo.news.NewsService;
import com.example.demo.systemUser.SystemUserRepository;

@Controller
public class LoginController {

	@Autowired
	KeiyakuMasterRepository keiyakuMasterRepositry;

	@Autowired
	KoinMasterRepository koinMasterRepositry;

	@Autowired
	SystemUserRepository systemUserRepositry;

	@Autowired
    HttpSession session;

	@Autowired
	private NewsService newsService;
	
	@Autowired
	HttpServletRequest request;

	@Autowired
	private NewsRepository newsRepositry;
	
	@RequestMapping(value = "/login")
	private String login(Model model) {

		if(request.getParameter("error") != null) {
			
			model.addAttribute("error", "ログインIDかパスワードに誤りがあります");
		}
			
		return "login";
	}
	
	@RequestMapping(value = "/logout")
	private String logout() {

		return "login";
	}

	@RequestMapping(value = "/index")
	private String signin(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("userName", auth.getName());

    	var miShoninCount = keiyakuMasterRepositry.countByShoninflg(0);

    	model.addAttribute("miShoninCount", Integer.toString(miShoninCount));

        var list = newsRepositry.findAllByKokaiflgOrderByIdAsc(1);
        model.addAttribute("list", list);

		return "index";
	}

}
