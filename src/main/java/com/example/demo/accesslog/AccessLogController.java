package com.example.demo.accesslog;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.systemUser.PagenationHelper;

@Controller
public class AccessLogController {

	@Autowired
	private AccessLogService accessLogService;

    @Autowired
    HttpSession session;

	@RequestMapping(value = "/accesslog/pagenate")
	public String pagenate(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable) {

		AccessLogListForm accessLogListForm = (AccessLogListForm)session.getAttribute("accesslogListForm");

		return this.list(model, accessLogListForm, pageable);
	}

	@RequestMapping(value = "/accesslog/list")
	public String list(Model model, @ModelAttribute("accesslogListForm") AccessLogListForm accessLogListForm, @PageableDefault(page = 0, size = 5) Pageable pageable) {

		session.setAttribute("accesslogListForm", accessLogListForm);

		Page<AccessLog> list = accessLogService.findUsers(accessLogListForm, pageable);

		model.addAttribute("list", list.getContent());
        model.addAttribute("accesslogListForm",accessLogListForm);
        model.addAttribute("page",PagenationHelper.createPagenation(list));

        return "/accesslog/list";
	}

}
