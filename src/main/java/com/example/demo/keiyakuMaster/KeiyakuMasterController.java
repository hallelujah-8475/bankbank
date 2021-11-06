package com.example.demo.keiyakuMaster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.accesslog.AccessLogService;
import com.example.demo.clientMaster.ClientMasterRepository;
import com.example.demo.helper.Common;
import com.example.demo.koinMaster.KoinMasterRepository;
import com.example.demo.shohinMaster.ShohinMasterRepository;
import com.example.demo.systemUser.PagenationHelper;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller
public class KeiyakuMasterController {

	@Autowired
	private KeiyakuMasterService keiyakuMasterService;

	@Autowired
	private ClientMasterRepository clientMasterRepository;

	@Autowired
	private KoinMasterRepository koinMasterRepository;
	
	@Autowired
	private ShohinMasterRepository shohinMasterRepository;

	@Autowired
	private KeiyakuMasterRepository keiyakuMasterRepository;
	
	@Autowired
	private AccessLogService accessLogService;
	
	@Autowired
	HttpServletRequest request;

    @Autowired
    HttpSession session;
    
	@Autowired
    ResourceLoader resource;
	
	@Autowired
	Common common;
	
	@Autowired
	private KeiyakuMasterValidator keiyakuMasterValidator;

	@InitBinder("keiyakuMasterForm")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(keiyakuMasterValidator);
	}

	@RequestMapping(value = "/keiyakuMaster/detail")
	private String detail(@RequestParam("id") int id, @ModelAttribute("keiyakuMasterForm") KeiyakuMasterForm keiyakuMasterForm, HttpSession session,Model model) {

		if(id != 0) {
			// 更新
			var keiyakuMaster =keiyakuMasterRepository.findById(id);

			BeanUtils.copyProperties(keiyakuMaster, keiyakuMasterForm);

			keiyakuMasterForm.setKoinname(koinMasterRepository.findById(keiyakuMaster.getKoinid()).getKoinname());
			keiyakuMasterForm.setClientname(clientMasterRepository.findById(keiyakuMaster.getClientid()).getName());
			
			if(keiyakuMaster.getShohinid() != 0) {
				
				keiyakuMasterForm.setShohinname(shohinMasterRepository.findById(keiyakuMaster.getShohinid()).getName());
			}

			model.addAttribute("image", Base64.getEncoder().encodeToString(keiyakuMaster.getFiledata()));
		}
		
		String contenttype = "";
		
		if(!org.apache.commons.lang3.StringUtils.isBlank(keiyakuMasterForm.getFilename())) {
			
			String kakuchoshi = keiyakuMasterForm.getFilename().substring(keiyakuMasterForm.getFilename().lastIndexOf("."));	
			
			if(kakuchoshi.equals(".jpg")) {
				
				contenttype = "image/jpg";
			}else if(kakuchoshi.equals(".png")) {
				
				contenttype = "image/png";
			}
		}
			
		model.addAttribute("contentype", contenttype);

		session.setAttribute("keiyakuMasterForm",keiyakuMasterForm);

		return "keiyakuMaster/detail";
	}

	@RequestMapping(value = "/keiyakuMaster/edit")
	private String edit(Model model, @RequestParam(name = "id", required = true, defaultValue = "0") int id, @ModelAttribute("keiyakuMasterForm") KeiyakuMasterForm keiyakuMasterForm, HttpSession session) {

		keiyakuMasterForm.setKeiyakukbn(1);
		
		if(id != 0) {
			
			// 更新
			var keiyakuMaster =keiyakuMasterRepository.findById(id);

			BeanUtils.copyProperties(keiyakuMaster, keiyakuMasterForm);

			keiyakuMasterForm.setKoinname(koinMasterRepository.findById(keiyakuMaster.getKoinid()).getKoinname());
			keiyakuMasterForm.setClientname(clientMasterRepository.findById(keiyakuMaster.getClientid()).getName());

			if(keiyakuMaster.getShohinid() != 0) {
				
				keiyakuMasterForm.setShohinname(shohinMasterRepository.findById(keiyakuMaster.getShohinid()).getName());
			}
				
		}

		model.addAttribute("shohinList", common.setShohinSelectTag());
		model.addAttribute("clientList", common.setClientSelectTag());
		model.addAttribute("koinList", common.setKoinSelectTag());

		session.setAttribute("keiyakuMasterForm",keiyakuMasterForm);

		return "keiyakuMaster/edit";
	}

	@RequestMapping("/keiyakuMaster/editCheck")
	public String editCheck(@RequestParam("filedata") MultipartFile file, HttpSession session, @Validated @ModelAttribute KeiyakuMasterForm keiyakuMasterForm, BindingResult result, Model model) throws IOException {

		keiyakuMasterForm.setFilename(StringUtils.cleanPath(file.getOriginalFilename()));

		keiyakuMasterForm.setFiledataString(Base64.getEncoder().encodeToString(file.getBytes()));

		String contenttype = "";
		
		if(!org.apache.commons.lang3.StringUtils.isBlank(keiyakuMasterForm.getFilename())) {
			
			String kakuchoshi = keiyakuMasterForm.getFilename().substring(keiyakuMasterForm.getFilename().lastIndexOf("."));	
			
			if(kakuchoshi.equals(".jpg")) {
				
				contenttype = "image/jpg";
			}else if(kakuchoshi.equals(".png")) {
				
				contenttype = "image/png";
			}
		}
			
		model.addAttribute("contentype", contenttype);
		model.addAttribute("image", keiyakuMasterForm.getFiledataString());

		if(keiyakuMasterForm.getShohinid() != 0) {
			keiyakuMasterForm.setShohinname(shohinMasterRepository.findById(keiyakuMasterForm.getShohinid()).getName());
		}
		if(keiyakuMasterForm.getClientid() != 0) {
			keiyakuMasterForm.setClientname(clientMasterRepository.findById(keiyakuMasterForm.getClientid()).getName());
		}
		if(keiyakuMasterForm.getKoinid() != 0) {
			keiyakuMasterForm.setKoinname(koinMasterRepository.findById(keiyakuMasterForm.getKoinid()).getKoinname());
		}
		
		session.setAttribute("keiyakuMasterForm",keiyakuMasterForm);
		
		model.addAttribute("shohinList", common.setShohinSelectTag());
		model.addAttribute("clientList", common.setClientSelectTag());
		model.addAttribute("koinList", common.setKoinSelectTag());

//		if(result.hasErrors()) {
//			return "keiyakuMaster/edit";
//		}

		return "keiyakuMaster/editCheck";
	}

	@PostMapping("/keiyakuMaster/finish")
	public String finish(HttpSession session, @ModelAttribute("keiyakuMasterForm") KeiyakuMasterForm keiyakuMasterForm) {

		var keiyakuMaster = new KeiyakuMaster();

		keiyakuMasterForm.setFiledata(Base64.getDecoder().decode(keiyakuMasterForm.getFiledataString()));

		BeanUtils.copyProperties(keiyakuMasterForm, keiyakuMaster);

		keiyakuMaster.setShitenid(koinMasterRepository.findById(keiyakuMaster.getKoinid()).getShitenid());
		
		this.keiyakuMasterService.save(keiyakuMaster);

		accessLogService.save(6, "更新", "成功");
		
		return "keiyakuMaster/finish";
	}

	@RequestMapping(value = "/keiyakuMaster/pagenate")
	public String pagenate(Model model, Pageable pageable) {

		KeiyakuMasterListForm keiyakuMasterListForm = (KeiyakuMasterListForm)session.getAttribute("keiyakuMasterListForm");

		return this.list(model, keiyakuMasterListForm, pageable);
	}

	@RequestMapping(value = "/keiyakuMaster/list")
	public String list(Model model, @ModelAttribute("keiyakuMasterListForm") KeiyakuMasterListForm keiyakuMasterListForm, @PageableDefault(page = 0, size = 10) Pageable pageable) {
		
		session.setAttribute("keiyakuMasterListForm", keiyakuMasterListForm);

		if(request.getParameter("fromMenu") != null) {

			keiyakuMasterListForm.setShoninflg(99);
		}

		Page<KeiyakuMaster> list = keiyakuMasterService.findUsers(keiyakuMasterListForm, pageable);

		model.addAttribute("list", list.getContent());
        model.addAttribute("keiyakuMasterListForm",keiyakuMasterListForm);
        model.addAttribute("page",PagenationHelper.createPagenation(list));

        return "keiyakuMaster/list";
	}

	@RequestMapping("/keiyakuMaster/delete")
	public String delete(@RequestParam("id") int id, Model model, @ModelAttribute("keiyakuMasterListForm") KeiyakuMasterListForm keiyakuMasterListForm, Pageable pageable) {

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

		return "keiyakuMaster/edit";
	}

	@RequestMapping("/keiyakuMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (KeiyakuMasterForm) session.getAttribute("keiyakuMasterForm");

		if(sessionEditForm == null) {

			return "redirect:keiyakuMaster/detail";
		}

		model.addAttribute("keiyakuMasterForm", sessionEditForm);

		return "keiyakuMaster/detail";
	}

	@RequestMapping("/keiyakuMaster/shonin")
	public String shonin(@RequestParam("id") int id, Model model, @ModelAttribute("keiyakuMasterListForm") KeiyakuMasterListForm keiyakuMasterListForm, Pageable pageable) {

		var keiyakuMaster = keiyakuMasterRepository.findById(id);

		keiyakuMaster.setShoninflg(1);

		keiyakuMasterService.save(keiyakuMaster);

		return this.list(model, keiyakuMasterListForm, pageable);
	}
	
	@RequestMapping(value = "/keiyakuMaster/report", method = RequestMethod.GET)
	public String getReport(HttpServletResponse response, HttpSession session) throws FileNotFoundException, IOException, JRException {

		var keiyakuMasterForm = (KeiyakuMasterForm) session.getAttribute("keiyakuMasterForm");

		var keiyakuMaster = keiyakuMasterRepository.findById(keiyakuMasterForm.getId());
		
		// パラメータ
        HashMap<String, Object> params = new HashMap<String, Object>();
        
        params.put("printdate", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
        params.put("moshikominin", keiyakuMaster.getClientid() != 0 ? clientMasterRepository.findById(keiyakuMaster.getClientid()).getName() : "");
        
        String naiyoLabel = keiyakuMaster.getKeiyakukbnlabel();
        
        if(keiyakuMaster.getKeiyakukbn() == 2) {
        	
        	naiyoLabel += "　" + shohinMasterRepository.findById(keiyakuMasterForm.getShohinid()).getName();
        }
        params.put("naiyo", naiyoLabel);        
        params.put("kingaku", keiyakuMaster.getPrice() != 0 ? "¥" + keiyakuMaster.getPrice() : "");        
        params.put("kinri", keiyakuMaster.getKinri() != 0 ? keiyakuMaster.getKinri() + "％" : "");        
        params.put("hensaikigen", !org.apache.commons.lang3.StringUtils.isBlank(keiyakuMaster.getReturnlimit()) ? keiyakuMaster.getReturnlimit().replaceFirst("-", "年").replaceFirst("-", "月") + "日" : "");        
        params.put("shikinshito", !org.apache.commons.lang3.StringUtils.isBlank(keiyakuMaster.getShikinshitotext()) ? keiyakuMaster.getShikinshitotext() : "");        
        params.put("kokatotenbo", !org.apache.commons.lang3.StringUtils.isBlank(keiyakuMaster.getKokatext()) ? keiyakuMaster.getKokatext() : "");        
        params.put("ringisuisenjiyu", !org.apache.commons.lang3.StringUtils.isBlank(keiyakuMaster.getRingitext()) ? keiyakuMaster.getRingitext() : "");        
        params.put("tanto", keiyakuMasterForm.getKoinid() != 0 ? koinMasterRepository.findById(keiyakuMasterForm.getKoinid()).getKoinname() : "");        
       
        // コンパイル
        JasperReport jasperReport = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/bankbank/src/main/resources/report/template.jrxml");

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