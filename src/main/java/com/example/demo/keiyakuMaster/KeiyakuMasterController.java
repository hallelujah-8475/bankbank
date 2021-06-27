package com.example.demo.keiyakuMaster;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.example.demo.clientMaster.ClientMaster;
import com.example.demo.clientMaster.ClientMasterService;
import com.example.demo.koinMaster.KoinMaster;
import com.example.demo.koinMaster.KoinMasterService;
import com.example.demo.shohinMaster.ShohinMaster;
import com.example.demo.shohinMaster.ShohinMasterService;
import com.example.demo.systemUser.PagenationHelper;

@Controller
public class KeiyakuMasterController {

	@Autowired
	private KeiyakuMasterService keiyakuMasterService;

	@Autowired
	private ClientMasterService clientMasterService;

	@Autowired
	private KoinMasterService koinMasterService;

	@Autowired
	private ShohinMasterService shohinMasterService;

	@Autowired
	private KeiyakuMasterRepository keiyakuMasterRepository;

	@Autowired
	HttpServletRequest request;

    @Autowired
    HttpSession session;

//	@Autowired
//	private KeiyakuMasterValidator keiyakuMasterValidator;
//
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.addValidators(keiyakuMasterValidator);
//	}

	private void setShohinSelectTag(Model model) {

		var list = shohinMasterService.findAll();

		Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

		for(ShohinMaster entity : list) {

			optionMap.put(entity.getShohinid(), entity.getName());
		}

		model.addAttribute("shohinList", optionMap);
	}
	private void setClientSelectTag(Model model) {

		var list = clientMasterService.findAll();

		Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

		for(ClientMaster entity : list) {

			optionMap.put(entity.getClientid(), entity.getName());
		}

		model.addAttribute("clientList", optionMap);
	}

	private void setKoinSelectTag(Model model) {

		var list = koinMasterService.findAll();

		Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

		for(KoinMaster entity : list) {

			optionMap.put(entity.getKoinid(), entity.getKoinname());
		}

		model.addAttribute("koinList", optionMap);
	}

	@RequestMapping(value = "/keiyakuMaster/detail")
	private String detail(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("keiyakuMasterForm") KeiyakuMasterForm keiyakuMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var keiyakuMaster =keiyakuMasterRepository.findById(id).get();

			keiyakuMasterForm.setId(id);
			keiyakuMasterForm.setKeiyakuid(keiyakuMaster.getKeiyakuid());
			keiyakuMasterForm.setShohinid(keiyakuMaster.getShohinid());
			keiyakuMasterForm.setPrice(keiyakuMaster.getPrice());
			keiyakuMasterForm.setKinri(keiyakuMaster.getKinri());
			keiyakuMasterForm.setReturnlimit(keiyakuMaster.getReturnlimit());
			keiyakuMasterForm.setClientid(keiyakuMaster.getClientid());
			keiyakuMasterForm.setKoinid(keiyakuMaster.getKoinid());
			keiyakuMasterForm.setKoinname(koinMasterService.findByKoinid(keiyakuMaster.getKoinid()).getKoinname());
			keiyakuMasterForm.setClientname(clientMasterService.findByClientid(keiyakuMaster.getClientid()).getName());
			keiyakuMasterForm.setShohinname(shohinMasterService.findByShohinid(keiyakuMaster.getClientid()).getName());
		}

		session.setAttribute("keiyakuMasterForm",keiyakuMasterForm);

		return "/keiyakuMaster/detail";
	}

	@RequestMapping(value = "/keiyakuMaster/edit")
	private String edit(Model model, @RequestParam(name = "id", required = false) Long id, @ModelAttribute("keiyakuMasterForm") KeiyakuMasterForm keiyakuMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var keiyakuMaster =keiyakuMasterRepository.findById(id).get();

			keiyakuMasterForm.setId(id);
			keiyakuMasterForm.setKeiyakuid(keiyakuMaster.getKeiyakuid());
			keiyakuMasterForm.setShohinid(keiyakuMaster.getShohinid());
			keiyakuMasterForm.setPrice(keiyakuMaster.getPrice());
			keiyakuMasterForm.setKinri(keiyakuMaster.getKinri());
			keiyakuMasterForm.setReturnlimit(keiyakuMaster.getReturnlimit());
			keiyakuMasterForm.setClientid(keiyakuMaster.getClientid());
			keiyakuMasterForm.setKoinid(keiyakuMaster.getKoinid());
			keiyakuMasterForm.setKoinname(koinMasterService.findByKoinid(keiyakuMaster.getKoinid()).getKoinname());
			keiyakuMasterForm.setClientname(clientMasterService.findByClientid(keiyakuMaster.getClientid()).getName());
			keiyakuMasterForm.setShohinname(shohinMasterService.findByShohinid(keiyakuMaster.getClientid()).getName());
		}

		this.setShohinSelectTag(model);
		this.setClientSelectTag(model);
		this.setKoinSelectTag(model);

		session.setAttribute("keiyakuMasterForm",keiyakuMasterForm);

		return "/keiyakuMaster/edit";
	}

	@RequestMapping("/keiyakuMaster/editCheck")
	public String editCheck(HttpSession session, @Validated @ModelAttribute KeiyakuMasterForm keiyakuMasterForm, BindingResult result) {

		session.setAttribute("keiyakuMasterForm",keiyakuMasterForm);

//		if(result.hasErrors()) {
//			return "/keiyakuMaster/edit";
//		}

		return "/keiyakuMaster/editCheck";
	}

	@PostMapping("/keiyakuMaster/finish")
	public String finish(HttpSession session) {
		var sessionEditForm = (KeiyakuMasterForm) session.getAttribute("keiyakuMasterForm");

		var keiyakuMaster = new KeiyakuMaster();

		if(sessionEditForm.getId() == null) {
			int maxId = keiyakuMasterService.findByMaxKeiyakuId();

			keiyakuMaster.setKeiyakuid(maxId + 1);
		}else {
			keiyakuMaster.setId(sessionEditForm.getId());
			keiyakuMaster.setKeiyakuid(sessionEditForm.getKeiyakuid());
		}

		keiyakuMaster.setShohinid(sessionEditForm.getShohinid());
		keiyakuMaster.setPrice(sessionEditForm.getPrice());
		keiyakuMaster.setKinri(sessionEditForm.getKinri());
		keiyakuMaster.setReturnlimit(sessionEditForm.getReturnlimit());
		keiyakuMaster.setClientid(sessionEditForm.getClientid());
		keiyakuMaster.setKoinid(sessionEditForm.getKoinid());

		this.keiyakuMasterService.save(keiyakuMaster);

		return "/keiyakuMaster/finish";
	}

	@RequestMapping(value = "/keiyakuMaster/pagenate")
	public String pagenate(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable) {

		KeiyakuMasterListForm keiyakuMasterListForm = (KeiyakuMasterListForm)session.getAttribute("keiyakuMasterListForm");

		return this.list(model, keiyakuMasterListForm, pageable);
	}

	@RequestMapping(value = "/keiyakuMaster/list")
	public String list(Model model, @ModelAttribute("keiyakuMasterListForm") KeiyakuMasterListForm keiyakuMasterListForm, @PageableDefault(page = 0, size = 5) Pageable pageable) {

		session.setAttribute("keiyakuMasterListForm", keiyakuMasterListForm);

		if(request.getParameter("fromMenu") != null) {

			keiyakuMasterListForm.setShoninflg(99);
		}

		Page<KeiyakuMaster> list = keiyakuMasterService.findUsers(keiyakuMasterListForm, pageable);

		model.addAttribute("list", list.getContent());
        model.addAttribute("keiyakuMasterListForm",keiyakuMasterListForm);
        model.addAttribute("page",PagenationHelper.createPagenation(list));

        return "/keiyakuMaster/list";
	}

	@RequestMapping("/keiyakuMaster/delete")
	public String delete(@RequestParam(name = "id", required = false) Long id, Model model, @ModelAttribute("keiyakuMasterListForm") KeiyakuMasterListForm keiyakuMasterListForm, @PageableDefault(page = 0, size = 10) Pageable pageable) {

		this.keiyakuMasterRepository.deleteById(id);

		return this.list(model, keiyakuMasterListForm, pageable);
	}

	@RequestMapping("/keiyakuMaster/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (KeiyakuMasterForm) session.getAttribute("keiyakuMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/keiyakuMaster/edit";
		}

		model.addAttribute("keiyakuMasterForm", sessionEditForm);

		return "/keiyakuMaster/edit";
	}

	@RequestMapping("/keiyakuMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (KeiyakuMasterForm) session.getAttribute("keiyakuMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/keiyakuMaster/detail";
		}

		model.addAttribute("keiyakuMasterForm", sessionEditForm);

		return "/keiyakuMaster/detail";
	}

	@RequestMapping("/keiyakuMaster/shonin")
	public String shonin(@RequestParam(name = "id", required = false) Long id, Model model, @ModelAttribute("keiyakuMasterListForm") KeiyakuMasterListForm keiyakuMasterListForm, @PageableDefault(page = 0, size = 10) Pageable pageable) {

		var keiyakuMaster = keiyakuMasterRepository.findById(id).get();

		keiyakuMaster.setShoninflg(1);

		keiyakuMasterService.save(keiyakuMaster);

		return this.list(model, keiyakuMasterListForm, pageable);
	}

}
