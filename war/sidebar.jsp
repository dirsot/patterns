<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="pl.dirsot.bets.model.deals"%>
<%@ page import="pl.dirsot.bets.model.incoms"%>
<%@ page import="pl.dirsot.bets.model.leagues"%>
<%@ page import="pl.dirsot.bets.dao.Dao"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
	String logged = (String) session.getAttribute("logged");
	String user = (String) session.getAttribute("user");
	String type = (String) session.getAttribute("type");
	if(user == null){
		user = "0";
	}
	if(logged == null){
		logged = "0";
	}
	if(type == null){
		type = "1";
	}
	Dao dao = Dao.INSTANCE;

	incoms incom = dao.getIncom(user);
	
%>

<script>
	function sfocus() {
		if (document.getElementById('s').value == 'Wpisz lige lub klub') {
			document.getElementById('s').value = '';
		}
	}
	function sblur() {
		if (document.getElementById('s').value == '') {
			document.getElementById('s').value = 'Wpisz lige lub klub';
		}
	}
</script>

<div id="sidebar">
	<div id="rss">
		<a href="rss"> <img src="./images/rss.gif">
		</a>
	</div>
	<ul>
		<li><h2>Szukaj zakladów</h2>

			<div id="menu_search_box">
				<form method="get" id="searchform" style="display: inline;"
					action="showResult.jsp">

					<input type="text" class="s" value="Wpisz lige lub klub" name="search" id="s"
						onBlur="sblur()" onFocus="return sfocus()" />&nbsp;

				</form>
			</div></li>
		<script language="JavaScript" type="text/javascript">
		function showPay(){
document.getElementById("wplata").style.visibility = "visible";
		}
</script>
		<li><h2>Konto</h2>
			<ul>
				<%
					if (logged.contains("1")) {
				%>
				<p>
					<a href="#">Na swoim koncie posiadasz <%=incom.getQuantity() %>zł</a>
				</p>
				<p>
					<a href="#">Wypłać pieniądze</a>
				</p>
				<p>
					<a href="#" onclick="return showPay();">Dokonaj wpłaty</a>
				</p>
				<div id="wplata" style="visibility:hidden"><form action="./wplac" method="post"><input type="text" name="quantity"></input><input type="submit" value="wpłać"> </form> </div>
				<p>
					<a href="#">Sprawdz hisotrię</a>
				</p>
				<%
					} else {
				%>
				<p>
					<a href="./logowanie.jsp">Zaloguj sie </a>
				</p>
				<p>
					<a href="register.jsp">Załóż nowe konto </a>
				</p>
				<%
					}
				%>
			</ul></li>

		<%

			List<leagues> leagues = new ArrayList<leagues>();
			leagues = dao.getLeagues();

			List<deals> userDeals = new ArrayList<deals>();
			userDeals = dao.getUserDeals(user,15);
			if (logged.contains("1")) {
		%>
		<li><h2>Zaklady</h2>
			<ul>
				<%
					for (deals deal : userDeals) 
						if(deal.getActive() )
						{
				%>
				<p>
					<a href=./addDeal.jsp?bet=<%=deal.getBet()%>>Na <%=deal.getWho()%> w zakładzie <%=deal.getBet()%> postawiles <%=deal.getAmmount()%> zł</a>
				</p>

				<%
					}
				%>
			</ul></li>
		<%
			}
		%>
		<li><h2>Ligi</h2>
			<ul>
				<%
					for (leagues league : leagues) {
				%>
				<p>
					<a href="./liga.jsp?id=<%=league.getLeague().replace(" ","_")%>"><%=league.getLeague()%></a>
				</p>
				<%
					}
				%>

			</ul></li>
		<%
			if (type.contains("0")) {
		%>
		<li><h2>Administracja</h2>
			<ul>
				<p>
					<a href="addTeam.jsp">Dodaj druzyne lub lige</a>
				</p>
				<p>
					<a href="addBet.jsp">Dodaj zaklad</a>
				</p>
				<p>
					<a href="addNews.jsp">Dodaj info</a>
				</p>
				<p>
					<a href="changeNews.jsp">Modyfikuj info</a>
				</p>
				<p>
					<a href="changeBet.jsp">Modyfikuj zaklad</a>
				</p>
			</ul></li>
		<%
			}
		%>
		<p>
					<a href="others/praca.pdf">pdf</a>
				</p>
	</ul>
</div>
