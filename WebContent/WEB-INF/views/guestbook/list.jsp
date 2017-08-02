<%@  page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.javaex.dao.GuestBookDao"%>
<%@ page import="com.javaex.vo.GuestBookVo"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
	List<GuestBookVo> list = (List<GuestBookVo>) request.getAttribute("list");
	System.out.println(list.toString());
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">

					<form action="/mysite/gb" method="post">
						<input type=hidden name ="a" value ="add">
						<table>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name" /></td>
								<td>비밀번호</td>
								<td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>
					</form>
					<ul>
						<li>
						<c:forEach var="vo" items="${list}" varStatus="status">
							<table>
								<tr>
									<td>[${vo.num }]
									</td>
									<td>${vo.name }</td>
									<td>${vo.date }</td>
									<td><a href="/mysite/gb?a=deleteform&no=${vo.num }">삭제</a>
									</td>
								</tr>
								<tr>
									<td colspan=4>${vo.messege }</td>
								</tr>
							</table>
						</c:forEach>
							<%--
							<%
								for (GuestBookVo vo : list) {
							%>
							<table>
								<tr>
									<td>[<%=vo.getNum()%>]
									</td>
									<td><%=vo.getName()%></td>
									<td><%=vo.getDate()%></td>
									<td><a href="/mysite/gb?a=deleteform&no=<%=vo.getNum()%>">삭제</a>
									</td>
								</tr>
								<tr>
									<td colspan=4><%=vo.getMessege()%></td>
								</tr>
							</table>
							<%
 								}
 							%>  --%>
 							<br>
						</li>
					</ul>

				</div>
				<!-- /guestbook -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
	<!-- /container -->

</body>
</html>