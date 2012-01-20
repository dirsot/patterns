<%-- 
    Document   : index
    Created on : 2011-09-24, 17:36:55
    Author     : stive
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pl.dirsot.bets.model.bets"%>
<%@ page import="pl.dirsot.bets.dao.Dao"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	String login = (String) request.getAttribute("login");
	String imie = (String) request.getAttribute("imie");
%>
<%
	Dao dao = Dao.INSTANCE;
	String id = (String) request.getAttribute("id");
	List<bets> betsToShow = new ArrayList<bets>();
	betsToShow = dao.getBetsByLeague("Championship");
%>

<jsp:include page="header.jsp" />
<div id="content">
	<jsp:include page="sidebar.jsp" />



	<div style="width: 660px; min-height: 200px; float: right;">
		<div class="posttop">
			<div id="date">
				<p class="month">aa</p>
			</div>
			<h2>
				<a href="./" rel="bookmark" title="Permanent Link to ddd"> ff </a>
			</h2>
			<div class="postinfo">Posted by ff</div>
		</div>
		<div class="entry">Read the rest of this entry</div>
		<div class="postbottom">
			<div class="metainf">Filled Under: f</div>
			<div class="commentinf">
				<%
					if (4 > 0) {
				%>
				Obecnie jest 1 komentarzy.
				<%
					} else {
				%>
				Dodaj komentarz
				<%
					}
				%>
			</div>
		</div>
	</div>


	<div class="popular"
		style="width: 600; min-height: 100px; margin: 5px 5px 5px 5px; float: right;">
		Najpopularniejsze

		<table style="width: 660px">
			<tr>
				<td>Spotkanie</td>
				<td>Data</td>
				<td>1</td>
				<td>X</td>
				<td>2</td>
			</tr>
			<%
				if (betsToShow != null)
					for (bets bet : betsToShow) {
			%>
			<tr>
				<td><a href="./addDeal.jsp?bet=<%=bet.getId()%>"><%=bet.getTeam1()%>-<%=bet.getTeam2()%><%=bet.getLeague()%></a>
				</td>
				<td><%=bet.getHumanDate()%></td>
				<td><%=bet.getFirst()%></td>
				<td><%=bet.getDraw()%></td>
				<td><%=bet.getSecond()%></td>
			</tr>
			<%
				}
			%>

		</table>


	</div>
	<div class="ending"
		style="width: 600; min-height: 100px; margin: 5px 5px 5px 5px; float: right;">
		Konczoce sie
		<table style="width: 660px">
			<tr>
				<td>Spotkanie
				<td>
				<td>Data</td>
				<td>1</td>
				<td>X</td>
				<td>2</td>
			<tr>

				<td><a href="./addBet.jsp?bet=2">33-33</a>
				<td>
				<td>33</td>
				<td>33</td>
				<td>33</td>
				<td>rr</td>
			<tr>
			<tr>
		</table>
	</div>

</div>


</div>