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
import pl.dirsot.bets.model.users;
import pl.dirsot.bets.shared.zaklady;

@SuppressWarnings({ "serial", "unused" })
public class ServletChangeBet extends HttpServlet {

	public void processRequest(HttpServletRequest req,
			HttpServletResponse resp, int type) throws IOException {

		HttpSession session = req.getSession(true);
		String user = checkNull(session.getAttribute("user").toString());
		long time = System.currentTimeMillis() * 1000;

		long betId = getLong(req.getParameter("id"));
		if (type == 0) {
			float first = getFloat(req.getParameter("1"));
			float draw = getFloat(req.getParameter("X"));
			float second = getFloat(req.getParameter("2"));

			Dao.INSTANCE.changeBet(betId, first, draw, second, time);
		} else {
			Dao.INSTANCE.deactiveBet(betId);
		}

		resp.sendRedirect("/addDeal.jsp?bet="+betId);
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
