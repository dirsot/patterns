<%-- 
    Document   : index
    Created on : 2011-09-24, 17:36:55
    Author     : stive
--%>
<%@page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pl.dirsot.bets.model.deals"%>
<%@ page import="pl.dirsot.bets.model.news"%>
<%@ page import="pl.dirsot.bets.shared.Stats"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	Stats stats = Stats.INSTANCE;

	String type = (String) request.getParameter("type");
	List<deals> dealsByAmmount = new ArrayList<deals>();
	List<deals> activeDeals = new ArrayList<deals>();
	List<deals> dealsByUser = new ArrayList<deals>();
	if(type==null)
		type="";
	
	if (type.equalsIgnoreCase("ammount")) {
		dealsByAmmount = stats.getDealsByAmmount();
	} else if (type.equalsIgnoreCase("user")) {
		dealsByUser = stats.getDealsByUser();
	} else {
		activeDeals = stats.getActiveDeals();
	}
%>

<jsp:include page="header.jsp" />
<div id="content">

	<jsp:include page="sidebar.jsp" />

	<div style="width: 660px; min-height: 200px; float: right;">

		<div class="popular"
			style="width: 600; min-height: 100px; margin: 5px 5px 5px 5px; float: right;">
			<%
				if (type.equalsIgnoreCase("ammount")) {
					out.println("Lista zakładów po kwocie");
				} else if (type.equalsIgnoreCase("user")) {
					out.println("Lista zakładów po użytkowniku");
				} else {
					out.println("Lista aktywnych zakładów");
				}
			%>
			<table style="width: 660px">
				<tr>
					<td>User</td>
					<td>Zakład</td>
					<td>Kwota</td>
					<td>Założono</td>
					<td>Początek</td>
				</tr>
				<%
					for (deals deal : dealsByUser) {
				%>
				<tr>

					<td><%=deal.getUserName()%></td>
					<td><%=deal.getBet()%></td>
					<td><%=deal.getAmmount()%></td>
					<td><%=deal.getDateAdded()%></td>
					<td><%=deal.getDateEnd()%></td>

				</tr>
				<%
					}
				%>

			</table>


		</div>


	</div>


</div>
<jsp:include page="footer.jsp" />
