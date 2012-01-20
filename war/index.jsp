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
	String registerStatus = (String) request.getAttribute("registerStatus");
	String login = (String) request.getAttribute("login");
	String imie = (String) request.getAttribute("imie");
%>
<%
	Dao dao = Dao.INSTANCE;

	List<news> newsToShow = new ArrayList<news>();
	newsToShow = dao.getNews(5);

	List<bets> betsToShow = new ArrayList<bets>();
	betsToShow = dao.getBets(150);
	
	
%>

<jsp:include page="header.jsp" />
<div id="content">

	<jsp:include page="sidebar.jsp" />

	<div style="width: 660px; min-height: 200px; float: left;">
			<div class="post-container">
					<%
				for (news info : newsToShow) {
			%>
		
        <div class="post" id="post-<%=info.getId()%>">
          <div class="posttop">
		  <div id="date">
            <p class="month">
            <%=info.getHumanDate()%>
            </p>
          </div>
            <h2>
              <%=info.getTitle()%>
              </h2>
            <div class="postinfo"> Posted by
              <%=info.getUserName()%>,
              
			  </div>
         
        </div>
          <div class="entry">
          <%=info.getContent()%>
          
          </div>
          <div class="postbottom">

            <div class="commentinf">
            	<a href=./wiadomosc.jsp?id=<%=info.getId()%>>Brak komentarzy</a>
              
            </div>
          </div>
        </div>
       <% } %>
      </div>	
			
			
			


	<div class="popular"
		style="width: 600; min-height: 100px; margin: 5px 5px 5px 5px; float: left;">

		<table style="width: 660px">
			<tr>
				<td>Spotkanie</td>
				<td>Data</td>
				<td>1</td>
				<td>X</td>
				<td>2</td>
			</tr>
			<%
				for (bets bet : betsToShow) 
					if((bet.getMore()!=null)&&(bet.getActive())){
			%>
			<tr>
				<td><a href="./changeBet.jsp?id=<%=bet.getId()%>"></a><a href="./addDeal.jsp?bet=<%=bet.getId()%>"><%=bet.getTeam1()%>-<%=bet.getTeam2()%></a>
				</td>
				<td><%=bet.getHumanDate()%></td>
				<td><%=bet.getPositiveFirst()%></td>
				<td><%=bet.getPositiveDraw()%></td>
				<td><%=bet.getPositiveSecond()%></td>
			</tr>
			<%
				}
			%>

		</table>


	</div>


</div>


</div>
<jsp:include page="footer.jsp" />
