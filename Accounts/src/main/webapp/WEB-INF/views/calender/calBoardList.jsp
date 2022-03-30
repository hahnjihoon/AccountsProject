<%@page import="com.test.Accounts.calendar.model.vo.CalDto" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	List<CalDto> list=(List<CalDto>)request.getAttribute("list");
%>
<body>
<h1>일정목록보기</h1>
<table border="1">
	<col width="50px">
	<col width="50px">
	<col width="300px">
	<col width="25px">
	<col width="250px">
	<tr>
		<th><input type="checkbox" name="all" onclick="allChk(this.checked)" /></th>
		<th>번호</th>
		<th>제목</th>
		<th>일정</th>
		<th>작성일</th>
	</tr>
	<%
		if(list==null||list.size()==0){
			out.print("<tr><td colspan='5'> 작성된 일정이 없습니다 </td></tr>");
			
		}else{
			for(CalDto dto:list){
				%>
				<tr>
					<td><input type="checkbox" name="seq" value="<%=dto.getSeq()%>" /></td>
					<td><%=dto.getSeq()%></td>
					<td><%=dto.getTitle()%></td>
					<td><%=dto.getMdate()%></td>
					<td><%=dto.getRegdate()%></td>
				</tr>
				<%
			}
		}
	%>
	<tr>
		<td colspan="5">
			<a href="calendar.do">달력보기</a>
		</td>
	</tr>
	
</table>

</body>
</html>