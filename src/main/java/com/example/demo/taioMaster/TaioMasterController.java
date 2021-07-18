package com.example.demo.taioMaster;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.clientMaster.ClientMaster;
import com.example.demo.clientMaster.ClientMasterRepository;
import com.example.demo.clientMaster.ClientMasterService;
import com.example.demo.koinMaster.KoinMaster;
import com.example.demo.koinMaster.KoinMasterRepository;
import com.example.demo.koinMaster.KoinMasterService;
import com.example.demo.shitenMaster.ShitenMaster;
import com.example.demo.shitenMaster.ShitenMasterService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller
public class TaioMasterController {

	@Autowired
	private TaioMasterService taioMasterService;

	@Autowired
	private TaioMasterRepository taioMasterRepository;

	@Autowired
	private ShitenMasterService shitenMasterService;

	@Autowired
	private ClientMasterService clientMasterService;

	@Autowired
	private KoinMasterService koinMasterService;

	@Autowired
	private KoinMasterRepository koinMasterRepository;

	@Autowired
	private ClientMasterRepository clientMasterRepository;

	@Autowired
    ResourceLoader resource;

	private void setShitenSelectTag(Model model) {

        var list = shitenMasterService.findAll();

        Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

        for(ShitenMaster entity : list) {

        	optionMap.put(entity.getShitenid(), entity.getShitenname());
        }

        model.addAttribute("shitenList", optionMap);
    }

	private void setClientSelectTag(Model model) {

		var list = clientMasterService.findAll();

		Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

		for(ClientMaster entity : list) {

			optionMap.put(entity.getId(), entity.getName());
		}

		model.addAttribute("clientList", optionMap);
	}

	private void setKoinSelectTag(Model model) {

		var list = koinMasterService.findAll();

		Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

		for(KoinMaster entity : list) {

			optionMap.put(entity.getId(), entity.getKoinname());
		}

		model.addAttribute("koinList", optionMap);
	}

	@RequestMapping(value = "/taioMaster/detail")
	private String detail(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("taioMasterForm") TaioMasterForm taioMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var taioMaster = taioMasterRepository.findById(id).get();

			taioMasterForm.setId(id);
			taioMasterForm.setTaioid(taioMaster.getTaioid());
			taioMasterForm.setTaiostartdate(taioMaster.getTaiostartdate());
			taioMasterForm.setTaioenddate(taioMaster.getTaioenddate());
			taioMasterForm.setClientid(taioMaster.getClientid());
			taioMasterForm.setShitenid(taioMaster.getShitenid());
			taioMasterForm.setKoinid(taioMaster.getKoinid());
			taioMasterForm.setShitenname(shitenMasterService.findByShitenid(taioMaster.getShitenid()).getShitenname());
			taioMasterForm.setKoinname(koinMasterRepository.findById(taioMaster.getKoinid()).getKoinname());
			taioMasterForm.setClientname(clientMasterRepository.findById(taioMaster.getClientid()).getName());
		}

		session.setAttribute("taioMasterForm", taioMasterForm);

		return "/taioMaster/detail";
	}

	@RequestMapping(value = "/taioMaster/edit")
	private String edit(Model model, @RequestParam(name = "id", required = false) Long id, @ModelAttribute("taioMasterForm") TaioMasterForm taioMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var taioMaster = taioMasterRepository.findById(id).get();

			taioMasterForm.setId(id);
			taioMasterForm.setTaioid(taioMaster.getTaioid());
			taioMasterForm.setTaiostartdate(taioMaster.getTaiostartdate());
			taioMasterForm.setTaioenddate(taioMaster.getTaioenddate());
			taioMasterForm.setClientid(taioMaster.getClientid());
			taioMasterForm.setShitenid(taioMaster.getShitenid());
			taioMasterForm.setKoinid(taioMaster.getKoinid());
			taioMasterForm.setShitenname(shitenMasterService.findByShitenid(taioMaster.getShitenid()).getShitenname());
			taioMasterForm.setKoinname(koinMasterRepository.findById(taioMaster.getKoinid()).getKoinname());
			taioMasterForm.setClientname(clientMasterRepository.findById(taioMaster.getClientid()).getName());
		}

		this.setShitenSelectTag(model);
		this.setClientSelectTag(model);
		this.setKoinSelectTag(model);

		session.setAttribute("taioMasterForm", taioMasterForm);

		return "/taioMaster/edit";
	}

	@RequestMapping("/taioMaster/editCheck")
	public String editCheck(@ModelAttribute("taioMasterForm") TaioMasterForm taioMasterForm, HttpSession session) {

		session.setAttribute("taioMasterForm", taioMasterForm);

		return "/taioMaster/editCheck";
	}

	@PostMapping("/taioMaster/finish")
	public String finish(HttpSession session, @ModelAttribute("taioMasterForm") TaioMasterForm taioMasterForm) {
		TaioMasterForm sessionEditForm = (TaioMasterForm) session.getAttribute("taioMasterForm");

		var taioMaster = new TaioMaster();

		if(sessionEditForm.getId() == null) {
			int maxId = taioMasterService.findByMaxTaioId();

			taioMaster.setTaioid(maxId + 1);
		}else {
			taioMaster.setId(sessionEditForm.getId());
			taioMaster.setTaioid(sessionEditForm.getTaioid());
		}

		taioMaster.setTaiostartdate(sessionEditForm.getTaiostartdate());
		taioMaster.setTaioenddate(sessionEditForm.getTaioenddate());
		taioMaster.setClientid(sessionEditForm.getClientid());
		taioMaster.setShitenid(sessionEditForm.getShitenid());
		taioMaster.setKoinid(sessionEditForm.getKoinid());


		this.taioMasterService.save(taioMaster);

		return "/taioMaster/finish";
	}

	@RequestMapping(value = "/taioMaster/list")
	public String list(Model model) {
        var list = taioMasterService.findAll();
        model.addAttribute("list", list);
        return "/taioMaster/list";
	}

	@RequestMapping("/taioMaster/delete")
	public String delete(Model model, @ModelAttribute("taioMasterForm") TaioMasterForm taioMasterForm, HttpSession session) {

		var sessionEditForm = (TaioMasterForm) session.getAttribute("taioMasterForm");

		this.taioMasterRepository.deleteById(sessionEditForm.getId());

		return this.list(model);
	}

	@RequestMapping("/taioMaster/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (TaioMasterForm) session.getAttribute("taioMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/taioMaster/edit";
		}

		model.addAttribute("taioMasterForm", sessionEditForm);

		return "/taioMaster/edit";
	}

	@RequestMapping("/taioMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (TaioMasterForm) session.getAttribute("taioMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/taioMaster/detail";
		}

		model.addAttribute("taioMasterForm", sessionEditForm);

		return "/taioMaster/detail";
	}

	@RequestMapping(value = "/taioMaster/report", method = RequestMethod.GET)
	public String getReport(HttpServletResponse response, HttpSession session) throws FileNotFoundException, IOException, JRException {

		TaioMasterForm sessionEditForm = (TaioMasterForm) session.getAttribute("taioMasterForm");
		var taioMaster = taioMasterRepository.findById(sessionEditForm.getId()).get();

		// パラメータ
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("Client_name", Integer.toString(taioMaster.getKoinid()));

        // ファイル読み込み
        InputStream input = new FileInputStream(resource.getResource("classpath:report/Blank_A4.jrxml").getFile());

        // コンパイル
        JasperReport jasperReport = JasperCompileManager.compileReport(input);

        // 生成
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

        // byteで出力
        byte[] byteData = JasperExportManager.exportReportToPdf(jasperPrint);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + "sample.pdf");
        response.setContentLength(byteData.length);

        OutputStream os = null;

        os = response.getOutputStream();
        os.write(byteData);
        os.flush();
        os.close();

        return null;
	}
}
