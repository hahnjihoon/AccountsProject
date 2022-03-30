<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat" %>

<%@page import="com.test.Accounts.calendar.model.vo.CalDto" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	function allSel(val){
		
		var chks=document.getElementsByName("seq");
		for(var i=0;i<chks.length;i++){
			chks[i].checked=val;
		}
	}
	
	$(function(){
		
		$("form").submit(function(){
			var bool=true;
			var count=$(this).find("input[name=seq]:checked").length;
			if(count==0){
				alert("최소하나이상 체크하시오");
				bool=false;
				
			}else if(confirm("정말 삭제하시겠습니까?")){
				bool=true;
			}else{
				bool=false;
			}
			return bool;
			
		});
		
		var chks=document.getElementsByName("seq");
		for(var i=0;i<chks.length;i++){
			chks[i].onclick=function(){
				var checkedObjs=document.querySelectorAll("input[name=seq]:checked");
				if(checkedObjs.length==chks.length){
					document.getElementsByName("all")[0].checked=true;
				}else{
					document.getElementsByName("all")[0].checked=false;
				}				
			}
		}		
	})
</script>

</head>
<%
	List<CalDto> list=(List<CalDto>)request.getAttribute("list");
%>
<body>
<h1>일정목록보기</h1>
<form action="calMuldel.do" method="post">
<input type="hidden" name="year" value="${ param.year }">
<input type="hidden" name="month" value="${ param.month }">
<input type="hidden" name="date" value="${ param.date }">
<table border="1">
	<col width="50px">
	<col width="50px">
	<col width="300px">
	<col width="250px">
	<col width="250px">
	<tr>
		<th><input type="checkbox" name="all" onclick="allSel(this.checked)" /></th>
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
					<td><a href="calDetail.do?seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a></td>
					<td><%=toDates(dto.getMdate())%></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="<%=dto.getRegdate()%>"/></td>
				</tr>
				<%
			}
		}
	%>
	<tr>
		<td colspan="5">
		    <input type="submit" value="삭제" />
			<a href="calendar.do">달력보기</a>
		</td>
	</tr>
	
</table>
</form>
<a href="main.do">
<button>메인으로돌아가기</button>
</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button onclick="javascript:history.go(-1);">뒤로</button>

<%!
	public String toDates(String mdate){
	
	
		String m=mdate.substring(0,4)+"-"
				+mdate.substring(4,6)+"-"
				+mdate.substring(6,8)+" "
				+mdate.substring(8,10)+":"
				+mdate.substring(10)+":00";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		Timestamp tm = Timestamp.valueOf(m);
	
		return sdf.format(tm);
	}
 %>
</body>
</html>