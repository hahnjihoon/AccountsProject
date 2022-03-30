<%@page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#calendar{
		border-collapse: collapse; /* 테두리 실선*/
		border: 1px solid gray;
	}
	#calendar td{
		width:80px;
		height:80px;
		text-align:left;
		vertical-align:top;
		
	}
	a{
		text-decoration:none;
	}
	img{
		width:15px;
		height:15px;
	}
</style>
</head>
<%
	String paramYear=request.getParameter("year");
	String paramMonth=request.getParameter("month");

	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH)+1;
	
	if(paramYear != null){
		year=Integer.parseInt(paramYear);
		
	}
	if(paramMonth != null){
		month=Integer.parseInt(paramMonth);
	}
	if(month>12){
		month=1;
		year++;
	}
	if(month<1){
		month=12;
		year--;
	}
	
	cal.set(year, month-1, 1);
	int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
	
	int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
%>
<body>
<h1 align="center">가계부 달력보기</h1>
<table border="1" id="calendar" align="center">
	<caption>
		
		<a href="calendar.do?year=<%=year-1%>&month=<%=month%>">◁</a>
		<a href="calendar.do?year=<%=year%>&month=<%=month-1%>">◀</a>
		<%=year%>년<%=month%>월
		<a href="calendar.do?year=<%=year%>&month=<%=month+1%>">▶</a>
		<a href="calendar.do?year=<%=year+1%>&month=<%=month%>">▷</a>
		
		
	</caption>
	<tr>
		<th>일</th>
		<th>월</th>
		<th>화</th>
		<th>수</th>
		<th>목</th>
		<th>금</th>
		<th>토</th>
	</tr>
	<tr>
		<%
			for(int i=0; i<dayOfWeek-1;i++){
				out.print("<td>&nbsp;</td>");
			}
			for(int i=1; i<=lastDay; i++){
				%>
				<td>
					<a style="color:<%=fontColor(dayOfWeek, i) %>;"  href="calBoardList.do?year=<%=year%>&month=<%=month%>&date=<%=i%>"><%=i%></a>
					
					<a href="insertCalForm.do?year=<%=year%>&month=<%=month%>&date=<%=i%>">
						<img src="${ pageContext.request.contextPath}/resources/img/pen.png" alt="내용추가" />
					</a>
				</td>				
				<%
				if((dayOfWeek-1+i)%7==0){
					out.print("</tr><tr>");
				}
			}
			
			//공백출력
			int countNbsp=(7-(dayOfWeek-1+lastDay)%7)%7;
			for(int i=0;i<countNbsp;i++){
				out.print("<td>&nbsp;</td>");
			}
		%>
	</tr>
</table>

<%!
	public String fontColor(int dayOfWeek, int i){
		String color="";
		if((dayOfWeek-1+i)%7==0){
			color="blue";
		}else if((dayOfWeek-1+i)%7==1){
			color="red";
		}else{
			color="black";
		}
		return color;
	}
%>

<a href="main.do">
<button>메인으로돌아가기</button>
</a>
</body>
</html>









