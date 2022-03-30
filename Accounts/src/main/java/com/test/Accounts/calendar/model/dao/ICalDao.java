package com.test.Accounts.calendar.model.dao;

import java.util.List;

import com.test.Accounts.calendar.model.vo.CalDto;

public interface ICalDao {
	//일정추가하기
    public boolean insertCal(CalDto dto);
    //일정목록조회
    public List<CalDto> calBoardList(String id, String yyyyMMdd);
    
    //일정상세보기
    public CalDto calDetail(int seq);
    
    //일정수정
    public boolean calUpdate(CalDto dto);
    
    
    //일정삭제
    public boolean calMuldel(String[] seq);
    
    //일정개수조회
    public int calCount(String id, String yyyyMMdd);
    
    
    //달력에 일정표시
}
