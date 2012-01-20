<%-- 
    Document   : index
    Created on : 2011-09-24, 17:36:55
    Author     : stive
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

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
<%
	Dao dao = Dao.INSTANCE;

	List<news> newsToShow = new ArrayList<news>();
	newsToShow = dao.getNews(5);

	List<bets> betsToShow = new ArrayList<bets>();
	betsToShow = dao.getBets(15);
%>

<jsp:include page="header.jsp" />
<div id="content">
	<jsp:include page="sidebar.jsp" />

	<div style="width: 660px; min-height: 200px; float: right;">
				
Dodaj news
<form action="addNews" method="get">
Tytul<input type="text" name="title" ><br>
Tresc<TEXTAREA NAME="content" COLS=40 ROWS=6></TEXTAREA><br>

Czy post aktywny<br>
<input type="radio" name="active" value="1"> Tak
<input type="radio" name="active" value="0"> Nie<br>
Wyswietlaj do :<br>
<input type="Text" id="demo1" maxlength="25" size="25" name="endTime"><a href="javascript:NewCal('demo1','ddmmyyyy',true,24)"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
            
<input type="submit" value="dodaj" />

</form>

</div>


</div>