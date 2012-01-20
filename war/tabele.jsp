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

<jsp:include page="header.jsp" />
<% if(imie!="null") %><div style="height:30px;width: 100%;">Witaj <%=imie %>, zostałeś poprawnie zalogowany.</div>
<div  id="content">
<jsp:include page="sidebar.jsp" />

      <div class="post-container__">
        
          <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0" width="550" height="300" id="zoom_map" align="top">
<param name="movie" value="europe.swf?data_file=data.xml" />
<param name="quality" value="high" />
<param name="bgcolor" value="#FFFFFF" />
<embed src="europe.swf?data_file=data.xml" quality="high" bgcolor="#FFFFFF"  width="550" height="300" name="Clickable europe Map" align="top" 
type="application/x-shockwave-flash" 
pluginspage="http://www.macromedia.com/go/getflashplayer"></embed>
</object>
     
     <c:if test="${fn:length(param.kraj) > 0}" >
     <iframe frameborder="0" width="478" height="1000" scrolling="auto" src="http://www.activescores.com/standings.php?f=${param.kraj}"></iframe>
     </c:if>

      </div>
	

   </div>