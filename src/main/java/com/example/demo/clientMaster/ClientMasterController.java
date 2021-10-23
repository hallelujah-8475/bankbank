
package com.example.demo.clientMaster;

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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.accesslog.AccessLogService;
import com.example.demo.systemUser.PagenationHelper;

@Controller
public class ClientMasterController {

	@Autowired
	private ClientMasterService clientMasterService;

	@Autowired
	private ClientMasterRepository clientMasterRepository;

	@Autowired
	private AccessLogService accessLogService;
	
    @Autowired
    HttpSession session;
    
	@Autowired
	private ClientMasterValidator clientMasterValidator;

	@InitBinder("clientMasterForm")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(clientMasterValidator);
	}
    
	@RequestMapping(value = "/clientMaster/detail")
	private String detail(@RequestParam("id") int id, @ModelAttribute("clientMasterForm") ClientMasterForm clientMasterForm, HttpSession session) {

		if(id != 0) {

			// 更新
			var clientMaster = clientMasterRepository.findById(id);

			BeanUtils.copyProperties(clientMaster, clientMasterForm);
		}

		session.setAttribute("clientMasterForm", clientMasterForm);

		return "clientMaster/detail";
	}

	@RequestMapping(value = "/clientMaster/edit")
	private String edit(@RequestParam(name = "id", required = true, defaultValue = "0") int id, @ModelAttribute("clientMasterForm") ClientMasterForm clientMasterForm, HttpSession session) {

		if(id != 0) {
			// 更新
			var clientMaster = clientMasterRepository.findById(id);

			BeanUtils.copyProperties(clientMaster, clientMasterForm);
		}

		session.setAttribute("clientMasterForm", clientMasterForm);

		return "clientMaster/edit";
	}

	@RequestMapping("/clientMaster/editCheck")
	public String editCheck(@Validated @ModelAttribute ClientMasterForm clientMasterForm, BindingResult result, HttpSession session) {

		session.setAttribute("clientMasterForm", clientMasterForm);
		
		if(result.hasErrors()) {
			return "clientMaster/edit";
		}
		
		return "clientMaster/editCheck";
	}

	@PostMapping("/clientMaster/finish")
	public String finish(HttpSession session, @ModelAttribute("clientMasterForm") ClientMasterForm clientMasterForm) {

		var clientMaster = new ClientMaster();

		BeanUtils.copyProperties(clientMasterForm, clientMaster);

		this.clientMasterService.save(clientMaster);

		accessLogService.save(4, "更新", "成功");
		
		return "clientMaster/finish";
	}

	@RequestMapping(value = "/clientMaster/pagenate")
	public String pagenate(Model model, Pageable pageable) {

		ClientMasterListForm clientMasterListForm = (ClientMasterListForm)session.getAttribute("clientMasterListForm");

		return this.list(model, clientMasterListForm, pageable);
	}

	@RequestMapping(value = "/clientMaster/list")
	public String list(Model model, @ModelAttribute("clientMasterListForm") ClientMasterListForm clientMasterListForm, @PageableDefault(page = 0, size = 10) Pageable pageable) {

		session.setAttribute("clientMasterListForm", clientMasterListForm);

		Page<ClientMaster> list = clientMasterService.findUsers(clientMasterListForm, pageable);

		model.addAttribute("list", list.getContent());
        model.addAttribute("clientMasterListForm",clientMasterListForm);
        model.addAttribute("page",PagenationHelper.createPagenation(list));

        return "clientMaster/list";
	}

	@RequestMapping("/clientMaster/delete")
	public String delete(@RequestParam("id") int id, Model model, @ModelAttribute("clientMasterForm") ClientMasterForm clientMasterForm, HttpSession session, @ModelAttribute("clientMasterListForm") ClientMasterListForm clientMasterListForm, Pageable pageable) {

		this.clientMasterRepository.deleteById(id);

		return this.list(model, clientMasterListForm, pageable);
	}

	@RequestMapping("/clientMaster/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (ClientMasterForm) session.getAttribute("clientMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/clientMaster/edit";
		}

		model.addAttribute("clientMasterForm", sessionEditForm);

		return "clientMaster/edit";
	}

	@RequestMapping("/clientMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (ClientMasterForm) session.getAttribute("clientMasterForm");

		if(sessionEditForm == null) {

			return "redirect:clientMaster/detail";
		}

		model.addAttribute("clientMasterForm", sessionEditForm);

		return "clientMaster/detail";
	}

}
