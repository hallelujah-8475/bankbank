package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@Autowired
	private LoginService userService;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private LoginValidator loginValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(loginValidator);
	}

	@RequestMapping(value = "/login")
	private String login() {


		return "/login";
	}

}
