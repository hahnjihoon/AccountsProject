package com.test.Accounts.calendar.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.Accounts.calendar.model.service.ICalService;
import com.test.Accounts.calendar.model.vo.CalDto;





@Controller
public class CalendarController {
	
	private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);
	
	@Autowired
	private ICalService calService;
    
	@RequestMapping(value = "calendar.do", method=RequestMethod.GET)
	public String calendar(Locale locale, Model model, String year, String month) {
		logger.info("달력보기{}.", locale);
		
		return "calender/calendar";
		
	}
	
	@RequestMapping(value = "/insertCalForm.do", method=RequestMethod.GET)
	public String insertCalForm(Locale locale, Model model) {
		logger.info("일정추가폼이동{}.", locale);
		
		return "calender/insertCalForm";
		
	}
		
	
    
	@RequestMapping(value = "calBoardList.do", method=RequestMethod.GET)
	public String calBoardList(HttpServletRequest request, Locale locale, Model model, String year, String month, String date) {
		logger.info("일정목록보기{}.", locale);
		
		String yyyyMMdd=year+(month.length()<2?"0"+month:month)+(date.length()<2?"0"+date:date);
		
		HttpSession session=request.getSession();
		String id = (String)session.getAttribute("id");
//		String id="hk";
		
		List<CalDto> list = calService.calBoardList(id, yyyyMMdd);
		model.addAttribute("list", list);
		
		return "calender/calBoardList";
		
	}
	
	
		
		
    
}




