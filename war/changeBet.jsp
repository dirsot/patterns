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
	String id = (String) request.getAttribute("id");

	Dao dao = Dao.INSTANCE;

	bets betToChange =  dao.getBetToChange("9473");
%>

<jsp:include page="header.jsp" />
<div id="content">
	<jsp:include page="sidebar.jsp" />

	<div style="width: 660px; min-height: 200px; float: right;">
			
	<p>Zmieniasz zakład o id =	<%=betToChange.getId()%>
	
	<form action=changeBet>
	<p>pomiędzy <%=betToChange.getTeam1()%> - <%=betToChange.getTeam2()%>
	<p>odbędzie się  <input type="Text" id="demo1" maxlength="25" size="25" name="date" value="<%=betToChange.getHumanDate()%>"><a href="javascript:NewCal('demo1','ddmmyyyy',true,24)"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
	<p>Obecne stawki
	<p>1 <input type="text" name="first" value="<%=betToChange.getFirst()%>"> </input</p> 
	<p>x <input type="text" name="draw" value="<%=betToChange.getDraw()%>"> </input</p> 
	<p>2 <input type="text" name="second" value="<%=betToChange.getSecond()%>"> </input</p>
	<input type="hidden" name="id" value="<%=betToChange.getId()%>"/> 
	<p><input type="submit" value="Zmien"/>
	</form>


</div>


</div>