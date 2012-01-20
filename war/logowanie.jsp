<%@page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pl.dirsot.bets.model.bets"%>
<%@ page import="pl.dirsot.bets.model.news"%>
<%@ page import="pl.dirsot.bets.dao.Dao"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	String registerStatus = (String) request.getAttribute("registerStatus");
	String login = (String) request.getAttribute("login");
	String imie = (String) request.getAttribute("imie");
%>


<jsp:include page="header.jsp" />
<div id="content">

	<jsp:include page="sidebar.jsp" />

	<form method="post" action=logUser>
							Login:<input type="text" name="login" /> <br>
							Hasło:<input type="password" name="pass" /><br> 
							<input type="submit" value="Loguj" />
						</form>


</div>
<jsp:include page="footer.jsp" />
