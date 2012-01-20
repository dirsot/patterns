<%-- 
    Document   : index
    Created on : 2011-09-24, 17:36:55
    Author     : stive
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="pl.dirsot.bets.model.bets"%>
<%@ page import="pl.dirsot.bets.dao.Dao"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
  String bet = (String) request.getParameter("bet"); 
  String type = (String) session.getAttribute("type");
  String user = (String) session.getAttribute("user");
	type = (type==null)?"1":"0";
	bet = (bet==null)?"1":bet;
	
%>


<%
	Dao dao = Dao.INSTANCE;
	bets betToShow =  dao.getOneBet(Long.parseLong(bet));
%>

<jsp:include page="header.jsp" />
<div  id="content">
<jsp:include page="sidebar.jsp" />

      <div class="post-container__">
	<%if(Dao.INSTANCE.isActiveUser(user)){ %>

                          Spotkanie <%=betToShow.getTeam1()%> - <%=betToShow.getTeam2()%> o <%=betToShow.getHumanDate()%>
                          Postaw zakład
                          <form method="get" action="addDeal">
                              1(<%=betToShow.getFirst()%>)<input type="radio" name="typ" value="1"/>
                              X(<%=betToShow.getDraw()%>)<input type="radio" name="typ" value="x"/>
                              2(<%=betToShow.getSecond()%>)<input type="radio" name="typ" value="2"/><br>
                              Kwota<input type="input" name="ammount"/>
                              
                              <input type="hidden" value="<%=betToShow.getId()%>" name="bet" />
                              <input type="submit" value="Dodaj"/>
                             
                          </form>
                          
                          <%if(type.equalsIgnoreCase("0")){ %>
                          Zmień stawki
                          <form action="changeBet" method="post">
                          	1 <input type="text" value="<%=betToShow.getFirst()%>" name="1" /><br>
							x <input type="text" value="<%=betToShow.getDraw()%>" name="X" /><br> 
							2 <input type="text" value="<%=betToShow.getSecond()%>" name="2" /><br /> 
							<input type="hidden" name="id" value="<%=betToShow.getId() %>" /> <br> 
							<input type="submit" value="Dodaj" />
                          </form>
                          <p>Zawieś zakład</p>
                          
                          <a href=./changeBet?id=<%=betToShow.getId() %>>Wykonaj</a>
                          
                          <%} %>

<%}else{ %>
Aby dodać konto musisz być zalogowany i aktywować konto.
<%} %>

      </div>
	

   </div>