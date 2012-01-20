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

@SuppressWarnings({ "serial", "unused" })
public class ServletAddPayment extends HttpServlet {

	public void processRequest(HttpServletRequest req,
			HttpServletResponse resp, int type) throws IOException {

		HttpSession session = req.getSession(true);
		String user = checkNull(session.getAttribute("user").toString());

		float quantity = getFloat(req.getParameter("quantity"));
		
		
		Dao.INSTANCE.addMoney(user, quantity);
		
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
