<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="pl.dirsot.bets.model.bets" %>
<%@ page import="pl.dirsot.bets.dao.Dao" %>

<!DOCTYPE html>


<%@page import="java.util.ArrayList"%>

<html>
	<head>
		<title>Todos</title>
		<link rel="stylesheet" type="text/css" href="css/main.css"/>
		  <meta charset="utf-8"> 
	</head>
	<body>
<%
Dao dao = Dao.INSTANCE;

List<bets> betsToShow = new ArrayList<bets>();
            
betsToShow = dao.getBets(15);   
%>


<div style="clear: both;"/>	
You have a total number of <%= betsToShow.size() %>  Todos.

<table>
  <tr>
      <th>Short description </th>
      <th>Long Description</th>
      <th>URL</th>
      <th>Done</th>
    </tr>

<% for (bets bet : betsToShow) {%>
<tr> 
<td>
<%=bet.getTeam1()%>
</td>
<td>
<%=bet.getTeam2()%>
</td>
<td>
<%=bet.getHumanDate()%>
</td>
<td>
<a class="done" href="/done?id=<%=bet.getId()%>" >Done</a>
</td>
</tr> 
<%}
%>
</table><table>
  <tr>
      <th>Short description </th>
      <th>Long Description</th>
      <th>URL</th>
      <th>Done</th>
    </tr>

<% for (bets bet : betsToShow) {%>
<tr> 
<td>
<%=bet.getTeam1()%>
</td>
<td>
<%=bet.getTeam2()%>
</td>
<td>
<%=bet.getHumanDate()%>
</td>
<td>
<a class="done" href="/done?id=<%=bet.getId()%>" >Done</a>
</td>
</tr> 
<%}
%>
</table>


<hr />

<div class="main">

<div class="headline">New todo</div>

<% if ("3" != null){ %> 

<form action="/new" method="post" accept-charset="utf-8">
	<table>
		<tr>
			<td><label for="summary">Summary</label></td>
			<td><input type="text" name="summary" id="summary" size="65"/></td>
		</tr>
		<tr>
			<td valign="description"><label for="description">Description</label></td>
			<td><textarea rows="4" cols="50" name="description" id="description"></textarea></td>
		</tr>
	<tr>
		<td valign="top"><label for="url">URL</label></td>
		<td><input type="url" name="url" id="url" size="65" /></td>
	</tr>
	<tr>
			<td colspan="2" align="right"><input type="submit" value="Create"/></td>
		</tr>
	</table>
</form>

<% }else{ %>

Please login with your Google account

<% } %>
</div>
</body>
</html>