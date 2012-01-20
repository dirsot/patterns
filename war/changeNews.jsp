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

</div>


</div>