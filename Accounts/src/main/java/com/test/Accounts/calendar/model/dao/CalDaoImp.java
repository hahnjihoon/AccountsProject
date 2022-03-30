package com.test.Accounts.calendar.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.Accounts.calendar.model.vo.CalDto;
 
@Repository
public class CalDaoImp implements ICalDao {
	
	private String namespace="calMapper.";
	
    @Autowired
    SqlSessionTemplate sqlSession; 
    
    //일정추가하기
    public boolean insertCal(CalDto dto) {
    	int count=sqlSession.insert(namespace+"insertCal", dto);
    	return count>0?true:false;
    }
    
    //일정목록조회
    public List<CalDto> calBoardList(String id, String yyyyMMdd){
    	
    	Map<String, String> map = new HashMap<>();
    	map.put("id", id);
    	map.put("yyyyMMdd", yyyyMMdd);
    	List<CalDto>list=sqlSession.selectList(namespace+"calBoardList", map);
    	
    	return list;
    }
    
    //일정상세보기
    public CalDto calDetail(int seq) {
    	CalDto dto=sqlSession.selectOne(namespace+"calDetail", seq);
    	return dto;
    }
    
    //일정수정
    public boolean calUpdate(CalDto dto) {
    	int count=sqlSession.update(namespace+"calUpdate", dto);
    	return count>0?true:false;
    }
    
    
    //일정삭제
    public boolean calMuldel(String[] seq) {
    	Map<String, String[]> map = new HashMap<>();
    	map.put("seqs", seq);
    	int count=sqlSession.delete(namespace+"calMuldel", map);
    	return count>0?true:false;
    }
    
    //일정개수조회
    public int calCount(String id, String yyyyMMdd) {
    	Map<String, String>map = new HashMap<>();
    	map.put("id", id);
    	map.put("yyyyMMdd", yyyyMMdd);
    	int count=sqlSession.selectOne(namespace+"calCount", map);
    	return count;
    }
    
    
    //달력에 일정표시
    
    
    
    
    
}