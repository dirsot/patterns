<%-- 
    Document   : index
    Created on : 2011-09-24, 17:36:55
    Author     : stive
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pl.dirsot.bets.model.leagues"%>
<%@ page import="pl.dirsot.bets.model.bets"%>
<%@ page import="pl.dirsot.bets.model.teams"%>
<%@ page import="pl.dirsot.bets.utils.search"%>
<%@ page import="java.util.List"%>
<%@ page import="java.lang.Integer"%>
<%@page import="java.util.ArrayList"%>

<%
	String s = (String) request.getParameter("search");
	String direct = (String) request.getParameter("direct");
	String type = (String) request.getParameter("type");
%>
<%
	search classe = search.INSTANCE;
	List<leagues> leaguesToShow = new ArrayList<leagues>();
	leaguesToShow = classe.searchLeagues(s);

	List<teams> teamsToShow = new ArrayList<teams>();
	teamsToShow = classe.searchTeams(s);
	
	List<bets> betsToShow = new ArrayList<bets>();
	if (type != null) {
		betsToShow = classe.getBets(s, type);
	}
%>

<jsp:include page="header.jsp" />
<div id="content">
	<jsp:include page="sidebar.jsp" />

	<div style="width: 660px; min-height: 200px; float: right;">
		<div style="height: 40px"></div>
		Szukasz zakladow dla podanego kryterium
		<table style="width: 660px">
			<%
				if (type != null) {
					for (bets bet : betsToShow)
						if (bet.getMore() != null) {
			%>

			<tr>
				<td><a href="./addDeal.jsp?bet=<%=bet.getId()%>"><%=bet.getTeam1()%>-<%=bet.getTeam2()%>
						<%=bet.getMore()%></a></td>
				<td><%=bet.getHumanDate()%></td>
				<td><%=bet.getFirst()%></td>
				<td><%=bet.getDraw()%></td>
				<td><%=bet.getSecond()%></td>
			</tr>

			<%
				}
				} else {
			%>
		</table>
		<br> Do twojego zapytanie pasuja nastepujace Ligi:
		<%
			for (leagues league : leaguesToShow) {
		%>
		<p>
			<a
				href="/showResult.jsp?search=<%=league.getLeague().replace(" ","_")%>&direct=1&type=l "><%=league.getLeague()%></a>
		</p>

		<%
			}
		%>
		<div style="height: 40px"></div>
		Druzyny:
		<%
			for (teams team : teamsToShow) {
		%>
		<p>
			<a href="/showResult.jsp?search=<%=team.getTeam().replace(" ","_")%>&direct=1&type=d "><%=team.getTeam()%></a>
		</p>

		<%
			}
			}
		%>
	</div>


</div>