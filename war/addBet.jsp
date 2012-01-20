<%-- 
    Document   : index
    Created on : 2011-09-24, 17:36:55
    Author     : stive
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pl.dirsot.bets.model.teams"%>
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

	List<teams> teams = new ArrayList<teams>();
	teams = dao.getTeams();


%>

<jsp:include page="header.jsp" />
<div id="content">
	<jsp:include page="sidebar.jsp" />

	<div style="width: 660px; min-height: 200px; float: right;">

		<form action="addBet" method="get">
			Wybierz pierwsza druzyne <select name="team1" size="w">
				<%
					for (teams one : teams) {
				%>
			<option value="<%=one.getTeam()%>"><%=one.getTeam()%></option>
				<%
					}
				%>

			</select><br> Wybierz druga <select name="team2" size="w">
				<%
					for (teams one : teams) {
				%>
				<option value="<%=one.getTeam()%>"><%=one.getTeam()%></option>
				<%
					}
				%>
			</select><br /> 
			<input type="Text" id="demo1" maxlength="25" size="25">
			<a href="javascript:NewCal('demo1','ddmmyyyy',true,24)">
			<img src="images/cal.gif" width="16" height="16" border="0"
				alt="Pick a date"></a> Podaj stawki podstawowe<br />
			1 <input type="text" value="1" name="1" /><br>
			x <input type="text" value="1" name="X" /><br> 
			2 <input type="text" value="1" name="2" /><br /> <br> 
			<input type="submit" value="Dodaj" />
		</form>

	</div>


</div>