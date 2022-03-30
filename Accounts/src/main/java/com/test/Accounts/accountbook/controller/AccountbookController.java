package com.test.Accounts.accountbook.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountbookController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountbookController.class);
	
	@RequestMapping("accountbook.do")
	public String moveAccountPage() {
		
		return "accountbook/accountbook";
	}
	
	@RequestMapping("calendarPage.do")
	public String moveCalenderPage() {
		
		return "calender/calendar";
	}
	
	
	
}





