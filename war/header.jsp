<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.Date" session="true"%>
<%@ page import="java.lang.Integer"%>

<%
	//sports.findOldBets();
%>
<%
	if (session.isNew()) {
		session.setAttribute("user", "None");
		session.setAttribute("type", "-1");
		session.setAttribute("logged", "0");
	}
String type = (String) session.getAttribute("type");
	type = (type==null)?"1":type;
%>



<html xmlns="http://www.w3.org/1999/xhtml">
<head profile="http://gmpg.org/xfn/11">
<title>Zakłady bukmacherskie</title>
<!-- leave this for stats -->
<link rel="stylesheet" href="./style.css" type="text/css" media="screen" />
<link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="" />
<link rel="alternate" type="text/xml" title="RSS .92" href="" />
<link rel="alternate" type="application/atom+xml" title="Atom 0.3"
	href="" />
<link rel="pingback" href="" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language="javascript" type="text/javascript"
	src="js/datetimepicker.js">
</script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-26662013-1']);
  _gaq.push(['_setDomainName', '.dirsot.pl']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
</head>

<body>

	<div id="wrap">
		<div id="content-container">

			<div id="header">

				<h1>
					<a href="//">Najlepsze zakłady w internecie</a>
				</h1>
				<div id="subtitle">Każdego dnia setki możliwości na wygraną</div>
				<div class="alignleft"></div>

			</div>
			<div id="navlist-container">
				<div id="navlist">
					<ul>

						<li class=""><a href="./">Home</a></li>
						<li class=""><a href="./tabele.jsp">Tabele</a></li>
					
						<%
							String logged = "";
							logged = (String) session.getAttribute("logged");
							String one = "1";
							if (logged == null) {
								logged = "0";
							}

							if (logged.contains(one)) {
						%>

						<li class=""><a href="./wyloguj.jsp">Wyloguj</a></li>

						<%
							if(((session.getAttribute("type").toString()).equalsIgnoreCase("0"))) {
						%>
						<li class=""><a href="./addBet.jsp">Dodaj zaklad</a></li>
						<li class=""><a href="./changeBet.jsp">Zmien zaklad</a></li>
						<li class=""><a href="./statystyki.jsp">Statystyki</a></li>
						<li class=""><a href="./users.jsp">Użytkownicy</a></li>
						<%}
							} 
						%>

					</ul>
				</div>
			</div>