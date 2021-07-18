package com.example.demo.shohinMaster;

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

import com.example.demo.systemUser.PagenationHelper;

@Controller
public class ShohinMasterController {

	@Autowired
	private ShohinMasterService shohinMasterService;

	@Autowired
	private ShohinMasterRepository shohinMasterRepository;

    @Autowired
    HttpSession session;

	@RequestMapping(value = "/shohinMaster/detail")
	private String detail(@RequestParam("id") int id, @ModelAttribute("shohinMasterForm") ShohinMasterForm shohinMasterForm, HttpSession session) {

		BeanUtils.copyProperties(shohinMasterRepository.findById(id), shohinMasterForm);

		session.setAttribute("shohinMasterForm", shohinMasterForm);

		return "/shohinMaster/detail";
	}

	@RequestMapping(value = "/shohinMaster/edit")
	private String edit(@RequestParam(name = "id", required = true, defaultValue = "0") int id, @ModelAttribute("shohinMasterForm") ShohinMasterForm shohinMasterForm, HttpSession session) {

		if(id == 0) {
			// 新規登録
		}else {
			// 更新
			BeanUtils.copyProperties(shohinMasterRepository.findById(id), shohinMasterForm);
			shohinMasterForm.setId(id);
		}

		session.setAttribute("shohinMasterForm", shohinMasterForm);

		return "/shohinMaster/edit";
	}

	@RequestMapping("/shohinMaster/editCheck")
	public String editCheck(HttpSession session, @Validated @ModelAttribute ShohinMasterForm shohinMasterForm, BindingResult result) {

		session.setAttribute("shohinMasterForm", shohinMasterForm);

		if(result.hasErrors()) {
			return "/shohinMaster/edit";
		}

		return "/shohinMaster/editCheck";
	}

	@PostMapping("/shohinMaster/finish")
	public String finish(HttpSession session, @ModelAttribute("shohinMasterForm") ShohinMasterForm shohinMasterForm) {

		var shohinMaster = new ShohinMaster();

		BeanUtils.copyProperties(shohinMasterForm, shohinMaster);

		this.shohinMasterService.save(shohinMaster);

		return "/shohinMaster/finish";
	}

	@RequestMapping(value = "/shohinMaster/pagenate")
	public String pagenate(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable) {

		ShohinMasterListForm shohinMasterListForm = (ShohinMasterListForm)session.getAttribute("shohinMasterListForm");

		return this.list(model, shohinMasterListForm, pageable);
	}

	@RequestMapping(value = "/shohinMaster/list")
	public String list(Model model, @ModelAttribute("shohinMasterListForm") ShohinMasterListForm shohinMasterListForm, @PageableDefault(page = 0, size = 10) Pageable pageable) {

		session.setAttribute("shohinMasterListForm", shohinMasterListForm);

		Page<ShohinMaster> list = shohinMasterService.findUsers(shohinMasterListForm, pageable);

		model.addAttribute("list", list.getContent());
        model.addAttribute("shohinMasterListForm",shohinMasterListForm);
        model.addAttribute("page",PagenationHelper.createPagenation(list));

        return "/shohinMaster/list";
	}

	@RequestMapping("/shohinMaster/delete")
	public String delete(@RequestParam("id") int id, Model model, @ModelAttribute("shohinMasterListForm") ShohinMasterListForm shohinMasterListForm, @PageableDefault(page = 0, size = 10) Pageable pageable) {

		this.shohinMasterRepository.deleteById(id);

		return this.list(model, shohinMasterListForm, pageable);
	}

	@RequestMapping("/shohinMaster/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (ShohinMasterForm) session.getAttribute("shohinMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/shohinMaster/edit";
		}

		model.addAttribute("shohinMasterForm", sessionEditForm);

		return "/shohinMaster/edit";
	}

	@RequestMapping("/shohinMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (ShohinMasterForm) session.getAttribute("shohinMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/shohinMaster/detail";
		}

		model.addAttribute("shohinMasterForm", sessionEditForm);

		return "/shohinMaster/detail";
	}

}
