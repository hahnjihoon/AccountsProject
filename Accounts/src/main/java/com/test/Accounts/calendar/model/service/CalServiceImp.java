package com.test.Accounts.calendar.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Accounts.calendar.model.dao.ICalDao;
import com.test.Accounts.calendar.model.vo.CalDto;

@Service
public class CalServiceImp implements ICalService{
	
	@Autowired
	private ICalDao calDao;
	
	@Override
	public boolean insertCal(CalDto dto) {
		return calDao.insertCal(dto);
	}

	@Override
	public List<CalDto> calBoardList(String id, String yyyyMMdd) {
		return calDao.calBoardList(id, yyyyMMdd);
	}

	@Override
	public CalDto calDetail(int seq) {
		return calDao.calDetail(seq);
	}

	@Override
	public boolean calUpdate(CalDto dto) {
		return calDao.calUpdate(dto);
	}

	@Override
	public boolean calMuldel(String[] seq) {
		return calDao.calMuldel(seq);
	}

	@Override
	public int calCount(String id, String yyyyMMdd) {
		return calDao.calCount(id, yyyyMMdd);
	}

}
