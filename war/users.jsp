<%-- 
    Document   : index
    Created on : 2011-09-24, 17:36:55
    Author     : stive
--%>
<%@page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pl.dirsot.bets.model.bets"%>
<%@ page import="pl.dirsot.bets.model.users"%>
<%@ page import="pl.dirsot.bets.dao.Dao"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	Dao dao = Dao.INSTANCE;

	List<users> allUsers = new ArrayList<users>();
	allUsers = dao.getUsers();

	
	
%>


<jsp:include page="header.jsp" />
<div id="content">
	<jsp:include page="sidebar.jsp" />

	<div style="width: 660px; min-height: 200px; float: right;">
			<div style="">
		
		<table style="width: 660px">
			<tr>
				<td>Login</td>
				<td>Aktywny</td>
				<td>Rola</td>
			</tr>
			<%
				for (users user : allUsers){
			%>
			<tr>
				<td><%=user.getLogin()%></td>
				<td><% out.print(((user.getActive())?"Tak":"Nie"));%></td>
				<td><% out.print(((user.getType()==0)?"Administrator":"Klient")); %></td>
			</tr>
			<%
				}
			%>
	</table>

</div>
</div>
</div>
<jsp:include page="footer.jsp" />
