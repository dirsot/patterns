package pl.dirsot.bets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.dirsot.bets.dao.Dao;
import pl.dirsot.bets.model.incoms;

@SuppressWarnings({ "serial", "unused" })
public class ServletAddBet extends HttpServlet {

	public void processRequest(HttpServletRequest req,
			HttpServletResponse resp, int type) throws IOException {

		HttpSession session = req.getSession(true);
		String user = checkNull(session.getAttribute("user").toString());
		long time = System.currentTimeMillis() * 1000;


		String team1 = req.getParameter("team1");
		String team2 = req.getParameter("team2");
			float first = getFloat(req.getParameter("1"));
			float draw = getFloat(req.getParameter("X"));
			float second = getFloat(req.getParameter("2"));

		
		Dao.INSTANCE.addBet(team1, team2, time,"", "", first, draw, second,
				"_", 0, true, -1,-1);
		
		
		resp.sendRedirect("/index.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		processRequest(request, response, 0);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		processRequest(request, response, 1);
	}

	private long getLong(String value) {
		long number = Long.valueOf(checkNull(value));
		return number;
	}

	private float getFloat(String value) {
		Float number = Float.valueOf(checkNull(value));
		return number;
	}

	private String checkNull(String s) {
		if (s == null) {
			return "-1";
		}
		return s;
	}

}
