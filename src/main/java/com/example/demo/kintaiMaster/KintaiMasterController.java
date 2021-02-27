package com.example.demo.kintaiMaster;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.example.demo.clientMaster.ClientMasterService;
import com.example.demo.koinMaster.KoinMaster;
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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class KintaiMasterController {

	@Autowired
	private KintaiMasterService kintaiMasterService;

	@Autowired
	private KimtaiMasterRepository kimtaiMasterRepository;

	@Autowired
	private ShitenMasterService shitenMasterService;

	@Autowired
	private ClientMasterService clientMasterService;

	@Autowired
	private KoinMasterService koinMasterService;

	@Autowired
    ResourceLoader resource;

	private void setShitenSelectTag(Model model) {

        var list = shitenMasterService.findAll();

        Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

        for(ShitenMaster entity : list) {

        	optionMap.put(entity.getShitenid(), entity.getName());
        }

        model.addAttribute("shitenList", optionMap);
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

			optionMap.put(entity.getKoinid(), entity.getName());
		}

		model.addAttribute("koinList", optionMap);
	}

	@RequestMapping(value = "/kintaiMaster/detail")
	private String detail(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("kintaiMasterForm") KintaiMasterForm kintaiMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var kintaiMaster = kimtaiMasterRepository.findById(id).get();

			kintaiMasterForm.setId(id);
			kintaiMasterForm.setTaioid(kintaiMaster.getTaioid());
			kintaiMasterForm.setTaiostartdate(kintaiMaster.getTaiostartdate());
			kintaiMasterForm.setTaioenddate(kintaiMaster.getTaioenddate());
			kintaiMasterForm.setClientid(kintaiMaster.getClientid());
			kintaiMasterForm.setShitenid(kintaiMaster.getShitenid());
			kintaiMasterForm.setKoinid(kintaiMaster.getKoinid());



		}

		session.setAttribute("kintaiMasterForm", kintaiMasterForm);

		return "/kintaiMaster/detail";
	}

	@RequestMapping(value = "/kintaiMaster/edit")
	private String edit(Model model, @RequestParam(name = "id", required = false) Long id, @ModelAttribute("kintaiMasterForm") KintaiMasterForm kintaiMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var kintaiMaster = kimtaiMasterRepository.findById(id).get();

			kintaiMasterForm.setId(id);
			kintaiMasterForm.setTaioid(kintaiMaster.getTaioid());
			kintaiMasterForm.setTaiostartdate(kintaiMaster.getTaiostartdate());
			kintaiMasterForm.setTaioenddate(kintaiMaster.getTaioenddate());
			kintaiMasterForm.setClientid(kintaiMaster.getClientid());
			kintaiMasterForm.setShitenid(kintaiMaster.getShitenid());
			kintaiMasterForm.setKoinid(kintaiMaster.getKoinid());

		}

		this.setShitenSelectTag(model);
		this.setClientSelectTag(model);
		this.setKoinSelectTag(model);

		session.setAttribute("kintaiMasterForm", kintaiMasterForm);

		return "/kintaiMaster/edit";
	}

	@RequestMapping("/kintaiMaster/editCheck")
	public String editCheck(@ModelAttribute("kintaiMasterForm") KintaiMasterForm kintaiMasterForm, HttpSession session) {

		session.setAttribute("kintaiMasterForm", kintaiMasterForm);

		return "/kintaiMaster/editCheck";
	}

	@PostMapping("/kintaiMaster/finish")
	public String finish(HttpSession session, @ModelAttribute("kintaiMasterForm") KintaiMasterForm kintaiMasterForm) {
		KintaiMasterForm sessionEditForm = (KintaiMasterForm) session.getAttribute("kintaiMasterForm");

		var kintaiMaster = new KintaiMaster();

		if(sessionEditForm.getId() == null) {
			int maxId = kintaiMasterService.findByMaxTaioId();

			kintaiMaster.setTaioid(maxId + 1);
		}else {
			kintaiMaster.setId(sessionEditForm.getId());
			kintaiMaster.setTaioid(sessionEditForm.getTaioid());
		}

		kintaiMaster.setTaiostartdate(sessionEditForm.getTaiostartdate());
		kintaiMaster.setTaioenddate(sessionEditForm.getTaioenddate());
		kintaiMaster.setClientid(sessionEditForm.getClientid());
		kintaiMaster.setShitenid(sessionEditForm.getShitenid());
		kintaiMaster.setKoinid(sessionEditForm.getKoinid());


		this.kintaiMasterService.save(kintaiMaster);

		return "/kintaiMaster/finish";
	}

	@RequestMapping(value = "/kintaiMaster/list")
	public String list(Model model) {
        var list = kintaiMasterService.findAll();
        model.addAttribute("list", list);
        return "/kintaiMaster/list";
	}

	@RequestMapping("/kintaiMaster/delete")
	public String delete(Model model, @ModelAttribute("kintaiMasterForm") KintaiMasterForm kintaiMasterForm, HttpSession session) {

		var sessionEditForm = (KintaiMasterForm) session.getAttribute("kintaiMasterForm");

		this.kimtaiMasterRepository.deleteById(sessionEditForm.getId());

		return this.list(model);
	}

	@RequestMapping("/kintaiMaster/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (KintaiMasterForm) session.getAttribute("kintaiMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/kintaiMaster/edit";
		}

		model.addAttribute("kintaiMasterForm", sessionEditForm);

		return "/kintaiMaster/edit";
	}

	@RequestMapping("/kintaiMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (KintaiMasterForm) session.getAttribute("kintaiMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/kintaiMaster/detail";
		}

		model.addAttribute("kintaiMasterForm", sessionEditForm);

		return "/kintaiMaster/detail";
	}

	@RequestMapping(value = "/kintaiMaster/report", method = RequestMethod.GET)
	public String getReport(HttpServletResponse response) {

		/**▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼　データ作成部　▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼**/
        //ヘッダーデータ作成
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("Client_name", "山本証券");

        //フィールドデータ作成
//        SampleProductDao dao = new SampleProductDao();
//        List<SampleProductModel> fields = dao.findByAll();
        List<String> fields = new ArrayList<String>();
        fields.add("テスト");

        /**▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲　データ作成部　▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲**/
        /**▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼　帳票出力部　▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼**/
        //データを検索し帳票を出力
        byte[] output  = OrderReporting2(params, fields);
        /**▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲　データ作成部　▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲**/

        /**▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼　データ作成データダウンロード部 ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼**/
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + "sample.pdf");
        response.setContentLength(output.length);

        OutputStream os = null;
        try {
            os = response.getOutputStream();
            os.write(output);
            os.flush();

            os.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        /**▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲　データ作成部　▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲**/


        return null;
	}

	/**
     * ジャスパーレポートコンパイル。バイナリファイルを返却する。
     * @param data
     * @param response
     * @return
     */
	private byte[] OrderReporting2(HashMap<String, Object> param, List<String> data) {
        InputStream input;
        try {
            //帳票ファイルを取得
            input = new FileInputStream(resource.getResource("classpath:report/Blank_A4.jrxml").getFile());
            //リストをフィールドのデータソースに
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
            //帳票をコンパイル
            JasperReport jasperReport = JasperCompileManager.compileReport(input);

            JasperPrint jasperPrint;
            //パラメーターとフィールドデータを注入
            jasperPrint = JasperFillManager.fillReport(jasperReport, param, new JREmptyDataSource());
            //帳票をByte形式で出力
            return  JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (FileNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (JRException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
