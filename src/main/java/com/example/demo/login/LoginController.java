package com.example.demo.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.keiyakuMaster.KeiyakuMasterRepository;

@Controller
public class LoginController {

	@Autowired
	KeiyakuMasterRepository keiyakuMasterRepositry;

	@Autowired
    HttpSession session;

	@RequestMapping(value = "/login")
	private String login() {

		return "login";
	}

	@RequestMapping(value = "/logout")
	private String logout() {

		return "login";
	}

	@RequestMapping(value = "/index")
	private String signin(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Principalからログインユーザの情報を取得
        String userName = auth.getName();
        model.addAttribute("userName", userName);

    	var miShoninCount = keiyakuMasterRepositry.countByShoninflg(0);

    	model.addAttribute("miShoninCount", Integer.toString(miShoninCount));

		return "index";
	}

}
