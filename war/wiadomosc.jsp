<%-- 
    Document   : index
    Created on : 2011-09-24, 17:36:55
    Author     : stive
--%>
<%@page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pl.dirsot.bets.model.comments"%>
<%@ page import="pl.dirsot.bets.model.news"%>
<%@ page import="pl.dirsot.bets.dao.Dao"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	String registerStatus = (String) request.getAttribute("registerStatus");
	String login = (String) request.getAttribute("login");
	
	
	String id = (String) request.getParameter("id");
%>
<%
	Dao dao = Dao.INSTANCE;

	List<comments> allComments = dao.getCommentsById(id);

	news info = dao.getNewsById(id);
		
%>

<jsp:include page="header.jsp" />
<div id="content">

	<jsp:include page="sidebar.jsp" />

	<div style="width: 660px; min-height: 200px; float: left;">
			<div class="post-container">

		
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

            </div>
          </div>
        </div>
 
      </div>	
		<%for (comments com:allComments){ %>	
			
			<%=com.getContent() %><br>
	<%} %>

</div>
<form action=/addComment method=post>
<input type="text" name="comment" />
<input type="hidden" name="id"  value="<%=info.getId()%>"/>
<input type="submit" value="Dodaj komentarz">
</form>

</div>
<jsp:include page="footer.jsp" />
