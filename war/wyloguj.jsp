<%-- 
    Document   : index
    Created on : 2011-09-24, 17:36:55
    Author     : stive
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
  String login = (String) request.getAttribute("login"); 
  String imie = (String) request.getAttribute("imie");
%>
<% session.setAttribute("user","None");
        session.setAttribute("logged","0"); %>
<jsp:include page="header.jsp" />
<% if(imie!="null") %><div style="height:30px;width: 100%;">Witaj <%=imie %>, zostałeś poprawnie zalogowany.</div>
<div  id="content">
<jsp:include page="sidebar.jsp" />

      <div class="post-container">
          Zostałeś wylogowany.
        

      </div>
	

   </div>