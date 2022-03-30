package com.test.Accounts.calendar.controller;


import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.Accounts.calendar.model.service.ICalService;
import com.test.Accounts.calendar.model.vo.CalDto;
import com.test.Accounts.common.Util;





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
	
	
	@RequestMapping(value="/insertCalBoard.do", method=RequestMethod.POST)
	public String insertCalBoard(CalDto dto, Locale locale, Model model) {
		logger.info("일정추가하기{}.", locale);
		
		String mdate=dto.getYear()
				+Util.isTwo(dto.getMonth())
				+Util.isTwo(dto.getDate())
				+Util.isTwo(dto.getHour())
				+Util.isTwo(dto.getMin());
		
		boolean isS=calService.insertCal(new CalDto(0, dto.getId(), dto.getTitle(), dto.getContent(), mdate, null));
		
		if(isS) {
			return "redirect:calendar.do?year="+dto.getYear()+"&month="+dto.getMonth();
		}else {
			model.addAttribute("message", "일정등록실패");
			return "common/error";
		}
	}
		
	
    
	@RequestMapping(value = "calBoardList.do", method=RequestMethod.GET)
	public String calBoardList(HttpServletRequest request, Locale locale, Model model, /*String year, String month, String date*/
			@RequestParam Map<String,String> ymd) {
		logger.info("일정목록보기{}.", locale);
		
//		String yyyyMMdd=year+(month.length()<2?"0"+month:month)+(date.length()<2?"0"+date:date);
		String yyyyMMdd = ymd.get("year")
				+(ymd.get("month").length()<2?"0"+ymd.get("month"):ymd.get("month"))
				+(ymd.get("date").length()<2?"0"+ymd.get("date"):ymd.get("date"));
//		String yyyyMMdd=ymd.get("year")
//				+Util.isTwo(ymd.get("month"))
//				+Util.isTwo(ymd.get("Date"));
		
		
//		HttpSession session=request.getSession();
//		String id = (String)session.getAttribute("id");
		String id="K001";
		
		List<CalDto> list = calService.calBoardList(id, yyyyMMdd);
		model.addAttribute("list", list);
		
		return "calender/calBoardList";
		
	}
	
	@RequestMapping(value = "/calMuldel.do", method=RequestMethod.POST)
	public String calMuldel(String[] seq, CalDto dto, Locale locale, Model model) {
		logger.info("일정삭제하기{}.", locale);
		
		String source="year="+dto.getYear()+"&month="+dto.getMonth()
					+"&date="+dto.getDate();
		
		boolean isS=calService.calMuldel(seq);
		if(isS) {
			return "redirect:calBoardList.do?"+source;
		}else {
			model.addAttribute("message","일정삭제실패");
			return "common/error";
		}		
	}
	
	
	@RequestMapping(value = "/calDetail.do", method=RequestMethod.GET)
	public String calDetail(int seq, Locale locale, Model model) {
		logger.info("상세내용보기{}.", locale);
		CalDto dto = calService.calDetail(seq);
		model.addAttribute("dto", dto);
		return "calender/calDetail";
		
	}
	
	
	@RequestMapping(value = "/updateForm.do", method=RequestMethod.GET)
	public String updateForm(int seq, Locale locale, Model model) {
		logger.info("일정수정폼이동{}.", locale);
		CalDto dto=calService.calDetail(seq);
		model.addAttribute("dto",dto);		
		return "calender/calUpdateForm";
		
	}
	
		
    
}




