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
public class ServletSearchBets extends HttpServlet {

	public void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String search = checkNull(req.getParameter("s"));
		

		Dao.INSTANCE.getLeagues();
		Dao.INSTANCE.getTeams();

		resp.sendRedirect("/index.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		processRequest(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		processRequest(request, response);

	}

	private String checkNull(String s) {
		if (s == null) {
			return "-1";
		}
		return s;
	}

}
