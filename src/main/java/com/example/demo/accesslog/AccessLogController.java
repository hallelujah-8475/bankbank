package com.example.demo.accesslog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessLogController {

	@Autowired
	private AccessLogService accessLogService;

	@RequestMapping(value = "/accesslog/list")
	public String list(Model model) {
        var list = accessLogService.findAll();
        model.addAttribute("list", list);
        return "/accesslog/list";
	}

}
