<%-- 
    Document   : index
    Created on : 2011-09-24, 17:36:55
    Author     : stive
--%>
<%@page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pl.dirsot.bets.model.bets"%>
<%@ page import="pl.dirsot.bets.model.news"%>
<%@ page import="pl.dirsot.bets.dao.Dao"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	String login = (String) request.getAttribute("login");
	String imie = (String) request.getAttribute("imie");
%>


<jsp:include page="header.jsp" />
<div id="content">
	<jsp:include page="sidebar.jsp" />
<%=session.getAttribute("type") %>
	<div style="width: 660px; min-height: 200px; float: right;">
			<div style="">
		<h3>Witaj na stonie dodania nowego użytkownika.<br />Podaj wszystkie dane, a następnie wyślij .</h3>
		<form action="addNewUser" method="get">
			<p>Login <input type="text" name="login" />
			<p>E-mail <input type="text" name="email" />
			<p>Imię <input type="text" name="name" />
			<p>Nazwisko <input type="text" name="surname" />
			<p>Haslo <input type="text" name="password" />
			<p>Powtórz hasło <input type="text" name="passwordAgain" />
			<p><input type="submit" value="Wyślij" />
		</form>
			


</div>
</div>
</div>
<jsp:include page="footer.jsp" />
