package org.study.todomanager.web;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.study.todomanager.dao.ShopDao;
import org.study.todomanager.model.Member;


import org.study.todomanager.model.Schedule;


@Controller
public class ShopController {

	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	@Autowired
	ShopDao dao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		logger.info("initBinder called...");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "sche_time", 
				new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, "member_id", new MemberIdEditor());
		binder.registerCustomEditor(String.class, "sche_name", new MemberIdEditor());
		binder.registerCustomEditor(String.class, "sche_check", new MemberIdEditor());
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register() {
		logger.info("/register get called...");
		
		return "registerForm";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public String registerProc(Member member, String member_pw1, String member_pw2) {
		logger.info("/register post called: " + member);
		
		if (member_pw1.length() > 0 && member_pw1.equals(member_pw2)) {
			member.setMember_pw(member_pw1);
			logger.info("member: " + member + "(" + member_pw1 + ", " + member_pw2 +")");
			
			try {
				dao.insertMember(member);
				return "success";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fail2";
			}
			
		} else {
			logger.info("register post: pw incorrect");
			return "fail";
		}

	}
	
	@RequestMapping(value="/idcheck", method=RequestMethod.POST)
	@ResponseBody
	public String idCheck(String id) {
		logger.info("idCheck: " + id);
		
		if (dao.existMemberId(id)) {
			return "not available";
		} else {
			return "available";
		}

	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET) 
	public String list(Model model) {
		List<Member> list = null;
		try {
			list = dao.readMember();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(String id, Model model) {
		logger.info("update get: id=" + id);
		Member member = null;
		try {
			member = dao.readMember(id);
			model.addAttribute("member", member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "updateForm";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public String updateProc(Member member) {
		logger.info("update post: member=" + member);
		
		try {
			if (dao.updateMember(member)) {
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "fail2";
	}
	
	@RequestMapping(value="/saleList", method=RequestMethod.GET)
	public String saleList(Model model) {
		try {
			List<Schedule> list = dao.listSales();
			model.addAttribute("sale", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "saleList";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		logger.info("login get....");
		return "loginForm";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPost(String member_id, String member_pw, Model model) {
		logger.info("login post....");
		
		try {
			Member member = dao.readMember(member_id);
			if (member != null && member.getMember_pw().equals(member_pw)) {
				model.addAttribute("user", member.getMember_name());
			} else {
				model.addAttribute("error", "주어진 정보가 맞지 않습니다");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "loginForm";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert() {
		logger.info("/insert get called...");
		
		return "insertForm";
	}

	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insertProc(@Valid Schedule update, BindingResult result, Model model) {
		logger.info("sale update: " + update.toString());
	
		if(update.getMember_id()!=null) {
			
			try {
				dao.insertTodo(update);
				logger.info("여기까찌");
				return "redirect:/";  // 페이지로 인식이 안되고 문자열로 인식이된다.
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if (result.hasErrors()) {
				logger.info("binding errors occurred...");
				
				return "insertForm";
			}
				}
		
		return "redirect:/saleList";
	}
	

}